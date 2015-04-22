package com.wxz.freecard.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.wxz.freecard.CardApplication;
import com.wxz.freecard.constant.Constant;

public class FileUtil
{
    public static File getSplashFile()
    {
        File file = CardApplication.getInstance().getFilesDir();
        file = new File(file.getAbsolutePath()+ Constant.SPLASH_FILE_PATH);
        if (!file.exists())
        {
            file.mkdirs();
            return null;
        }
        else
        {
            
        }
        return file;
    }
    
    public static void saveSplashFile(InputStream is,String fileName) throws IOException
    {
        File file = getSplashFile();
        if (file.exists())
        {
            file.delete();
        }
        file.createNewFile();
        BufferedInputStream bis = new BufferedInputStream(is);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 4];
        int read = 0;
        int length = 0;
        while ((length = bis.read(buffer, read, buffer.length)) != -1)
        {
            fos.write(buffer,read,length);
            read += length;
        }
        fos.flush();
        fos.close();
        bis.close();
    }
}
