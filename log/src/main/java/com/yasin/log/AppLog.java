package com.yasin.log;


/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 *
 * 需要添加库：com.orhanobut:logger:2.2.0
 *
 */
public class AppLog implements LogProvider {

    private static LogProvider provider;

    private AppLog() {
    }

    @Override
    public void d(String tag, String msg) {
        ObjectCheckUtils.checkInit(provider);
        provider.d(tag, msg);

    }

    @Override
    public void v(String tag, String msg) {
        ObjectCheckUtils.checkInit(provider);
        provider.v(tag, msg);

    }

    @Override
    public void i(String tag, String msg) {
        ObjectCheckUtils.checkInit(provider);
        provider.i(tag, msg);

    }

    @Override
    public void w(String tag, String msg) {
        ObjectCheckUtils.checkInit(provider);
        provider.w(tag, msg);

    }

    @Override
    public void w(String tag, String msg, Throwable t) {
        ObjectCheckUtils.checkInit(provider);
        provider.w(tag, msg, t);

    }

    @Override
    public void e(String tag, String msg) {
        ObjectCheckUtils.checkInit(provider);
        provider.e(tag, msg);
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        ObjectCheckUtils.checkInit(provider);
        provider.e(tag, msg, t);

    }

    private static class SingletonInstance {
        private static final AppLog instance = new AppLog();
    }

    public static AppLog getInstance() {
        return SingletonInstance.instance;
    }

    public static void init(LogProvider logProvider) {
        provider = logProvider;
    }

}
