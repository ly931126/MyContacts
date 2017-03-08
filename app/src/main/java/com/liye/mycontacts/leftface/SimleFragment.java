package com.liye.mycontacts.leftface;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liye.mycontacts.R;
import com.liye.mycontacts.menu.TelephoneActivity;


@SuppressLint("ValidFragment")
public class SimleFragment extends Fragment {
    private View view;
    private ListView mList;
    private String title[];
    private String content[];
private ImageView mRetern;

    private TelephoneActivity activity;
    @SuppressLint("ValidFragment")
    public SimleFragment(TelephoneActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_simle, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRetern=(ImageView)view.findViewById(R.id.star_return);
        mRetern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 按钮按下，将抽屉打开
                activity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mList = (ListView) view.findViewById(R.id.detail);
        mList.setAdapter(new FavouriteAdapter());
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 找到内容的控件
                TextView content = (TextView) view.findViewById(R.id.content);
                // 判断是否可见
                int visible = content.getVisibility();
                /*
				 * VISIBLE:可见（显示，不保留空间） GONE:不可见，隐藏 INVISBLE(可见/显示，保留空间）
				 */
                if (visible == view.VISIBLE) {
                    // 如果可见，设置隐藏
                    content.setVisibility(view.GONE);
                } else {
                    // 不可见，则显示
                    content.setVisibility(view.VISIBLE);
                }
            }
        });
    }

    class FavouriteAdapter extends BaseAdapter {
        public FavouriteAdapter() {
            title = getResources().getStringArray(R.array.titles);
            content = getResources().getStringArray(R.array.contents);
        }
        //得到冷笑话的数量
        @Override
        public int getCount() {
            return title.length;
        }
        // 获取子条目
        @Override
        public Object getItem(int position) {
            return title[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View reserveView, ViewGroup parent) {
            // 将布局转为view
            LayoutInflater in = LayoutInflater.from(getActivity());
            View v = in.inflate(R.layout.item_simle, null);
            TextView titles = (TextView) v.findViewById(R.id.title);
            titles.setText(title[position]);
            titles.setTextColor(Color.BLUE);
            titles.setTextSize(24);
            TextView contents = (TextView) v.findViewById(R.id.content);
            contents.setText(content[position]);
            contents.setTextColor(Color.BLACK);
            contents.setTextSize(18);
            return v;
        }

    }

}




