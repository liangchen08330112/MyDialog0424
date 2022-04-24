package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQList extends AppCompatActivity {

    //初始化资源
    private ListView listView;
    //资源准备
    int headers[] = {R.drawable.header1,R.drawable.header2,R.drawable.header3,R.drawable.header4,
            R.drawable.header5,R.drawable.header6};
    String names[] = {"隔壁老李","小王","小刘","小孙","小庄","小林"};
    String intros[] = {"我是隔壁老李，有事常联系。","我愿和你陪到天荒地老","过去终究是过去，过去的遗憾不能填补。",
                      "开心度过每一天.","人生在勤，功成不匮","夏日限定."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlist);
        initView();
    }

    private void initView() {
        //找到资源
        listView = findViewById(R.id.listView);
        //转化成List集合数据
        List<Map<String, Object>> userList = new ArrayList<>();
        for(int i=0;i<headers.length;i++){
            HashMap<String,Object> user = new HashMap<>();
            user.put("header",headers[i]);
            user.put("name",names[i]);
            user.put("intro",intros[i]);
            userList.add(user);
        }
        //定义SimpleAdapter对象，并传入我们想要看到的资源
        SimpleAdapter adapter = new SimpleAdapter(QQList.this,
                userList, R.layout.list_item,new String[]{"header","name","intro"},
                new int[]{R.id.imageView,R.id.textView_name,R.id.textView_introduction});
        //将adapter绑定在ListView上
        listView.setAdapter(adapter);
        //对ListView绑定单击事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //取出用户点击的第几条数据
                Map user = (Map) adapterView.getItemAtPosition(i);
                //取出用户名
                String name = (String) user.get("name");
                //提示框
                Toast.makeText(QQList.this,"你选择的是"+name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}