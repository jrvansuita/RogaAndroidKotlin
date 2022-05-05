package com.example.rogaandroidkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rogaandroidkotlin.R
import com.example.rogaandroidkotlin.model.Comment


class CommentAdapter(private val data:List<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {


    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(comment: Comment) {
            val name = view.findViewById<TextView>(R.id.name)
            val email = view.findViewById<TextView>(R.id.email)
            val body = view.findViewById<TextView>(R.id.body)

            name.text = comment.name
            email.text = comment.email
            body.text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size;
    }
}
