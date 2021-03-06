package com.sunys.core.run.impl.interceptor;

import com.sunys.core.run.RunContext;
import com.sunys.facade.run.RunChain;
import com.sunys.facade.run.MethodInterceptor;

/**
 * ContextMethodInterceptor
 * @author sunys
 * @date Dec 21, 2019
 */
public class ContextMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(RunChain runChain) throws Exception {
		Object target = runChain.getTarget();
		//设置当前线程运行的run接口
		RunContext.pushRun(target);
		try {
			//运行目标方法
			Object obj = runChain.invoke();
			return obj;
		} finally {
			//run接口运行结束后移除
			RunContext.popRun();
		}
	}

}
