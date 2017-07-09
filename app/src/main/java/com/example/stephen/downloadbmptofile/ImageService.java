package com.example.stephen.downloadbmptofile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stephen on 17-7-9.
 */

public class ImageService {
    public static Bitmap getImage(String path){
        byte[] data;
        Bitmap bitmap=null;
        try{
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");   //设置请求方法为GET
            conn.setReadTimeout(5*1000);    //设置请求过时时间为5秒
            InputStream inputStream = conn.getInputStream();   //通过输入流获得图片数据
            data = readInputStream(inputStream);     //获得图片的二进制数据
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图
            return bitmap;
        }catch (IOException e){
            e.printStackTrace();
            Log.d("ImageService","获取网络数据失败！");
            return bitmap;
        }
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
