package com.example.appbanlinhkien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanlinhkien.R;
import com.example.appbanlinhkien.model.Category;

import java.util.List;

public class CatergoryAdapter extends BaseAdapter {
    List<Category> array;
    Context context;

    public CatergoryAdapter(Context context, List<Category> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView textproductname;
        ImageView imgview;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_product, null);
            viewHolder.textproductname = view.findViewById(R.id.item_productname);
            viewHolder.imgview = view.findViewById(R.id.item_image);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
            viewHolder.textproductname.setText(array.get(i).getProductname());
            Glide.with(context).load(array.get(i).getImage()).into(viewHolder.imgview);
        }
        return view;
    }
}
