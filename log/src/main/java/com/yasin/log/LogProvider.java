package com.yasin.log;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 */
public interface LogProvider {

    /**
     * @param tag  日志标签
     * @param msg 日志内容
     */
    void d(String tag, String msg);

    /**
     * @param tag  日志标签
     * @param msg  日志内容
     */
    void v(String tag, String msg);

    /**
     * @param tag  日志标签
     * @param msg  日志内容
     */
    void i(String tag, String msg);

    /**
     * @param tag  日志标签
     * @param msg  日志内容
     */
    void w(String tag, String msg);

    /**
     * @param tag  日志标签
     * @param msg  日志内容
     * @param t  附加的异常对象
     */
    void w(String tag, String msg, Throwable t);

    /**
     * @param tag  日志标签
     * @param msg 日志内容
     */
    void e(String tag, String msg);

    /**
     * @param tag  日志标签
     * @param msg  日志内容
     * @param t  附加的异常对象
     */
    void e(String tag, String msg, Throwable t);

}
