package com.android.log;


import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LogUtils工具说明: 
 * 1 只输出等级大于等于LEVEL的日志 
 *   所以在开发和产品发布后通过修改LEVEL来选择性输出日志.
 *   当LEVEL=NOTHING则屏蔽了所有的日志. 
 * 2 v,d,i,w,e均对应两个方法. 
 *   若不设置TAG或者TAG为空则为设置默认TAG
 * 
 */
public class L {

	public static final int VERBOSE = 1;
	public static final int DEBUG = 2;
	public static final int INFO = 3;
	public static final int WARN = 4;
	public static final int ERROR = 5;
	public static final int NOLOG = 6;
	
	private static int LEVEL_PRINTF = VERBOSE;//<LEVEL_PRINTF,打印Log等级
	private static int LEVEL_WRITE = VERBOSE;//>LEVEL_WRITE,写入Log文件等级

	private static final String SEPARATOR = " ";

	/**
	 * 初始化日志，设置打印，写入日志level
	 * @param print 打印日志level
	 * @param write 写入日志level
	 */
	public static void init(int print,int write) {
		// TODO Auto-generated constructor stub
    	LEVEL_PRINTF = print;
    	LEVEL_WRITE = write;
	}

    
	public static void v() {
		printLog(VERBOSE,"","");
	}
	
	public static void v(Object msg) {
		printLog(VERBOSE,msg,"");
	}

	public static void d(Object msg) {
		printLog(DEBUG,msg,"");
	}

	public static void i(Object msg) {
		printLog(INFO,msg,"");
	}

	public static void w(Object msg) {
		printLog(WARN,msg,"");
	}

	public static void e(Object msg) {
		printLog(ERROR,msg,"");
	}

	
	public static void v(String tag, Object msg) {
		printLog(VERBOSE,msg,tag);
	}
	public static void d(String tag, Object msg) {
		printLog(DEBUG,msg,tag);
	}
	public static void i(String tag, Object msg) {
		printLog(INFO,msg,tag);
	}
	public static void w(String tag, Object msg) {
		printLog(WARN,msg,tag);
	}

	public static void e(String tag, Object msg) {
		printLog(ERROR,msg,tag);
	}

	private static void printLog(int level , Object obj, String TAG){
		if (LEVEL_PRINTF > VERBOSE){
			return;
		}
		String msg;
		String tag = TAG;
		StackTraceElement stackTraceElement;
		
		stackTraceElement= Thread.currentThread().getStackTrace()[4];
		
		if(TextUtils.isEmpty(tag)){
			try {
				tag = stackTraceElement.getFileName().split("[.]")[0];
			} catch (NullPointerException e) {
				// TODO: handle exception
				tag = stackTraceElement.getFileName();
			}
		}
		if(obj == null){
			msg = "msg is NULL";
		}else{
			msg = obj.toString();
		}
		if(TextUtils.isEmpty(msg)){
			msg = stackTraceElement.getMethodName();
		}
		
		msg = msg+ " -->at " + stackTraceElement.toString();
		
		switch (level) {
		case VERBOSE:
			Log.v(tag, msg);
			break;
		case DEBUG:
			Log.d(tag, msg);
			break;
		case INFO:
			Log.i(tag, msg);
			break;
		case WARN:
			Log.w(tag, msg);
			break;
		case ERROR:
			Log.e(tag, msg);
			break;
		default:
			break;
		}
		
		if (level >= LEVEL_WRITE) {
			Lw.v(getLogInfo(stackTraceElement), msg);
		}
	}
	
	/**
	 * 获取默认的TAG名称. 
	 * 比如在MainActivity.java中调用了日志输出. 
	 * 则TAG为MainActivity
	 */
	private static String getDefaultTag2(StackTraceElement stackTraceElement) {
		String fileName = stackTraceElement.getFileName();
		String stringArray[] = fileName.split("\\.");
		String tag = stringArray[0];
		return tag;
	}

    /**
	 * 获取TAG
	 */
	private static final String POINT = ":";

	private static String getDefaultTag(StackTraceElement stackTraceElement) {
		StringBuilder logInfoStringBuilder = new StringBuilder();

		// 获取类名.即包名+类名
		String className = stackTraceElement.getClassName();
		// 获取类名
		String fileClass = "";
		try {
			fileClass = stackTraceElement.getFileName().split("[.]")[0];
		} catch (NullPointerException e) {
			// TODO: handle exception
			fileClass = stackTraceElement.getFileName();
		}
		// 获取方法名称
		String methodName = stackTraceElement.getMethodName();

		//logInfoStringBuilder.append("[");
		//logInfoStringBuilder.append(APP_NAME).append(POINT);
		logInfoStringBuilder.append(className);
		logInfoStringBuilder.append(POINT).append( methodName);
		//logInfoStringBuilder.append("]");
		//[com.liushaobo.app.MainActivity.SetUI]
		return logInfoStringBuilder.toString();
	}
	
	/**
	 * 输出日志所包含的信息
	 */
	private static String getLogInfo(StackTraceElement stackTraceElement) {
		StringBuilder logInfoStringBuilder = new StringBuilder();
		// 获取线程名
		String threadName = Thread.currentThread().getName();
		// 获取线程ID
		long threadID = Thread.currentThread().getId();
		// 获取文件名.即xxx.java
		String fileName = stackTraceElement.getFileName();
		// 获取类名.即包名+类名
		String className = stackTraceElement.getClassName();
		// 获取方法名称
		String methodName = stackTraceElement.getMethodName();
		// 获取生日输出行数
		int lineNumber = stackTraceElement.getLineNumber();
		String date = new SimpleDateFormat( "MM-dd HH:mm:ss.S" ).format( new Date() );
		//logInfoStringBuilder.append(date).append(SEPARATOR);
		logInfoStringBuilder.append(threadName).append(SEPARATOR);
		logInfoStringBuilder.append(threadID).append(SEPARATOR);
		return logInfoStringBuilder.toString();
	}
	
}