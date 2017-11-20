package com.example.sarahcheng.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.server_list_view);
        String[] items ={"control MPD server","settings","Server3"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item,
                android.R.id.text1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent myintent = new Intent(view.getContext(),Main3Activity.class);
                    startActivityForResult(myintent,0);
                }
                if (position==1){
                    Intent myintent = new Intent(view.getContext(),Main3Activity.class);
                    startActivityForResult(myintent,0);
                }
                if (position==2){
                    Intent myintent = new Intent(view.getContext(),Main3Activity.class);
                    startActivityForResult(myintent,0);
                }

            }
        });


    }
}
