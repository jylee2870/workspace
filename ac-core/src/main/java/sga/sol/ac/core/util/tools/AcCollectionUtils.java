package sga.sol.ac.core.util.tools;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class AcCollectionUtils {
	private static Logger logger = LoggerFactory.getLogger(AcCollectionUtils.class);
	/**
	 * @Date : 2015. 1. 22.
	 * @Author : swcho
	 * @Description : List<Map<String,String>>을 하나의 Map<Stirng,String>으로 만들어준다
	 * @param inputList 변환할 LIST
	 * @param key
	 * @param value
	 */
	public static <T extends Object> Map<String, String> listToMap(List<? extends Map<T, T>> inputList, String key, String value) {
		Map<String, String> returnMap = new HashMap<String, String>();

		for (Map<T, T> map : inputList) {
			String mapKey = (String)map.get(key);
			String mapValue = (String)map.get(value);
			returnMap.put(mapKey, mapValue);
		}

		return returnMap;
	}

	/**
	 * @Date		: 2015. 2. 3. 
	 * @Author		: swcho
	 * @Description	: 빈에서 특정 속성 값만 리스트로 만들어 리턴한다
	 * @param inputObject
	 * @param property
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static <T, S> List<S> makeProperyListFromCollectionObject(Collection<T> inputObject, String property) {
		// 이 메서드에서만 사용되는 클래스를 정의한다
		class CustomTransformer implements Transformer {
			private String property;

			public CustomTransformer(String property) {
				this.property = property;
			}

			@Override
			public Object transform(Object paramObject) {
				//빈유틸로 특정 타입을 가지는 특정 값을 추출한다
				//BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(paramObject);
				//Class<S> clazz = wrapper.getPropertyType(property);
	
				//logger.info("type : {}  / value : {}", property, clazz);
				//return ConvertUtils.convert(wrapper.getPropertyValue(property),	clazz);
				
				S objectValue = getPropertyValue((T)paramObject, property);
				return objectValue;
			}
		}
		// 특정 속성값의 타입을 제네릭으로 하는 콜렉션 객체만든다
		Collection<S> collection = CollectionUtils.collect(inputObject,new CustomTransformer(property));
		
		// 콜렉션 객체에서 중복을 제거하기 위해 Set으로 변환한 후 리스트로 재 변환하여 리턴한다
		Set<S> set = new HashSet<S>(collection);
		return new ArrayList<S>(set);
	}

	/**
	 * @Date		: 2015. 2. 3. 
	 * @Author		: swcho
	 * @Description	: 콜렉션 객체를 빼줄때 속성으로 지정해준 값이 같으면 빼준다 
	 * @param fromCollection
	 * @param byCollection
	 * @param subtractFactoPropery
	 * @return
	 */
	public static <T,S> Collection<T> subtract(Collection<T> fromCollection, Collection<T> byCollection, String subtractFactorProperty){
		Collection<T> result = new ArrayList<T>(fromCollection);
		Iterator<T> iterator = byCollection.iterator();
		
		while( iterator.hasNext() ){
			T object = iterator.next();
			/*BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
			Class<S> clazz = wrapper.getPropertyType(subtractFactorProperty);
			S value =(S)ConvertUtils.convert(wrapper.getPropertyValue(subtractFactorProperty),clazz);*/
			S value = getPropertyValue(object, subtractFactorProperty);
			
			Iterator<T> iterator2 = fromCollection.iterator();
			
			while( iterator2.hasNext() ){
				/*BeanWrapper wrapper2 = PropertyAccessorFactory.forBeanPropertyAccess(object2);
				S value2 = (S)ConvertUtils.convert(wrapper2.getPropertyValue(subtractFactoPropery),clazz);
				*/
				T object2 = iterator2.next();
				S value2 = getPropertyValue(object2, subtractFactorProperty);
				
				if (value.equals( value2 ) ){
					result.remove(object2);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @Date		: 2015. 2. 3. 
	 * @Author		: swcho
	 * @Description	: 객체를 단순 비교해서 앞의 콜렉션에서 뒤의 콜렉션을 빼준다.
	 * @param fromCollection
	 * @param byCollection
	 * @return
	 */
	public static <T> Collection<T> subtract(Collection<T> fromCollection, Collection<T> byCollection){
		Collection<T> result = new ArrayList<T>(fromCollection);
		Iterator<T> iterator = byCollection.iterator();
		
		while( iterator.hasNext() ){
			result.remove( iterator.next() );
		}
		
		return result;
	}
	
	
	/**
	 * @Date		: 2015. 2. 4. 
	 * @Author		: swcho
	 * @Description	: 대상 객체의 속성 값을 속성명을 통해서 리턴한다 (내부에서만 사용)
	 * @param object
	 * @param property
	 * @return
	 */
	public static <T,S> S getPropertyValue(T object, String property){
		// 대상 객체를 리플렉션 방식으로 다루기 위해 스프링 제공 유틸로 wrap한다
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		
		// 대상 객체에서 prorerty로 반환되는 타입을 알아낸다 
		//Class<S> clazz = wrapper.getPropertyType(property);
		Class<?> clazz = wrapper.getPropertyType(property);
		
		// 대상 객체의 property 값을 추출하고, 위에서 알아낸 타입으로 변환한다
		S value = (S)ConvertUtils.convert(wrapper.getPropertyValue(property), clazz);
		
		return value;
	}
	
	/**
	 * 지정한 객체의 읽기용 속성 이름 목록을 반환한다.
	 * 
	 * @author surfree
	 * @param object 객체
	 * @return String[] 읽기용 속성 이름 목록
	 */
	public static <T> String [] getPropertyNames(T object) {
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		PropertyDescriptor descs[] = wrapper.getPropertyDescriptors();
		List<String> propNames = new ArrayList<String>();
		
		for(PropertyDescriptor desc: descs) {
			String name = desc.getName();
			Method method = desc.getReadMethod();
			
			// getter 존재 확인 후 getter가 존재하는 항목만 얻는다.
			if(null != method && !name.equalsIgnoreCase("class") ) {				
				propNames.add(name);
			}
		}
		
		return propNames.toArray(new String[0]);
	}
	
	public static String [] excludeStrings(String[] fields, String[] excludes) {
		List<String> fieldList = Arrays.asList(fields);
		ArrayList<String> resultList = new ArrayList<String>();
		
		resultList.addAll(fieldList);
		for(String exclude: excludes) {
			resultList.remove(exclude);
		}
		
		fields = resultList.toArray(new String[0]);
		
		return fields;
	}
	
	/**
	 * 지정한 객체의 속성 이름 목록에 해당하는 값들로 Map 객체로 반환한다.
	 * 
	 * @author surfree
	 * @param object 객체
	 * @param properties String[] 포함할 읽기용 속성 이름 목록
	 * @param nullToEmpty boolean 속성 값이 null일 경우 공백으로 치환 여부
	 * @return Map key에서 지정한 속성들이 포함된 Map 객체
	 */
	public static <T> Map<String, Object> getPropertyValues(T object, String properties[], boolean nullToEmpty) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		
		for(String property: properties) {
			PropertyDescriptor desc = wrapper.getPropertyDescriptor(property);
			String name = desc.getName();
			
			Method method = desc.getReadMethod();
			if(null != method && ! name.equalsIgnoreCase("class") ) {
				try {
					Object value = method.invoke(object);
					if(nullToEmpty && null == value) {
						result.put(name, "");
					}
					else {
						result.put(name, value);
					}
				} catch (Exception e) {
					logger.warn(e.getMessage(), e);
				}
			}
		}
		
		return result;		
	}
	
	/**
	 * 객체가 포함된 컬렉션에서 지정한 속성만 포함한 Map 컬렉션으로 변환한다.
	 * 
	 * @author surfree
	 * @param inputObject 변환할 컬렉션
	 * @param clazz	 컬렉션 객체에 포함되어 있는 클래스
	 * @param properties String[] 변환 시 포함할 읽기용 속성 이름 목록
	 * @param nullToEmpty boolean 속성 값이 null일 경우 공백으로 치환 여부
	 * @return
	 */
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> List<Map> makeMapListFromCollectionObject(Collection<T> inputObject, Class<T> clazz, String properties[], boolean nullToEmpty) {
		// 이 메서드에서만 사용되는 클래스를 정의한다
		class CustomTransformer implements Transformer {
			private String properties[];
			private boolean nullToEmpty;

			public CustomTransformer(String properties[], boolean nullToEmpty) {
				this.properties = properties;
				this.nullToEmpty = nullToEmpty;
			}

			@Override
			public Object transform(Object paramObject) {
				//빈유틸로 특정 타입을 가지는 특정 값을 추출한다
				Map objectValue = getPropertyValues((T)paramObject, properties, nullToEmpty);
				return objectValue;
			}
		}
		// 특정 속성값의 타입을 제네릭으로 하는 콜렉션 객체만든다
		Collection<Map> collection = CollectionUtils.collect(inputObject, new CustomTransformer(properties, nullToEmpty));
		
		// 콜렉션 객체에서 중복을 제거하기 위해 Set으로 변환한 후 리스트로 재 변환하여 리턴한다
		//Set<S> set = new HashSet<S>(collection);
		//return new ArrayList<S>(set);
		return new ArrayList<Map>(collection);
	}
	
	/**
	 * @Date		: 2015. 2. 4. 
	 * @Author		: swcho
	 * @Description	: 콜렉션에 있는 객체에서 속성의 값이 지정된 값과 같을때만 선택해서 콜렉션을 리턴한다
	 * @param fromCollection			대상콜렉션
	 * @param selectionFactorProperty	객체 속성 이름
	 * @param selectionFactorValue		속성과 비교할 값	
	 * @return
	 */
	public static <T,S> Collection<T> select(Collection<T> fromCollection, String selectionFactorProperty, S selectionFactorValue){
		class CustomPredicate implements Predicate{
			private String factor;
			private S value;
			public CustomPredicate(String factor, S value) {
				this.factor = factor;
				this.value = value;
			}
			
			@Override
			public boolean evaluate(Object object) {
				/*BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(paramObject);
				Class<S> clazz = wrapper.getPropertyType(this.factor);
				S paramValue = (S)ConvertUtils.convert(wrapper.getPropertyValue(this.factor),clazz);
				return paramValue.equals(value);*/
				
				S objectValue = getPropertyValue((T)object, factor);
				
				return objectValue.equals(value);
			}
		}
		Collection<T> collection = CollectionUtils.select(fromCollection, new CustomPredicate(selectionFactorProperty, selectionFactorValue));
		
		return collection;
	}
	
	/**
	 * @Description : 모델을 Map으로 변환
	 * @author : LeeJungYoung
	 * @param obj : 변환될 Model
	 * @return Map
	 */
	public static Map ConverObjectToMap(Object obj){
		try {
			//Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
			Field[] fields = obj.getClass().getDeclaredFields();
			Map resultMap = new HashMap();
			for(int i=0; i<=fields.length-1;i++){
				fields[i].setAccessible(true);
				resultMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return resultMap;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
