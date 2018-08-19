package com.github.wei.jtrace.core.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.wei.jtrace.agent.IAdvice;

/**
 * 切面入口
 * @author wei-8
 *
 */
public final class Advisor {
	private static Logger log = LoggerFactory.getLogger("Advisor");
	
	protected volatile static AdviceManager adviceManager;
	
	public static IAdvice onMethodBegin(Class<?> ownClass, Object own, String methodName, String methodDescr, Object[] matcherMessages) {
		if(log.isDebugEnabled()) {
			log.debug("Advisor begin {}.{}{}", ownClass.getName(), methodName, methodDescr);
		}
		
		if(adviceManager != null) {
			return adviceManager.createAdvice(ownClass, own, methodName, methodDescr, matcherMessages);
		}
		return new DummyAdvice();
	}
	

}
