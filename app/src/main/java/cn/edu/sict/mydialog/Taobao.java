package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import java.util.ArrayList;

public class Taobao extends AppCompatActivity {

    private GridView gridView;
    //初始化原始数据
    int[] pictures = {R.drawable.p603_img01,R.drawable.p603_img02,R.drawable.p603_img03,
            R.drawable.p603_img04,R.drawable.p603_img05,R.drawable.p603_img06,R.drawable.p603_img07,
            R.drawable.p603_img08,R.drawable.p603_img09,R.drawable.p603_img10};
    String[] texts = {"天猫商城","今日爆款","天猫国际","天猫农场","天猫超市","充值中心","机票酒店","领淘金币",
            "阿里拍卖","淘宝吃货"};

    ArrayList<GridItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        gridView = findViewById(R.id.gridView);
        //数据初始化
        initData();
        //创建GridAdapter对象
        GridAdapter adapter = new GridAdapter(this,dataList);
        //将adapter绑定到GridView
        gridView.setAdapter(adapter);
    }

    //数据初始化，将原始数据填充到dataList中
    private void initData() {
        dataList = new ArrayList<>();
        for(int i=0;i<pictures.length;i++){
            GridItem item = new GridItem(pictures[i],texts[i]);
            dataList.add(item);
        }
    }
}