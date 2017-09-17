package com.example.sakshi.esamadhan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sakshi.esamadhan.utils.PrefsHelper;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    EditText searchView;
    String searchtext;
    List<search> resultList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (EditText) findViewById(R.id.searchid);
        listView = (ListView)findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search obj=resultList.get(position);
                Intent intent =new Intent(SearchActivity.this,DetailedDoc.class);
                intent.putExtra("object",obj);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.update_menu) {

        }
        if (id == R.id.logout_menu) {

        }

        return super.onOptionsItemSelected(item);
    }

    public void onRegtnClick(View view) {
        Intent intent = new Intent(SearchActivity.this, com.example.sakshi.esamadhan.RegtnTabbedActivity.class);
        startActivity(intent);
    }

    public void onSearchClick(View view) {
        searchtext = searchView.getText().toString();
        Log.e("text ", searchtext);
        if (searchtext.length() > 0) {

            searchapi("http://esamadhan.herokuapp.com/doc/search?id="+searchtext);
        }

    }

    public void searchapi(String uri) {
        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.length() > 2) {
                            resultList = searchJsonParser.parsefeed(response);
                            searchAdapter adapter = new searchAdapter(SearchActivity.this, R.layout.list_layout, resultList);
                            listView.setAdapter(adapter);
                            Toast.makeText(SearchActivity.this, "O/p "+response+"", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(SearchActivity.this, "No result", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError ex) {
                        Toast.makeText(SearchActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue queue;
        queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(request);

    }
}
