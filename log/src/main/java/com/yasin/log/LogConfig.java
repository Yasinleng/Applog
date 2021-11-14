package com.yasin.log;

import android.content.Context;
import android.text.TextUtils;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/11/14.
 */
public class LogConfig {

    private String tag;
    private String diskPath;
    private String fileName;
    private int maxFileSize;
    private Context context;


    public LogConfig(Builder builder) {
        this.tag = builder.tag;
        this.diskPath = builder.diskPath;
        this.fileName = builder.fileName;
        this.maxFileSize = builder.maxFileSize;
        this.context = builder.context;

    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public String getDiskPath() {
        return diskPath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTag() {
        return tag;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {

        private String tag;
        private String diskPath;
        private String fileName;
        private Context context;
        private int maxFileSize = 500 * 1024;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(LogConfig config) {
            this.tag = config.tag;
            this.diskPath = config.diskPath;
            this.fileName = config.fileName;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder diskPath(String diskPath) {
            this.diskPath = diskPath;
            return this;

        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;

            return this;
        }

        public Builder maxFileSize(int maxFileSize) {
            this.maxFileSize = maxFileSize;
            return this;
        }

        public LogConfig build() {
            if (TextUtils.isEmpty(tag)) {
                tag = "log";
            }

            if (TextUtils.isEmpty(diskPath)) {
                diskPath = context.getExternalFilesDir("AppLog").getPath();
            }

            if (TextUtils.isEmpty(fileName)) {
                fileName = "appLog";
            }

            return new LogConfig(this);
        }
    }

}
