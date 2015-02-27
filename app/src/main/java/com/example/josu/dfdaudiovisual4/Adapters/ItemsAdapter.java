package com.example.josu.dfdaudiovisual4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.josu.dfdaudiovisual4.Models.Items;
import com.example.josu.dfdaudiovisual4.R;
import com.example.josu.dfdaudiovisual4.ViewHolders.ViewHolderItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josué on 31/01/2015.
 */
public class ItemsAdapter extends ArrayAdapter<Items>{

    private Context context;
    private List<Items> datos;

    public ItemsAdapter(Context context, ArrayList<Items> objects){
        super(context, R.layout.oval_adapter, objects);
        this.context = context;
        this.datos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;
        ViewHolderItems holder;

        Items items = datos.get(position);

        if(currentView == null){
            LayoutInflater inflater = LayoutInflater.from(context);

            if(items.getName().equals("ovalo")){
                currentView = inflater.inflate(R.layout.oval_adapter, parent, false);
            }else{
                currentView = inflater.inflate(R.layout.rectangle_adapter, parent, false);
            }

            holder = new ViewHolderItems();
            holder.operation = (TextView) currentView.findViewById(R.id.textView_Operation);
            currentView.setTag(holder);
        }

        holder = (ViewHolderItems) currentView.getTag();
        holder.operation.setText(items.getText());
        return currentView;

    }
}
