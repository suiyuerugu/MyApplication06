package com.example.wxj.myapplication06;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Map;
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> dataList;

    public MyAdapter(Context context, List<Map<String , Object>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)

    {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);//将定义好
            //的list_item.xml文件提取成view实例用来显示.convertview从布局文件中加载单个item的布局
            viewHolder.ivMenu = (ImageView) convertView.findViewById(R.id.img_menu);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvMajorMaterial = (TextView) convertView.findViewById(R.id
                    .tv_major_material);
            viewHolder.tvMinorMaterial = (TextView) convertView.findViewById(R.id
                    .tv_minor_material);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.cbPick = (CheckBox) convertView.findViewById(R.id.cb_pick);
            convertView.setTag(viewHolder);
            //首先我们要知道setTag方法是干什么的，他是给View对象的一个标签，标签可以是任何内容，
            // 我们这里把他设置成了一个对象，因为我们是把list_item.xml的元素抽象出来成为一个
            // 类ViewHolder，用了setTag，这个标签就是ViewHolder实例化后对象的一个属性。
            // 我们之后对于ViewHolder 实例化的对象viewholder的操作，
            // 都会因为java的引用机制而一直存活并改变convertView的内容，而不是每次都是去new
            // 一个。我们就这样达到重用。
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivMenu.setImageResource((int) dataList.get(position).get("img"));
        viewHolder.tvTitle.setText((String) dataList.get(position).get("title"));
        viewHolder.tvMajorMaterial.setText((String) dataList.get(position).get("major_material"));
        viewHolder.tvMinorMaterial.setText((String) dataList.get(position).get("minor_material"));
        viewHolder.tvPrice.setText((String) dataList.get(position).get("price"));
        viewHolder.cbPick.setChecked((boolean) dataList.get(position).get("pick"));

        viewHolder.cbPick.setOnCheckedChangeListener(new MyOnCheckedChangeListener(position));
        return convertView;
    }

    private class ViewHolder {
        ImageView ivMenu;
        TextView tvTitle;
        TextView tvMajorMaterial;
        TextView tvMinorMaterial;
        TextView tvPrice;
        CheckBox cbPick;
    }


    private class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private int postion;
        public MyOnCheckedChangeListener(int position) {
            this.postion=position;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Toast.makeText(context,"信息:"+postion+":"+b,Toast.LENGTH_LONG).show();
        }
    }
}
