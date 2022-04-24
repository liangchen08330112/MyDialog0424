package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Words extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_word, editText_explanation, editText_search;
    private Button button_insert, button_search, button_showAll;
    private LinearLayout LinearLayout;
    //定义数据库
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        initView();
        //建立数据库和数据表
        database = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
                +"/sqltest.db",null);
        /**
         * 2.在数据库中打开或创建数据表words，以word列为主键。
         * 表中有两列，一列是word，另一列是intro，这两列的数据类型为varchar(20)
         * varchar，是一种可变长度的字符串。varchar(20)指该字符串最长不超过20字符。
         */
        database.execSQL("create table if not exists words(word varchar(20)," +
                "intro varchar(20),primary key(word))");
    }
    private void initView(){
        editText_word = (EditText) findViewById(R.id.editText_word);
        editText_explanation = (EditText) findViewById(R.id.editText_explanation);
        editText_search = (EditText) findViewById(R.id.editText_search);
        button_insert = (Button) findViewById(R.id.button_insert);
        button_search = (Button) findViewById(R.id.button_search);
        button_showAll = (Button) findViewById(R.id.button_showAll);
        LinearLayout = (LinearLayout) findViewById(R.id.LinearLayout);

        button_insert.setOnClickListener(this);
        button_search.setOnClickListener(this);
        button_showAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //插入
            case R.id.button_insert:
                //定义word变量，接收用户输入并转化成字符串
                String word = editText_word.getText().toString().trim();
                //如果单词一栏输入为空：
                if(TextUtils.isEmpty(word)){
                    Toast.makeText(this,"您没有输入单词",Toast.LENGTH_SHORT).show();
                    return;
                }
                //定义explanation变量，接收用户输入并转化成字符串
                String explanation = editText_explanation.getText().toString().trim();
                //如果解释一栏输入为空
                if(TextUtils.isEmpty(explanation)){
                    Toast.makeText(this,"您没有输入解释",Toast.LENGTH_SHORT).show();
                    return;
                }
                //捕获异常，即便出现了异常，程序也不会崩溃。
                try{
                    //执行数据库中的插入语句，接收用户输入的单词和解释。
                    database.execSQL("insert into words(word,intro) values(?,?)",
                            new String[]{word,explanation});
                    Toast.makeText(this,"生词添加成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    //如果程序出现异常，会提示生词添加失败。
                    Toast.makeText(this,"生词添加失败",Toast.LENGTH_SHORT).show();
                }
                break;
                //搜索单个单词的解释
            case R.id.button_search:
                //接收用户输入，并转化成字符串
                String search = editText_search.getText().toString().trim();
                //搜索栏为空
                if(TextUtils.isEmpty(search)){
                    Toast.makeText(this,"您没有输入关键字",Toast.LENGTH_SHORT).show();
                    return;
                }
                //初定义结果变量，显示初始文本内容：未搜索到结果
                String result = "未搜索到结果";
                //定义一个结果集游标
                Cursor cursor = database.rawQuery("select * from words where word=?"
                        ,new String[]{search});
                //循环读取下一行
                while (cursor.moveToNext()){
                    //获取第二列内容，第一列是生词，第二列是解释。
                    result = cursor.getString(1);
                }
                //利用吐司提示框展示解释
                Toast.makeText(this,"解释："+result,Toast.LENGTH_SHORT).show();
                break;
                //展示所有生词
            case R.id.button_showAll:
                //定义结果集游标
                Cursor cursor_show = database.rawQuery("select * from words",null);
                //循环读取下一行
                while(cursor_show.moveToNext()){
                    //定义一个静态文本对象
                    TextView textView = new TextView(this);
                    //在页面上生成静态文本
                    textView.setText("生词："+cursor_show.getString(0)+"的解释是"+
                            cursor_show.getString(1));
                    //添加静态文本至页面
                    LinearLayout.addView(textView);
                }
                break;
        }
    }
    @Override
    protected void onDestroy(){
        //关闭数据库，防止资源浪费
        database.close();
        super.onDestroy();
    }
}