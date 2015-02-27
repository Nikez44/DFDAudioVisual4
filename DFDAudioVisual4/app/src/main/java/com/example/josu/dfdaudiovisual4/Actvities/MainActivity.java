package com.example.josu.dfdaudiovisual4.Actvities;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.josu.dfdaudiovisual4.Adapters.DFDAdapter;
import com.example.josu.dfdaudiovisual4.Models.DFD;
import com.example.josu.dfdaudiovisual4.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboFragmentActivity implements TextToSpeech.OnInitListener {

    @InjectView(R.id.listView_DFD)
    ListView listView_DFD;

    Locale locale = new Locale("es", "ES");


    private TextToSpeech tts;
    private ArrayList<DFD> DFDs;
    private DFDAdapter dfdAdapter;
    private OnItemClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tts = new TextToSpeech(this, this);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://fathomless-caverns-5697.herokuapp.com/diagrams.json",  new  JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        DFD dfd = new DFD(jsonObject);
                        dfdAdapter.add(dfd);
                        dfdAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_SHORT).show();

            }



        });

        DFDs = new ArrayList<DFD>();
        dfdAdapter = new DFDAdapter(this.getApplicationContext(), DFDs);

        listView_DFD.setAdapter(dfdAdapter);


        listView_DFD.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DFD dfd = (DFD) listView_DFD.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.putExtra("url", dfd.getUrl());
                startActivity(intent);

                return false;
            };
        });

        clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DFD dfd = (DFD) listView_DFD.getAdapter().getItem(position);
                tts.speak(dfd.getName(), TextToSpeech.QUEUE_FLUSH, null);
            }
        };

        listView_DFD.setOnItemClickListener(clickListener);





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
        tts.setLanguage(locale);
    }
}
