package com.android.http;

public interface YAsyncListener {
	/**
	 * 当请求成功的时候执行该方法
	 * 
	 * @param values
	 */
	public void onComplete(String values);

	/**
	 * 发生异常的时候执行该方法
	 * 
	 * @param exceptionInfo
	 */
	public void onException(Object exceptionInfo);
}
