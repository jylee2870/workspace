package sga.sol.ac.core.listener.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Date		: 2017. 4. 27. 
 * @Author		: swcho
 * @Description	: 사용자 정보를 사용자등급에 의해 삭제시 참조되는 데이터를 삭제하기위한 표시기 
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeleteUserByLevel {

}
