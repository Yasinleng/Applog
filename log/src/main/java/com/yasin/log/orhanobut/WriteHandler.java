package com.yasin.log.orhanobut;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/11/13.
 */
public class WriteHandler extends Handler {

    private String diskPath;
    private String fileName;
    private int maxFileSize;


    public WriteHandler(Looper looper, String diskPath, String fileName, int maxFileSize) {
        super(looper);
        this.diskPath = diskPath;
        this.fileName = fileName;
        this.maxFileSize = maxFileSize;

    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        String content= (String) msg.obj;
        FileWriter fileWriter=null;
        File logFile=getLogFile(diskPath,fileName);
        try {
            fileWriter=new FileWriter(logFile,true);
            writeLog(fileWriter,content);
            fileWriter.flush();
        } catch (IOException e) {
            if (fileWriter!=null){
                try {
                    fileWriter.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            e.printStackTrace();

        }finally {
            try {
                if (fileWriter!=null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    protected void writeLog(FileWriter fileWriter, String content) {
        try {
            fileWriter.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected File getLogFile(String folderName, String fileName) {
        File folder = new File(folderName);
        if (!folder.exists()) folder.mkdirs();
        int newFileCount = 0;
        File newFile = null;
        File existFile = null;
        newFile = new File(folder, String.format("%s_%s.csv", fileName, newFileCount));
        while (newFile.exists()) {
            existFile = newFile;
            newFileCount++;
            newFile = new File(folder, String.format("%s_%s.csv", fileName, newFileCount));
        }
        if (existFile != null && existFile.length() < maxFileSize) {
            return existFile;
        } else {
            return newFile;
        }

    }
}
