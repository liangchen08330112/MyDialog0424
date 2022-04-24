package cn.edu.sict.mydialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;  //上下文对象，从构造函数中接收赋值
    ArrayList<GridItem> dataList;  //数据列表

    //构造函数：创建GridAdapter对象的同时为成员变量赋初值
    public GridAdapter(Context context, ArrayList<GridItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    //一共有多少数据
    @Override
    public int getCount() {
        return dataList.size();
    }

    //获取一条数据
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    //获取一条数据的id号
    @Override
    public long getItemId(int position) {
        return position;
    }

    //获取一个小格的填充view
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //根据xml小格布局，加载到内存，获取一个View对象
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.griditem_taobao,null);
        //找到View对象中的控件
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        //获取列表中的该条数据，填充到小布局生成的view中
        GridItem item = dataList.get(position);
        imageView.setImageResource(item.getPictures());
        textView.setText(item.getTexts());
        //返回view
        return view;
    }
}
