package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiFunctions extends AppCompatActivity implements View.OnClickListener {

    //初始化控件
    Button button3,button4,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_functions);
        //设置动态权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},0x11);
        //初始化控件
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        //绑定点击事件监听器
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //新建Intent对象
        Intent intent = new Intent();
        switch (view.getId()){
            //点击拨打电话按钮
            case R.id.button3:
                intent.setAction(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:0531-96190");
                intent.setData(uri);
                startActivity(intent);
                break;
                //点击发送短信按钮
            case R.id.button4:
                intent.setAction(Intent.ACTION_SENDTO);
                Uri uri2 = Uri.parse("smsto:13355458896");
                intent.setData(uri2);
                intent.putExtra("sms","程序测试");
                startActivity(intent);
                break;
                //点击浏览网页按钮
            case R.id.button5:
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri3 = Uri.parse("http://www.baidu.com");
                intent.setData(uri3);
                startActivity(intent);
                break;
                //点击浏览相册按钮
            case R.id.button6:
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivity(intent);
                break;
        }
    }
}