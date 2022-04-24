package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_password;
    EditText editText_confirmPassword;
    TextView textView_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_confirmPassword = (EditText) findViewById(R.id.editText_confirmPassword);
        textView_result = (TextView) findViewById(R.id.textView_result);

        //为确认密码框绑定事件监听器。
        editText_confirmPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                //两次密码初始状态为无字符
                String password1 = "";
                String password2 = "";

                if(keyEvent.getAction() == KeyEvent.ACTION_UP){
                    //将两个输入结果转为字符串
                    password1 = editText_password.getText().toString();
                    password2 = editText_confirmPassword.getText().toString();
                    //只能用equals()方法判断两个字符串是否相等
                    //如果使用双等号，则判断两个字符串的地址是否相等，无法达到我们想要的效果。
                    if(password1.equals(password2)){ //如果两次输入的密码相等，则验证通过
                        textView_result.setText("密码验证通过！");
                    }else{  //否则输出“两次输入的密码不一致”
                        textView_result.setText("两次输入的密码不一致！");
                    }
                }
                return false;
            }
        });
    }
}