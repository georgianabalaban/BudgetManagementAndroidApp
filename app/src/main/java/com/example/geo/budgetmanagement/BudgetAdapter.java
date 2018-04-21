package com.example.geo.budgetmanagement;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by geo on 24.11.2017.
 */

public class BudgetAdapter extends ArrayAdapter<Budget> {

    private int idLayout;
    private List<Budget> lista;



    public BudgetAdapter(@NonNull Context context, @LayoutRes int resource, List<Budget> lista) {
        super(context, resource, lista);
        idLayout=resource;
        this.lista=lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(idLayout,parent,false);

        TextView salary=(TextView)view.findViewById(R.id.salariuGrid);
        TextView mancare=(TextView)view.findViewById(R.id.mancareGrid);
        TextView transport=(TextView)view.findViewById(R.id.transportGrid);
        TextView casnic=(TextView)view.findViewById(R.id.casnicGrid);

        Budget b=lista.get(position);
        salary.setText(""+b.getSalary());
        mancare.setText(""+b.getChmancare());
        transport.setText(""+b.getChtransport());
        casnic.setText(""+b.getChcasnice());
        return view;
    }

   /* public void erase(int position){
        lista.remove(position);
    }*/
}
