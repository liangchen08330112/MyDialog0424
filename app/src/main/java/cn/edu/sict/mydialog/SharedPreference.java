package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.SharedPreferencesCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SharedPreference extends AppCompatActivity implements View.OnClickListener {
    //初始化控件
    TextView textView;
    EditText editText_username;
    Button button_save, button_refresh, button_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        //利用findViewById()绑定控件
        textView = findViewById(R.id.textView);
        editText_username = findViewById(R.id.editText_username);
        button_save = findViewById(R.id.button_save);
        button_refresh = findViewById(R.id.button_refresh);
        button_read = findViewById(R.id.button_read);
        //对按钮绑定点击事件监听器
        button_save.setOnClickListener(this);
        button_refresh.setOnClickListener(this);
        button_read.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        //新建preference对象
        SharedPreferences preference = getPreferences(Context.MODE_PRIVATE);
        switch (view.getId()){
            //点击保存按钮后，会自动保存数据，并弹出保存成功的提示。
            case R.id.button_save:
                String name = editText_username.getText().toString().trim();
                //如果信息为空...
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this,"用户名不得为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                //新建editor对象
                SharedPreferences.Editor editor = preference.edit();
                editor.putString("name",name);
                editor.commit();
                Toast.makeText(this,"数据保存成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_read:
                //点击读取按钮，会读取保存过的数据
                String username = preference.getString("name","未设置");
                editText_username.setText(username);
                break;
            case R.id.button_refresh:
                //点击刷新按钮，清空输入框中的数据
                editText_username.setText("");
                break;
        }
    }
}