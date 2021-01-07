package sga.sol.ac.core.util.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AcCoreContextUtil implements ApplicationContextAware{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("setApplicationContext: " + applicationContext.getId());
		context = applicationContext;
	}

	/**
	 * @Method		: getContext
	 * @Date		: 2014. 7. 10. 
	 * @Author		: swcho
	 * @History		: 2014. 7. 10. 수정
	 * 				  2014. 8. 20. 수정 리스너를 사용하지 않고 스프링컨텍스트를 가져오게끔 변경	
	 * @Description	: 글로벌프로바이더로부터 서블릿 시작시 등록된 스프링컨텍스트를 가져온다 --> 변경 
	 * @return
	 */
	public static ApplicationContext getContext() {
		return context;
	}
}
