package com.example.kapil.okhttpnetworking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*


// this constructor githubUserAdapter takes arraylist of githubUsers
class GithubUserAdapter(private val githubUsers : ArrayList<GithubUser>): RecyclerView.Adapter<GithubUserAdapter.GithubViewHolder>(){

    // inflating layout in onCreateViewHolder
    //p0 is parent here
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GithubViewHolder = GithubViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_row, p0, false))

    override fun getItemCount(): Int =githubUsers.size
    // here p0 is holder
    // p1 is position
    override fun onBindViewHolder(p0: GithubViewHolder, p1: Int) {
        p0?.bind(githubUsers[p1])
    }


    class GithubViewHolder( itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind (githubUser: GithubUser){
            itemView?.tViewLogin?.text = githubUser.login
            itemView?.tViewScore?.text= githubUser.score.toString()
            itemView?.tViewUrl?.text=githubUser.html_url
                    Picasso.get().load(githubUser.avatar_url).into(itemView?.imageViewProfile)
        }
    }





}