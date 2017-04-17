package com.android.log;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Lw {
    // 日志类型标识符(优先级：由低到高排列，取值越小优先级越高)
    private static final char SHOW_VERBOSE_LOG = 0x01;//0000 0001
    private static final char SHOW_DEBUG_LOG = 0x02;//0000 0010	0x01 << 1
    private static final char SHOW_INFO_LOG = 0x04;//0000 0100	0x01 << 2
    private static final char SHOW_WARN_LOG = 0x08;//0000 1000	0x01 << 3
    private static final char SHOW_ERROR_LOG = 0x16;//0001 0000	0x01 << 4
    private static final char SHOW_NONE_LOG = 0x32;//0000 0000
    private static final int SDCARD_LOG_FILE_SAVE_DAYS = 2;
    private static final boolean _APP_ROUTE = false;
   
    /*
     * 逻辑运算:
     * 
     * !;&&;||;();
     * 
     * 
     * Java/C/Pascal位运算:
     * 
     * 按位与		&		相同位的两个数字都为1，则为1；若有一个不为1，则为0。 
     * 					and运算通常用于二进制取位操作，例如一个数 and 1的结果就是取二进制的最末位。
     * 					这可以用来判断一个整数的奇偶，二进制的最末位为0表示该数为偶数，最末位为1表示该数为奇数。
     * 按位或		|		相同位只要一个为1即为1。
     * 					or运算通常用于二进制特定位上的无条件赋值，例如一个数or 1的结果就是把二进制最末位强行变成1。
     * 					如果需要把二进制最末位变成0，对这个数or 1之后再减一就可以了，其实际意义就是把这个数强行变成最接近的偶数。
     * 按位异或	^		相同位不同则为1，相同则为0。
     * 					xor运算的逆运算是它本身，也就是说两次异或同一个数最后结果不变，即（a xor b) xor b = a。
     * 					xor运算可以用于简单的加密
     * 按位取反	~
     * 左移		<<		a shl b的值实际上就是a乘以2的b次方，因为在二进制数后添一个0就相当于该数乘以2
     * 带符号右移	>> 		a shr b表示二进制右移b位（去掉末b位），相当于a除以2的b次方（取整）。比如二分查找、堆的插入操作等等。
     * 无符号右移	>>>
     * 
     * C语言中位运算符之间，按优先级顺序排列为
     * 1 ~
     * 2 <<、>>
     * 3 &
     * 4 ^
     * 5 |
     * 6 &=、^=、|=、<<=、>>=
     */
    /**
     * 获取TAG
     */
    private static final String POINT = ":";
    private static final String SEPARATOR = " ";
    private static String TAG = "v160624";
    private static Context ctx;
    // 默认为五种日志类型均在 LogCat 中输出显示
    private static char m_cLogCatShowLogType = SHOW_VERBOSE_LOG |
            SHOW_DEBUG_LOG |
            SHOW_INFO_LOG |
            SHOW_WARN_LOG |
            SHOW_ERROR_LOG;// 0001 1111
    // 默认为五种日志类型均在 日志文件 中输出保存
    //public static char m_cFileSaveLogType = 0;
    // 以下注释不要删除，以便日后开启指定日志类型输出到日志文件中
//    public static char m_cFileSaveLogType =
//    										SHOW_NONE_LOG	 |
//    										SHOW_VERBOSE_LOG |
//                                            SHOW_DEBUG_LOG   |
//                                            SHOW_INFO_LOG    |
//                                            SHOW_WARN_LOG    |
//                                            SHOW_ERROR_LOG;
    // App 名称
    private static String m_strAppName;
    // 存放日志文件的目录全路径
    private static String m_strLogFolderPath = Environment.getExternalStorageDirectory() + "/_temp/log";
    private static String m_strLogName = "custom.txt";
    // 得到当前日期时间的指定格式字符串
    private static SimpleDateFormat m_sdfFileName = new SimpleDateFormat("yyyyMMddHH");// 日志名称格式
    private static String m_strDateTimeFileName = m_sdfFileName.format(new Date());

    public static void init(Context context) {
        // TODOf Auto-generated constructor stub
        ctx = context;
        TAG = ctx.getPackageName();
        SetLogFilePath();
    }

    private static void SetLogFilePath() {
        if (ctx != null) {
            m_strAppName = ctx.getPackageName();
            m_strLogFolderPath = Environment.getExternalStorageDirectory() + "/" +m_strAppName;
        }
        Log.e(TAG, m_strLogFolderPath);
    }

    private static void SaveLog2File(String strMsg) {
        FileWriter objFilerWriter = null;
        BufferedWriter objBufferedWriter = null;

        do // 非循环，只是为了减少分支缩进深度
        {
            String state = Environment.getExternalStorageState();
            // 未安装 SD 卡
            if (true != Environment.MEDIA_MOUNTED.equals(state)) {

                Log.d(TAG, "Not mount SD card!");
                break;
            }

            // SD 卡不可写
            if (true == Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                Log.d(TAG, "Not allow write SD card!");
                break;
            }

            // 只有存在外部 SD 卡且可写入的情况下才允许保存日志文件到指定目录路径下
            // 没有指定日志文件存放位置的话，就写到默认位置，即 SD 卡根目录下的 custom_android_log 目录中
            // 指定日志文件保存的路径，文件名由内部按日期时间形式
            m_strLogFolderPath = m_strLogFolderPath.trim();
            if (true == m_strLogFolderPath.equals("")) {
                m_strLogFolderPath = Environment.getExternalStorageDirectory() +
                        "/icar/log";
            }

            File fileSaveLogFolderPath = new File(m_strLogFolderPath);
            // 保存日志文件的路径不存在的话，就创建它
            if (true != fileSaveLogFolderPath.exists()) {
                fileSaveLogFolderPath.mkdirs();
            }

            // 如果这里保存日志文件的路径还不存在的话，则要提醒用户了
            if (true != fileSaveLogFolderPath.exists()) {
                Log.d(TAG, "Create log folder failed!");
                break;
            }


            m_strLogName = /*m_sAppName+*/m_strDateTimeFileName + ".custom.txt";
            File fileLogFilePath = new File(m_strLogFolderPath, m_strLogName);
            // 如果日志文件不存在，则创建它
            if (true != fileLogFilePath.exists()) {
                try {
                    fileLogFilePath.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

            // 如果执行到这步日志文件还不存在，就不写日志到文件了
            if (true != fileLogFilePath.exists()) {
                Log.d(TAG, "Create log file failed!");
                break;
            }

            try {
                objFilerWriter = new FileWriter(fileLogFilePath, //
                        true);          // 续写不覆盖
            } catch (IOException e1) {
                Log.d(TAG, "New FileWriter Instance failed");
                e1.printStackTrace();
                break;
            }

            objBufferedWriter = new BufferedWriter(objFilerWriter);

            // 得到当前日期时间的指定格式字符串
            String strDateTimeLogHead = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date());

            // 将日期时间头与日志信息体结合起来
            strMsg = /*TAG + " " + */strDateTimeLogHead + " " + strMsg + "\n";

            try {
                objBufferedWriter.write(strMsg);
                objBufferedWriter.flush();
            } catch (IOException e) {
                Log.d(TAG, "objBufferedWriter.write or objBufferedWriter.flush failed");
                e.printStackTrace();
            }

        } while (false);

        if (null != objBufferedWriter) {
            try {
                objBufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != objFilerWriter) {
            try {
                objFilerWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void v(String msg) {
        printLog(SHOW_VERBOSE_LOG, msg);
    }

    public static void v(String tag, String msg) {
        printLog(SHOW_VERBOSE_LOG, tag, msg);
    }

    public static void d(String msg) {
        printLog(SHOW_DEBUG_LOG, msg);
    }

    public static void d(String tag, String msg) {
        printLog(SHOW_DEBUG_LOG, tag, msg);
    }

    public static void i(String msg) {
        printLog(SHOW_INFO_LOG, msg);
    }

    public static void i(String tag, String msg) {
        printLog(SHOW_INFO_LOG, tag, msg);
    }

    public static void w(String msg) {
        printLog(SHOW_WARN_LOG, msg);
    }

    public static void w(String tag, String msg) {
        printLog(SHOW_WARN_LOG, tag, msg);
    }

    public static void e(String msg) {
        printLog(SHOW_ERROR_LOG, msg);
    }

    public static void e(String tag, String msg) {
        printLog(SHOW_ERROR_LOG, tag, msg);
    }

    //外部调用。
    public static void printLog(int level, String msg) {
        // 空字符串或全是空格的字符串做为日志输出没有意义！
        if ((null == msg) || (true == msg.trim().equals(""))) {
            return;
        }
        if (0 != (level & m_cLogCatShowLogType))//如果level在LogType里
        {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            String tag = getDefaultTag(stackTraceElement);
            msg = getLogInfo(stackTraceElement) + msg;
            Log.i(tag, msg);
            SaveLog2File(msg);
        }

    }

    //L打印写入的log
    private static void printLog(int level, String tag, String msg) {
        // 空字符串或全是空格的字符串做为日志输出没有意义！
        if ((null == msg) || (true == msg.trim().equals(""))) {
            return;
        }
        if (0 != (level & m_cLogCatShowLogType)) {
            msg = tag + msg;
            SaveLog2File(msg);
        }

    }

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
        }
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();

        //logInfoStringBuilder.append("[");
        //logInfoStringBuilder.append(APP_NAME).append(POINT);
        logInfoStringBuilder.append(className);
        logInfoStringBuilder.append(POINT).append(methodName);
        //logInfoStringBuilder.append("]");
        //[com.liushaobo.app.MainActivity.SetUI]
        return logInfoStringBuilder.toString();
    }

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

        String date = new SimpleDateFormat("MM-dd HH:mm:ss.S").format(new Date());
        logInfoStringBuilder.append(date).append(SEPARATOR);
        logInfoStringBuilder.append(threadName).append(SEPARATOR);
        logInfoStringBuilder.append(threadID).append(SEPARATOR);
        return logInfoStringBuilder.toString();
    }

    /**
     * 删除内存下过期的日志，在app中判断 每天调用一次删除log
     */
    private static void deleteSDcardExpiredLog() {
        File file = new File(m_strLogFolderPath);
        if (file.isDirectory()) {
            File[] allFiles = file.listFiles();
            for (File logFile : allFiles) {
                String fileName = logFile.getName();
                //Lr.v(fileName);
                if (m_strLogName.equals(fileName)) {
                    continue;
                }
                String createDateInfo = getFileNameWithoutExtension(fileName);
                if (canDeleteSDLog(createDateInfo)) {
                    logFile.delete();
                    Lw.v(logFile.getAbsolutePath() + " delete success.");
                }
            }
        }
    }


    /**
     * 判断sdcard上的日志文件是否可以删除
     *
     * @param createDateStr
     * @return
     */


    private static boolean canDeleteSDLog(String createDateStr) {


        boolean canDel = false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1 * SDCARD_LOG_FILE_SAVE_DAYS);// 删除7天之前日志
        Date expiredDate = calendar.getTime();

        try {
            Date createDate = m_sdfFileName.parse(createDateStr);

            canDel = createDate.before(expiredDate);
        } catch (ParseException e) {

            canDel = false;
        }
        return canDel;
    }

    /**
     * 去除文件的扩展类型（.log）
     *
     * @param fileName
     * @return
     */
    private static String getFileNameWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.indexOf("."));
    }
}