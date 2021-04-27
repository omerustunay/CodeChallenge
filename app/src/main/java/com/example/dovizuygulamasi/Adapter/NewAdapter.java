package com.example.dovizuygulamasi.Adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.dovizuygulamasi.Models.ApiModel;
import com.example.dovizuygulamasi.R;
import com.example.dovizuygulamasi.Activity.MainActivity;
import com.example.dovizuygulamasi.Activity.NewActivity;

import java.util.List;

public class NewAdapter extends BaseAdapter {

    //adaptor_view_layout
    //Adaptor icin islemler gerceklenir.
    List<ApiModel> list;
    Context context;
    MainActivity mainActivity;

    public NewAdapter(List<ApiModel> list, Context context, MainActivity mainActivity){
        this.list = list;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder{
        TextView symbol, price, change, txtDescription;
        ImageView img, image;
    }

    //Liste icerisindeki textview lar burada doldurulur.
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        int color = 0;
        ViewHolder holder = null;
        if (view==null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_view_layout,parent,false);

            holder.symbol = (TextView) view.findViewById(R.id.txtSymbol);
            holder.price = (TextView) view.findViewById(R.id.txtPrice);
            holder.change = (TextView) view.findViewById(R.id.txtChange);
            holder.txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            holder.img = (ImageView) view.findViewById(R.id.img);
            holder.image = (ImageView) view.findViewById(R.id.image);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        // SvgLoader ile svg formatli iconlar eklenir.
        // iconurl null ise catch devreye girer.
        iconUpload(list.get(position).getIconUrl(), holder.image);

        // text renklendirme için kullanildi.
        color = colorControl(list.get(position).getColor(), color);

        holder.symbol.setText(list.get(position).getSymbol());
        holder.symbol.setTextColor(color);
        holder.price.setText((list.get(position).getPrice()).substring(0,((list.get(position).getPrice()).indexOf("."))+3));
        holder.change.setText(list.get(position).getChange());
        holder.price.setTextColor(color);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewActivity.class);
                intent.putExtra("symbol", list.get(position).getSymbol());
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("allTimeHigh", list.get(position).getPrice());
                intent.putExtra("description", list.get(position).getDescription());
                intent.putExtra("iconUrl", list.get(position).getIconUrl());
                intent.putExtra("color", list.get(position).getColor());
                intent.putExtra("iconType", list.get(position).getIconType());
                context.startActivity(intent);
            }
        });
        return view;
    }

    private void iconUpload(String url, ImageView image) {

        try{
            SvgLoader.pluck()
                    .with(mainActivity)//Activity yazilir.
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(url, image);
        }catch (IllegalArgumentException e){
            SvgLoader.pluck()
                    .with(mainActivity)//Activity yazilir
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load("http://upload.wikimedia.org/wikipedia/commons/e/e8/Svg_example3.svg", image);
        }
    }

    private int colorControl(String renkKodu, int color){

        try{
            color = Color.parseColor(renkKodu);
        }catch (IllegalArgumentException e){
            color = Color.parseColor("#E53935");
        }
        return color;
    }

}
