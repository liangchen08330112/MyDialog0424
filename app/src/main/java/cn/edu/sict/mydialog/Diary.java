package cn.edu.sict.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Diary extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_date;
    private EditText editText_data;
    private Button button_save;
    private Button button_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        editText_data = (EditText) findViewById(R.id.editText_data);
        editText_date = (EditText) findViewById(R.id.editText_date);
        button_save = (Button) findViewById(R.id.button_save);
        button_read = (Button) findViewById(R.id.button_read);

        button_save.setOnClickListener(this);
        button_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String date = editText_date.getText().toString();
        if(TextUtils.isEmpty(date)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("请输入日期");
            builder.setCancelable(true);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        switch (view.getId()){
            case R.id.button_save:
                String data = editText_data.getText().toString().trim();
                if(TextUtils.isEmpty(data)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("提示");
                    builder.setMessage("请输入内容");
                    builder.setCancelable(true);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                }
                try {
                    //创建一个I/O流
                    FileOutputStream outputStream = openFileOutput(date,MODE_PRIVATE);
                    //使用文件输出流的write方法，将String字符串转化成的byte[]数组写入文件中
                    byte[] bytes = data.getBytes();
                    outputStream.write(bytes);
                    //关闭流
                    outputStream.flush(); //刷新
                    outputStream.close();
                    Toast.makeText(Diary.this,"内容已保存",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.button_read:
                try {
                    //新建一个FileInputStream，并传入date日期
                    FileInputStream inputStream = openFileInput(date);
                    //新建一个byte数组
                    byte[] bytes = new byte[1024];
                    //读取
                    inputStream.read(bytes);
                    //定义一个String对象，并传入byte
                    String string = new String(bytes);
                    //将刚才保存的内容展示出来
                    editText_data.setText(string);
                    //关闭流
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}