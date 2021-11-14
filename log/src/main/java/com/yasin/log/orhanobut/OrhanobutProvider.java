package com.yasin.log.orhanobut;

import android.os.HandlerThread;
import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.DiskLogStrategy;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.yasin.log.LogConfig;
import com.yasin.log.api.LogProvider;


/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/11/12.
 */
public class OrhanobutProvider implements LogProvider {

    private String globalTag;

    public OrhanobutProvider(LogConfig config) {
        this.globalTag = config.getTag();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy()));
        if (!TextUtils.isEmpty(config.getDiskPath())) {
            HandlerThread writer = new HandlerThread("AppFileLogger" + config.getDiskPath());
            writer.start();

            WriteHandler handler = new WriteHandler(writer.getLooper()
                    , config.getDiskPath()
                    , config.getFileName()
                    , config.getMaxFileSize());
            DiskLogStrategy logStrategy = new DiskLogStrategy(handler);

            FormatStrategy diskLogStrategy = CsvFormatStrategy.newBuilder()
                    .tag(config.getTag())
                    .logStrategy(logStrategy)
                    .build();
            Logger.addLogAdapter(new DiskLogAdapter(diskLogStrategy));

        }
    }

    protected FormatStrategy strategy() {
        return PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(6)
                .methodOffset(0)
                .logStrategy(new LogcatLogStrategy())
                .tag(globalTag)
                .build();
    }


    @Override
    public void d(String tag, String msg) {
        Logger.d("[%s] %s", tag, msg);
    }

    @Override
    public void v(String tag, String msg) {
        Logger.v("[%s] %s", tag, msg);

    }

    @Override
    public void i(String tag, String msg) {
        Logger.i("[%s] %s", tag, msg);

    }

    @Override
    public void w(String tag, String msg) {
        Logger.w("[%s] %s", tag, msg);

    }

    @Override
    public void w(String tag, String msg, Throwable t) {
        Logger.w("[%s] %s, throwable: %s", tag, msg, t.getMessage());
        t.printStackTrace();
        Logger.log(Logger.WARN, globalTag, "[$tag] $msg", t);
    }

    @Override
    public void e(String tag, String msg) {
        Logger.e("[%s] %s", tag, msg);
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        Logger.e(t, "[%s] %s", tag, msg);
    }
}
