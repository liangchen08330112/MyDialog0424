package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Opening extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        textView = findViewById(R.id.textView);

        //新建preference对象
        SharedPreferences preferences = getSharedPreferences("countPreferences", Context.MODE_PRIVATE);
        //定义一个count的int值，并将初始值设为0
        int count = preferences.getInt("count",0);
        //每打开一次，count自增一次。
        count++;
        //将打开页面次数转为静态文本，并显示在页面上
        textView.setText("您第"+count+"次打开了本页面");

        //新建editor对象，并调用preferences的edit()方法
        SharedPreferences.Editor editor = preferences.edit();
        //调用putInt()方法传入页面被打开的次数
        editor.putInt("count",count);
        //提交
        editor.commit();
    }
}