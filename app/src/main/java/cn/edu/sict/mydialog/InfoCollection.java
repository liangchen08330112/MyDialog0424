package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InfoCollection extends AppCompatActivity {

    RadioGroup radioGroup;
    CheckBox checkBox_watching, checkBox_playing, checkBox_eating,checkBox_loving;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_collection);
        radioGroup = findViewById(R.id.radioGroup);
        checkBox_watching = findViewById(R.id.checkBox_watching);
        checkBox_playing = findViewById(R.id.checkBox_playing);
        checkBox_eating = findViewById(R.id.checkBox_eating);
        checkBox_loving = findViewById(R.id.checkBox_loving);
        button = findViewById(R.id.button);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.radioButton_male){
                    Toast.makeText(InfoCollection.this, "您选中了：男性", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InfoCollection.this, "您选中了：女性", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            String results = "";
            @Override
            public void onClick(View view) {
                if(checkBox_playing.isChecked()==true){
                    results += "打王者 ";
                }
                if(checkBox_watching.isChecked()==true){
                    results += "追剧 ";
                }
                if(checkBox_loving.isChecked()==true){
                    results += "泡妞 ";
                }
                if(checkBox_eating.isChecked()==true){
                    results += "干饭 ";
                }
                Toast.makeText(InfoCollection.this, "您喜欢"+results, Toast.LENGTH_SHORT).show();
            }
        });
    }
}