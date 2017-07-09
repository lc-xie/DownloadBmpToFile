package com.example.stephen.downloadbmptofile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stephen on 17-7-9.
 */

public class SaveImageToLocalFIle {

    //保存bitmap到fileName，文件名为bitmapName.
    public static boolean saveBitmap(String filePath,String bitmapName,Bitmap bitmap){
        FileOutputStream outputStream=null;
        try{
            File file = new File(filePath, bitmapName);
            if (!file.exists())file.createNewFile();
            //打印文件路径名
            Log.d("MainActivity:",file.getPath());

            outputStream = new FileOutputStream(file);
            // 将图像写到流中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
