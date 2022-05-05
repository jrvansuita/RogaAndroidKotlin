package com.example.rogaandroidkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rogaandroidkotlin.R
import com.example.rogaandroidkotlin.model.Post

class PostAdapter(
    private var listener: PostAdapterListener,
    private val data:List<Post>
    ) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(post: Post, listener: PostAdapterListener) {
            val title = view.findViewById<TextView>(R.id.title)
            val body = view.findViewById<TextView>(R.id.body)

            title.text = post.title
            body.text = post.body

            view.setOnClickListener {
                listener.onClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    override fun getItemCount(): Int {
        return data.size;
    }
}

interface PostAdapterListener{

    fun onClick(post:Post)
}