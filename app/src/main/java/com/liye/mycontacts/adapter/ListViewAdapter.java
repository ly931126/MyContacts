package com.liye.mycontacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liye.mycontacts.R;

import java.util.List;
import java.util.Map;
/*
侧滑菜单字条目名字的适配器
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> listitem;

    public ListViewAdapter(Context context, List<Map<String, Object>> listitem) {
        this.context = context;
        this.listitem = listitem;
    }

    @Override
    public int getCount() {
        return listitem.size();
    }

    @Override
    public Object getItem(int position) {
        return listitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_leftview, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView textView = (TextView) convertView.findViewById(R.id.txt_title);

        Map<String, Object> map = listitem.get(position);
        imageView.setImageResource((Integer) map.get("image"));
        textView.setText(map.get("title") + "");
        //textView.setTextColor(R.color.white);
        return convertView;
    }


}

