package com.example.josu.dfdaudiovisual4.Actvities;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.josu.dfdaudiovisual4.Adapters.ItemsAdapter;
import com.example.josu.dfdaudiovisual4.Models.DFD;
import com.example.josu.dfdaudiovisual4.Models.Items;
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
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_items)
public class ItemsActivity extends RoboActivity implements TextToSpeech.OnInitListener{

    @InjectView(R.id.listView_Items)
    ListView listView_Items;

    Locale locale = new Locale("es", "ES");

    private TextToSpeech tts;

    private ArrayList<Items> aItems;
    private ItemsAdapter itemsAdapter;
    private OnItemClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts = new TextToSpeech(this, this);

        aItems = new ArrayList<Items>();
        itemsAdapter = new ItemsAdapter(getApplication(), aItems);
        listView_Items.setAdapter(itemsAdapter);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,  new  JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray items = response.getJSONArray("items");

                    try {
                        JSONObject inicio = new JSONObject();
                        inicio.put("id",0);
                        inicio.put("name","ovalo");
                        inicio.put("description","un óvalo que representa el inicio del diagrama de flujo");
                        inicio.put("text","Inicio");
                        Items ini = new Items(inicio);
                        itemsAdapter.add(ini);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    for(int i = 0; i < items.length(); i++){

                        Items item = new Items(items.getJSONObject(i));
                        itemsAdapter.add(item);
                        itemsAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {

                    JSONObject fini = new JSONObject();
                    fini.put("id",100);
                    fini.put("name","ovalo");
                    fini.put("description","un óvalo que representa el final del diagrama de flujo");
                    fini.put("text","Final");
                    Items fin = new Items(fini);
                    itemsAdapter.add(fin);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


        clickListener = new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items item = (Items) listView_Items.getAdapter().getItem(position);
                tts.speak(item.getDescription(), TextToSpeech.QUEUE_FLUSH, null);
            }
        };

        listView_Items.setOnItemClickListener(clickListener);


        listView_Items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Items item = (Items) listView_Items.getAdapter().getItem(position);
                Intent intent = new Intent(getApplication(),DescripcionActivity.class);
                intent.putExtra("descripcion" ,item.getDescription() );
                startActivity(intent);

                return false;
            };
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
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
