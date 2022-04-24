package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView textView_result;
    private TextView textView_title;
    private CheckBox checkBox_playing;
    private CheckBox checkBox_English;
    private CheckBox checkBox_programming;
    private String hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        textView_title = findViewById(R.id.textView_title);
        textView_result = findViewById(R.id.textView_result);
        checkBox_playing = findViewById(R.id.checkBox_playing);
        checkBox_English = findViewById(R.id.checkBox_English);
        checkBox_programming = findViewById(R.id.checkBox_programming);
        hobbies = new String();

        checkBox_playing.setOnCheckedChangeListener(this);
        checkBox_English.setOnCheckedChangeListener(this);
        checkBox_programming.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String motion = compoundButton.getText().toString();
        if(b){
            if(!hobbies.contains(motion)){
                hobbies = hobbies+motion;
                textView_result.setText("你选择了："+hobbies);
            }
        }else{
            if(hobbies.contains(motion)){
                hobbies = hobbies.replace(motion," ");
                textView_result.setText("你选择了："+hobbies);
            }
        }
    }
}