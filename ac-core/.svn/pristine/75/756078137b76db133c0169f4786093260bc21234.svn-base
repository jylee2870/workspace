package sga.sol.ac.core.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

/**
 * @Date		: 2016. 11. 1. 
 * @Author		: swcho
 * @Description	: 서비스 리스너 - 특정 메서드 실행 시 함께 실행할 메서드를 등록, 실행한다
 * 				  스프링 빈을 사용하기 위해 ApplicationContext를 DI해줘야 하며
 * 				  구현된 리스너는 @PostContruct를 통해 register를 제일 처음으로 실행해야 한다.
 * 				  (부트업시 실행할 대상을 등록하는 과정이 필요하기 때문에)
 */
public abstract class ServiceListener {
	protected List<ExecuteEntity> methods;
	
	protected ApplicationContext rootContext;
	
	public void register(Class<? extends Annotation> annotationClass) {
		methods = new ArrayList<ExecuteEntity>();
		
		for (String beanName : rootContext.getBeanDefinitionNames()) {
            Object obj = rootContext.getBean(beanName);
            
            Class<?> objClz = obj.getClass();
            if (org.springframework.aop.support.AopUtils.isAopProxy(obj)) {

                objClz = org.springframework.aop.support.AopUtils.getTargetClass(obj);
            }

            for (Method m : objClz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(annotationClass) ) {
                	ExecuteEntity entity = new ExecuteEntity();
                	entity.setBeanName(beanName);
                	entity.setClazz(objClz);
                	entity.setMethod(m);
                	
                	methods.add(entity);
                }
            }
        }
	}
		
	abstract public void chainExecute(Object...objects);
}
