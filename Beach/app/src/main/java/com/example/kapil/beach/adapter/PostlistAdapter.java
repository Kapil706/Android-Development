package com.example.kapil.beach.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kapil.beach.DetailActivity;
import com.example.kapil.beach.R;
import com.example.kapil.beach.rest.model.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public class PostlistAdapter extends RecyclerView.Adapter<PostlistAdapter.PostlistViewHolder> {

    private Context context;
    private List<Item> items;

    public PostlistAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    //Yeh function Sabse pehle hume jo views chahiye honge usko create karega aur store karke rakhega, uske saath hi yeh Refrences bhi store kar leta hain
    //Jisse hume baar baar find view by ID call na karna pade
    @NonNull
    @Override
    public PostlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //View crete karne ke liye hum ek class ka use karenge jo hoti hain layoutInflater
        //yeh class jo bhi views defined hotein hain humari layout file ke andar uske corresponding hume ek view object return karti hain
        LayoutInflater inflater = LayoutInflater.from(context);  //LayoutInflater ka object mil gaya
        //Ab iski madad se hum yaha view create karenge
        View view = inflater.inflate(R.layout.post_item,parent,false);  //Yaha par post_item = list_item

        //Ab hume apna View Holder Create karna hain
        return new PostlistViewHolder(view);

    }

    //Ab humare views create ho chuke hain toh ab yeh humare/fetched data ke saath views ko bind kar dega
    @Override
    public void onBindViewHolder(@NonNull PostlistViewHolder postlistViewHolder, int position) {
        //Yeh humara post hain jo hume bind karna hain
        final Item item = items.get(position);
        postlistViewHolder.postTitle.setText(item.getTitle());
        //Parse ke andar hum HTML content pass kartein hain
        Document document = Jsoup.parse(item.getContent());

        postlistViewHolder.postDescription.setText(document.text());

        //Ab image humari HTML mein hain usko nikaalne ke liye aur content fetch karene ke liye JSOUP use karenge



        //Ab iss document se hum kuch bhi nikaal saktein hain using HTML elements
        Elements elements = document.select("img"); //Yaha TAG select kiya

        Glide.with(context).load(elements.get(0).attr("src")).into(postlistViewHolder.postImage);

        postlistViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, DetailActivity.class);
                //URL of the item(post) is sent to NextActivity
                i.putExtra("url", item.getUrl());
                context.startActivity(i);
            }
        });

    }

    //Yeh function batata hain ki jo data hume show karna hain uska item count kitna hain
    @Override
    public int getItemCount() {
        //return 0;
        return items.size();
    }

    public class PostlistViewHolder extends RecyclerView.ViewHolder{
        //Refrences Yaha daal saktein hain
        ImageView postImage;
        TextView postTitle;
        TextView postDescription;

        public PostlistViewHolder(@NonNull View itemView) { //Yeh apna itemView
            super(itemView);
            postImage = itemView.findViewById(R.id.postimage);
            postTitle = itemView.findViewById(R.id.post_title);
            postDescription = itemView.findViewById(R.id.post_body);
        }
    }
}
