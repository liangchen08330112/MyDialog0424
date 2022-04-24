package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteTest extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_name, editText_number;
    private Button button_insert, button_delete, button_update, button_retrieve;

    //定义SQLite数据库对象database
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);
        initView();

        //【创建数据库、数据表】
        //1.创建路径为“/当前目录/db/”的数据库“sqltest.db”
        database = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+
                "/sqltest.db",null);
        /**
         * 2.在数据库中打开或创建数据表users，以name列为主键。
         * 表中有两列，一列是name，另一列是number，这两列的数据类型为varchar(20)
         * varchar，是一种可变长度的字符串。varchar(20)指该字符串最长不超过20字符。
         */
        database.execSQL("create table if not exists users(name varchar(20)," +
                "number varchar(20),primary key(name))");

    }

    private void initView() {
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_number = (EditText) findViewById(R.id.editText_number);
        button_insert = (Button) findViewById(R.id.button_insert);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_update = (Button) findViewById(R.id.button_update);
        button_retrieve = (Button) findViewById(R.id.button_retrieve);

        button_insert.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_update.setOnClickListener(this);
        button_retrieve.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //获取用户输入的姓名和学号
        String name = editText_name.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入姓名",Toast.LENGTH_SHORT).show();
            return;
        }
        String number = editText_number.getText().toString().trim();
        switch (view.getId()){
            //增加用户
            case R.id.button_insert:
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this,"请输入姓名",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    database.execSQL("insert into users(name,number) values(?,?)",
                            new String[]{name, number});
                    Toast.makeText(this,"添加成功", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this,"添加失败", Toast.LENGTH_SHORT).show();
                }
                break;
                //删除用户
            case R.id.button_delete:
                try{
                    database.execSQL("delete from users where name=?",new String[]{name});
                    Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
                }
                break;
                //修改学号
            case R.id.button_update:
                //确认输入了学号
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(this,"请输入学号",Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    database.execSQL("update users set number=? where name=?",
                            new String[]{number, name});
                    Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
                }
                break;
                //根据姓名查询学号
            case R.id.button_retrieve:
                try{
                    //预先设置学号框显示的内容
                    number = "用户不存在";
                    //新建一个结果集游标Crusor
                    Cursor cursor = database.rawQuery("select number from users where name=?",
                            new String[]{name});
                    //使用while循环遍历结果每一行
                    while(cursor.moveToNext()){ //游标从当前行移动至下一行
                        number = cursor.getString(0);  //获取该行第1列的值（该列的值为String类型）
                    }
                    cursor.close();//关闭游标
                    editText_number.setText(number);
                    Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this,"查询失败",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //关闭数据库
    @Override
    protected void onDestroy(){
        database.close();
        super.onDestroy();
    }
}