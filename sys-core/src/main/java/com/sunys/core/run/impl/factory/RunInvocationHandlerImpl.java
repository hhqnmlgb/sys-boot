package com.sunys.core.run.impl.factory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.sunys.core.run.impl.factory.handle.AnnotationHandler;
import com.sunys.core.run.impl.factory.handle.InterceptorAnnotationHandler;
import com.sunys.facade.run.Run;
import com.sunys.facade.run.RunChain;
import com.sunys.facade.run.RunChainImpl;
import com.sunys.facade.run.RunInvocationHandler;
import com.sunys.facade.run.RunMethodInterceptor;

/**
 * RunInvocationHandlerImpl
 * @author sunys
 * @date Dec 21, 2019
 */
public class RunInvocationHandlerImpl implements RunInvocationHandler {

	private Run run;
	private Map<Method, List<RunMethodInterceptor>> interceptorsMap;
	private AnnotationHandler handler = new InterceptorAnnotationHandler(this);

	public RunInvocationHandlerImpl(Run run) throws Exception {
		this.run = run;
		init();
	}

	private void init() throws Exception {
		handler.handle(run.getClass());
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		List<RunMethodInterceptor> interceptors = interceptorsMap.get(method);
		//创建方法执行链
		RunChain runChain = new RunChainImpl(interceptors, run, (Run) proxy, method, args);
		//执行拦截器和目标方法
		Object obj = runChain.invoke();
		return obj;
	}

	@Override
	public void setInterceptorsMap(Map<Method, List<RunMethodInterceptor>> interceptorsMap) {
		this.interceptorsMap = interceptorsMap;
	}

}