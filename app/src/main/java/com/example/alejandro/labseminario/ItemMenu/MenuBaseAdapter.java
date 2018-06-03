package com.example.alejandro.labseminario.ItemMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandro.labseminario.R;

import java.util.ArrayList;
import java.util.List;

public class MenuBaseAdapter extends BaseAdapter implements OnLoadCompleImg {

    private Context context;
    private ArrayList<ItemMenuStructure> list;
    private ArrayList<TextView>counter;

    public MenuBaseAdapter (Context context, ArrayList<ItemMenuStructure> list){
        this.list = list;
        this.context = context;
        counter = new ArrayList<TextView>();
    }


    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemfood, null);
        }
        TextView txt1 = convertView.findViewById(R.id.txtview2);
        TextView txt2 = convertView.findViewById(R.id.textView3);
        ImageView img = convertView.findViewById(R.id.imageView2);
        txt1.setText(this.list.get(position).getFoodname());
        txt2.setText(this.list.get(position).getQuantity()+"");
        counter.add(txt2);
        if (this.list.get(position).getImg()==null){
            LoaderImg loader = new LoaderImg();
            loader.setOnloadCompleteImg(img, position, this);
            loader.execute(this.list.get(position).getUrlimg());
        }else{
            img.setImageBitmap(this.list.get(position).getImg());
        }

        return convertView;
    }

    @Override
    public void OnLoadCompleImgResult(ImageView img, int position, Bitmap imgsourceimg) {
        this.list.get(position).setImg(imgsourceimg);
        img.setImageBitmap(imgsourceimg);
    }
}
