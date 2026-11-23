package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ImagineCafea> lista;

    public ImagineAdapter(Context context, int layout, List<ImagineCafea> lista) {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layout, parent, false);

        ImageView imagineIV = v.findViewById(R.id.imagineIV);
        TextView textTV = v.findViewById(R.id.textTV);

        ImagineCafea item = (ImagineCafea) getItem(position);

        if (item.getImagine() != null) {
            imagineIV.setImageBitmap(item.getImagine());
        }
        textTV.setText(item.getTextAfisat());

        return v;
    }
}