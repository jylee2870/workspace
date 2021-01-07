package sga.sol.ac.core.listener.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import sga.sol.ac.core.listener.ExecuteEntity;
import sga.sol.ac.core.listener.ServiceListener;
import sga.sol.ac.core.listener.annotation.DeleteUser;

/**
 * @Date		: 2016. 11. 1. 
 * @Author		: swcho
 * @Description	: UserInfoService.deleteUserInfoByRecids 에서 사용자 기본 정보 삭제시 사용
 * 				  @DeleteUser가 붙은 메서드를 실행  
 */
@Component
public class UserDeleteListener extends ServiceListener {

	@Autowired
	public void setRootContext(ApplicationContext rootContext){
		this.rootContext = rootContext;
	}
	
	@PostConstruct
	public void init(){
		super.register(DeleteUser.class);
	}
	
	@Override
	public void chainExecute(Object... objects) {
		List<Integer> userRecids =(List<Integer>)objects[0];
		
		for( ExecuteEntity entity : methods){
			try {
				entity.getMethod().invoke( rootContext.getBean( entity.getBeanName(), entity.getClazz() ), userRecids );
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
