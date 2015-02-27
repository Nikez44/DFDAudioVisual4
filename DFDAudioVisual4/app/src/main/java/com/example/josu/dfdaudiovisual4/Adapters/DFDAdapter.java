package com.example.josu.dfdaudiovisual4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.josu.dfdaudiovisual4.Models.DFD;
import com.example.josu.dfdaudiovisual4.R;
import com.example.josu.dfdaudiovisual4.ViewHolders.ViewHolderDFD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josué on 31/01/2015.
 */
public class DFDAdapter extends ArrayAdapter<DFD> {

    private Context context;
    private List<DFD> datos;

    public DFDAdapter(Context context, ArrayList<DFD> objects){
        super(context, R.layout.dfd_adapter, objects);
        this.context = context;
        this.datos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;
        ViewHolderDFD holder;

        if(currentView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            currentView = inflater.inflate(R.layout.dfd_adapter, parent, false);
            holder = new ViewHolderDFD();
            holder.name = (TextView) currentView.findViewById(R.id.textView_Nombre);
            currentView.setTag(holder);
        }

        holder = (ViewHolderDFD) currentView.getTag();
        DFD dfd = datos.get(position);
        holder.name.setText(dfd.getName());
        return currentView;

    }
}


