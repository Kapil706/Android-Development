package com.example.kapil.beach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kapil.beach.adapter.PostlistAdapter;
import com.example.kapil.beach.rest.model.PostList;

public class BlogActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        //RecyclerView ka object banaya
        recyclerView = findViewById(R.id.post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    public void getData()
    {
        Repository.getInstance().fetchBlogsList(new Repository.FetchBlogListListener() {
            @Override
            public void onListFetched(PostList postLists) {
                recyclerView.setAdapter(new PostlistAdapter(BlogActivity.this, postLists.getItems()));
            }

            @Override
            public void onError(String error) {
                Toast.makeText(BlogActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
