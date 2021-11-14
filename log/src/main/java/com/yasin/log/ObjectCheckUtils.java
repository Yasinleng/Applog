package com.yasin.log;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 */
public class ObjectCheckUtils {

    public static void checkInit(Object object) {
        if (object == null) {
            throw new RuntimeException("init First !!!");
        }
    }

    public static void checkNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }
}
