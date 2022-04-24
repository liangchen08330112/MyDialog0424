package cn.edu.sict.mydialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DbHelper extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_name, editText_number;
    private Button button_insert, button_delete, button_update, button_retrieve;

    MyHelper helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_helper);
        initView();
        helper = new MyHelper(this,"sqltest.db",null,1);
        //获取可读写SQLiteDatabse对象
        database = helper.getWritableDatabase();
    }

    private void initView(){
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
                //创建ContentValues对象
                ContentValues values = new ContentValues();
                //将数据添加到ContentValues对象
                values.put("name",name);
                values.put("number",number);
                long insertID = database.insert("users",null,values);
                if(insertID==-1){
                    Toast.makeText(this,"插入失败",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"插入成功",Toast.LENGTH_SHORT).show();
                }
                break;
            //删除用户
            case R.id.button_delete:
                long deleteLines = database.delete("users","name=?",
                        new String[]{name});
                Toast.makeText(this,"删除"+deleteLines+"条记录",
                        Toast.LENGTH_SHORT).show();
                break;
            //修改学号
            case R.id.button_update:
                //确认输入了学号
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(this,"请输入学号",Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues values2 = new ContentValues();
                values2.put("number",number);
                database.update("number",values2,"number=?",
                        new String[]{"2021083330112"});
                break;
            //根据姓名查询学号
            case R.id.button_retrieve:
                //查询结果存放在Cursor游标中
                Cursor cursor = database.query("users",new String[]{"number"},
                        "name=?"
                        ,new String[]{name},null,null,null,null);
                //如果使用主键查询，只能取出最多一行数据：读取Cursor中的数据
                if(cursor.getCount()==0){
                    editText_number.setText("用户不存在");
                    Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
                    //如果取出多行数据：遍历结果集中的各个行
                }else{
                    //游标从当前行移动到第一行
                    cursor.moveToFirst();
                    //获取该行第2列的值（该列的值为String类型）
                    editText_number.setText(cursor.getString(0));
                }
                //关闭游标
                cursor.close();
                break;
        }
    }
    //关闭数据库
    @Override
    protected void onDestroy(){
        database.close();
        super.onDestroy();
    }
    //创建SQLiteOpenHelper子类MyHelper
    class MyHelper extends SQLiteOpenHelper{
        //新建一个构造函数
        public MyHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            // context：上下文对象
            // name：数据库的名称
            // factory：允许查询数据时返回一个自定义Cursor，一般传入null
            // version：数据库的版本号，可用于对数据库进行升级操作
        }
        //第一次打开时创建数据表
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exists users(name varchar(50)," +
                    "number varchar(50),primary key(name))");
            /**
             * 在数据库中打开或创建数据表users，表中有两列："name"和"number"，这两列的数据类型为varchar(20)
             * varchar是一种可变长度的字符串，varchar(20)表示该字符串最长不超过20字符
             */
        }
        //版本更新时被调用
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        }
    }
}