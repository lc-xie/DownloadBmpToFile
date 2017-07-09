package com.example.stephen.downloadbmptofile;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.image);

        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==1){
                    //主线程更新ui
                    imageView.setImageBitmap(bitmap);
                    SaveImageToLocalFIle.saveBitmap(newGetCacheDir(),"pic.png",bitmap);
                    Log.d("Main:","getMsg");
                }
            }
        };
        context=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                bitmap = ImageService.getImage("http://book.meiriyiwen.com/book_imgs/1455522021.jpg");
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }
    /*
    * 获取本地缓存的地址
    * 要获取context，只能放在mainActivity中
    * */
    private String  newGetCacheDir() {
        String state = Environment.getExternalStorageState();
        File dir = null;
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // 有sd卡
            dir = new File(Environment.getExternalStorageDirectory(), "/Android/data/" + context.getPackageName()
                    + "/icon");
        } else {
            // 没有sd卡
            dir = new File(context.getCacheDir(), "/icon");
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath().toString();
    }

}
