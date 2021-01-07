package sga.sol.ac.core.listener.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import sga.sol.ac.core.listener.ExecuteEntity;
import sga.sol.ac.core.listener.ServiceListener;
import sga.sol.ac.core.listener.annotation.DeleteUserByLevel;

@Component
public class UserDeleteByLevelListener extends ServiceListener {

	@Autowired
	public void setRootContext(ApplicationContext rootContext){
		this.rootContext = rootContext;
	}
	
	@PostConstruct
	public void init(){
		super.register(DeleteUserByLevel.class);
	}
	
	@Override
	public void chainExecute(Object... objects) {
		int userLevelCd = (Integer)objects[0];
		
		for( ExecuteEntity entity : methods){
			try {
				entity.getMethod().invoke( rootContext.getBean( entity.getBeanName(), entity.getClazz() ), userLevelCd );
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

}
