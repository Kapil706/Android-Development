package com.example.kapil.okhttpnetworking;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// here in this I will use the OkHttp for networking and fetching the data from the github api through the url

// now OkHttp why ? because it reduces the line of codes // HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
//
//OkHttp is an HTTP client that’s efficient by default:
//
//    HTTP/2 support allows all requests to the same host to share a socket.
//    Connection pooling reduces request latency (if HTTP/2 isn’t available).
//    Transparent GZIP shrinks download sizes.
//    Response caching avoids the network completely for repeat requests.
//
//OkHttp perseveres when the network is troublesome: it will silently recover from common connection problems. If your service has multiple IP addresses OkHttp will attempt alternate addresses if the first connect fails. This is necessary for IPv4+IPv6 and for services hosted in redundant data centers. OkHttp initiates new connections with modern TLS features (SNI, ALPN), and falls back to TLS 1.0 if the handshake fails.
//
//Using OkHttp is easy. Its request/response API is designed with fluent builders and immutability. It supports both synchronous blocking calls and async calls with callbacks.

// I have added dependency for OkHttp in build (module)


// one more thing I am using here GSON now for JSON code
// added the dependency in build.grade and have pojo class apiResult


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etName);


        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetextView();
            }
        });
        }

    private void updatetextView() {
        String getUrl = "https://api.github.com/search/users?q=" + editText.getText().toString();

        makeNetworkCall(getUrl);

        }
    void makeNetworkCall(String url) {

        // make the client call
        OkHttpClient client = new OkHttpClient();

        // now requesting the url
        Request request = new Request.Builder().url(url).build();

        // now there are two methods for newCall request
        // one is synchronous which mainly runs on UI thread and other is asynchronous which runs on another thread
        // so we use asynchronous here

        client.newCall(request).enqueue(new Callback() {
            // onfailure will execute when there is no response
            @Override
            public void onFailure(Call call, IOException e) {
                // you can show a toast here
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // this method does not run on mainUI Thread
                String jsonResult = response.body().string();
              //  ArrayList<GithubUser> users = parseJson(jsonResult);
// make a new GSON object
                Gson gson = new Gson();
                // make object of api Result and getting json data and converting it into java
                ApiResult apiResult = gson.fromJson(jsonResult, ApiResult.class);

             //   final GithubUserAdapter githubUserAdapter = new GithubUserAdapter(users);
                final GithubUserAdapter githubUserAdapter = new GithubUserAdapter(apiResult.getItems());
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // the code here runs on main Ui Thread
                        RecyclerView recyclerView = findViewById(R.id.rvUsers);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerView.setAdapter(githubUserAdapter);

                    }
                });
            }
        });
    }
  // by the way there is no need of this code I am commenting this but you first try JSON then go for GSON
//            ArrayList<GithubUser> parseJson(String s) {
//                ArrayList<GithubUser> githubUsers = new ArrayList<>();
//                // Parse the JSON here
//                // first of all create JSON object the root one
//
//                try {
//                    JSONObject root = new JSONObject(s);
//                    // now I need JsonArray from the json object
//                    // passing key as items
//                    JSONArray items = root.getJSONArray("items");
//
//                    // loop over the array
//
//                    for (int i = 0; i < items.length(); i++) {
//                        // This jsonObject is now for particular user
//                        JSONObject jsonObject = items.getJSONObject(i);
//                        // getting the key value pairs
//                        String login = jsonObject.getString("login");
//                        Integer id = jsonObject.getInt("id");
//                        String Avatar = jsonObject.getString("avatar_url");
//                        Double score = jsonObject.getDouble("score");
//                        String html = jsonObject.getString("html_url");
//
//                        GithubUser githubUser = new GithubUser(login, id, html, score, Avatar);
//
//                        githubUsers.add(githubUser);
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                return githubUsers;
            }


