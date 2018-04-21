package com.example.geo.budgetmanagement;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by geo on 05.12.2017.
 */

public class CurrencyAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> lista;
    private final Integer[] imageId;


    public CurrencyAdapter(Activity context, List<String> lista, Integer[] imageId) {
        super(context, R.layout.currency_row, lista);
        this.context = context;
        this.lista = lista;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.currency_row, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtcurrency);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgcurrency);
        String s=lista.get(position);
        txtTitle.setText(s);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
