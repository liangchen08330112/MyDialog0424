package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        //判断点击了后退键还是菜单键
        //这样会使后退键失效，点击后退键只会弹出Toast提示，不会真正返回上一页。
        if(keyCode == KeyEvent.KEYCODE_BACK){ //方法一：直接keyCode比较
            Toast.makeText(MainActivity3.this,"你点击了后退键",Toast.LENGTH_SHORT).show();
            return true;
        }else if(event.getKeyCode() == KeyEvent.KEYCODE_MENU){ //方法二：从event中取出keyCode
            Toast.makeText(MainActivity3.this,"你点击了菜单键",Toast.LENGTH_SHORT).show();
            return true;
            //由于现在菜单键几乎绝迹，现在只放代码，笔记中的程序演示不再提示。
        }
        return super.onKeyDown(keyCode,event);
    }
}