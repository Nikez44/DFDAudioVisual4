package com.example.josu.dfdaudiovisual4.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josué on 31/01/2015.
 */
public class Items {

    private int id;
    private String name;
    private String description;
    private String text;

    public Items(JSONObject item){
        try {
            this.id = item.getInt("id");
            this.name = item.getString("name");
            this.description = item.getString("description");
            this.text = item.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}




