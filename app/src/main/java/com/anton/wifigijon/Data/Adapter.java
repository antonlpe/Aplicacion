package com.anton.wifigijon.Data;

/**
 * Created by Antón on 13/05/2017.
 *
 * Adapter personalizado para mostrar una lista con imagen y descripción
 * y almacenar latitud y longitud para pasarle al mapa
 */

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anton.wifigijon.R;



public class Adapter extends BaseAdapter {
    static class ViewHolder {
        public TextView mItemName;
    }

    private final List<Items> mItems;
    public LayoutInflater mInflater;


    public Adapter(Context context, List<Items> items) {

        if (context == null || items == null ) {
            throw new IllegalArgumentException();
        }

        this.mItems = items;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return mItems.size();
    }

    @Override
    public Object getItem(int position) {

        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;
        if (rowView == null) {
            rowView = mInflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mItemName = (TextView) rowView.findViewById(R.id.nameTextView);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Items item = (Items) getItem(position);
        viewHolder.mItemName.setText(item.getNombre());
        return rowView;
    }

}
