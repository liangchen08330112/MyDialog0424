package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class List extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);
        //定义一个数组
        String array[]={"应用电子系","工商管理系","会计系","信息技术系","马克思主义学院"};
        //定义一个ArrayAdapter，并传入三个参数
        //android.R.layout.simple_list_item_1为Android提供。
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,array);
        //将adapter绑定至列表
        listView.setAdapter(adapter);
        //对列表每一项绑定单击事件监听器
        listView.setOnItemClickListener(this);
    }

    //单击事件方法
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //使用switch语句分支
        switch (position){
            //对学校的其中五个院系绑定监听，点击后弹出对话框。
            case 0:
                Toast.makeText(this,"应用电子系",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"工商管理系",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"会计系",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"信息技术系",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"马克思主义学院",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}