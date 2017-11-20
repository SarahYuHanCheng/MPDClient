package com.example.sarahcheng.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
public class Main3Activity extends AppCompatActivity {

    private ListView mList;
    private ArrayList<String> arrayList;
    private MyCustomAdapter mAdapter;
    private TCPClient mTcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        arrayList = new ArrayList<String>();

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button send = (Button)findViewById(R.id.send_button);
        Button play_btn = (Button)findViewById(R.id.button_play);

        //relate the listView from java to the one created in xml
        mList = (ListView)findViewById(R.id.list);
        mAdapter = new MyCustomAdapter(this, arrayList);
        mList.setAdapter(mAdapter);

        // connect to the server
        new connectTask().execute("");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String message = editText.getText().toString();

                //add the text in the arrayList
                arrayList.add("c: " + "pause");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("pause");
                }

                //refresh the list
                mAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //add the text in the arrayList
                arrayList.add("c: " + "play");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage("play");
                }

                //refresh the list
                mAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }
    public class connectTask extends AsyncTask<String,String,TCPClient> {
        @Override
        protected TCPClient doInBackground(String... message) {
//we create a TCPClient object and
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
//here the messageReceived method is implemented
                public void messageReceived(String message) {
//this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
//in the arrayList we add the messaged received from server
            arrayList.add(values[0]);
// notify the adapter that the data set has changed. This means that new message received
// from server was added to the list
            mAdapter.notifyDataSetChanged();
        }
    }
}
