package com.example.kapil.jsonapicalling;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetextView();
            }
        });
    }

    private void updatetextView() {

        //  TextView textView = findViewById(R.id.textView);
// creating an object for network task
        NetworkTask networkTask = new NetworkTask();
        // in execute function arguments string url must go
        networkTask.execute("https://api.github.com/search/users?q=harshit ");

    }

    class NetworkTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            // strings[0]here signify the first array position for the string url

            String stringUrl = strings[0];
            // you can not make internet call through string object You must have an URL object to make internet or API call
// you have to handle URL in try catch bcz there may be some problem with string url
            try {
                URL url = new URL(stringUrl);
                // make a connection with this URL by doing openConnection
                url.openConnection();
                // store the instance of URl connection
                // reason is we want to get data
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();

                // to get data in chunks we use Scanner

                Scanner scanner = new Scanner(inputStream);
                // we use Delimiter bcz it will allow us to read entire inputStream in one go
                scanner.useDelimiter("\\A");

                if (scanner.hasNext()) {

                    String s = scanner.next();

                    // this return statement passed to onPostExecute method
                    return s;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
// this return will be called when try catch failed to execute

            return "failed to load";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // calling parseJson here
            ArrayList<GithubUser> users =parseJson( s);
            Log.e(TAG,"onPostExecute" +users.size());

            // set the adapter here
            GithubUserAdapter githubUserAdapter = new GithubUserAdapter(users);
            RecyclerView recyclerView = findViewById(R.id.rvUsers);
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            recyclerView.setAdapter(githubUserAdapter);
            // commenting this bcz i have to do something else
//            TextView textView = findViewById(R.id.textView);
//            textView.setText(s);
        }
    }
    // Now I will make a function parseJSON whose return type is array list of githubUser
    // it will take json String
    ArrayList<GithubUser>parseJson(String s){

        ArrayList<GithubUser> githubUsers = new ArrayList<>();
        // Parse the JSON here
        // first of all create JSON object the root one

        try {
            JSONObject root = new JSONObject(s);
            // now I need JsonArray from the json object
                     // passing key as items
            JSONArray items = root.getJSONArray("items");

            // loop over the array

            for (int i =0 ; i<items.length();i++){
                   // This jsonObject is now for particular user
                JSONObject jsonObject = items.getJSONObject(i);
                     // getting the key value pairs
                String login = jsonObject.getString("login");
                Integer id = jsonObject.getInt("id");
                String Avatar = jsonObject.getString("avatar_url");
                Double score = jsonObject.getDouble("score");
                String html = jsonObject.getString("html_url");

                GithubUser githubUser = new GithubUser(login, id, html,score,Avatar);

                githubUsers.add(githubUser);

            }

            } catch (JSONException e) {
            e.printStackTrace();
        }
                 return githubUsers;
        }

}