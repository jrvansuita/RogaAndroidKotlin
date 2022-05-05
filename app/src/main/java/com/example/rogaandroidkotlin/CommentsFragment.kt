package com.example.rogaandroidkotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rogaandroidkotlin.adapter.CommentAdapter
import com.example.rogaandroidkotlin.adapter.PostAdapter
import com.example.rogaandroidkotlin.api.Api
import com.example.rogaandroidkotlin.model.Comment
import com.example.rogaandroidkotlin.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val POST_TITLE = "POST_TITLE";
private const val POST_ID = "POST_ID";

class CommentsFragment : DialogFragment() {

    private lateinit var commentsRecycleView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var commentAdapter: RecyclerView.Adapter<*>

    private fun loadComments(postId:Int) {
        Api.retrofitService.getComments(postId).enqueue(object: Callback<List<Comment>> {

            override fun onResponse(
                call: Call<List<Comment>>,
                response: Response<List<Comment>>
            ) {
                if(response.isSuccessful){

                    val postTitle = requireArguments().getString(POST_TITLE)

                    var title = view?.findViewById<TextView>(R.id.title);
                    title?.text = getString(R.string.comments_about_post, postTitle)

                    commentsRecycleView = view?.findViewById<RecyclerView>(R.id.recycler_view)
                        ?.apply{
                            commentAdapter = CommentAdapter(response.body()!!)
                            layoutManager = manager
                            adapter = commentAdapter
                        }!!
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.comments_fragment, container, false)
        manager = LinearLayoutManager(activity)

        val postId = requireArguments().getInt(POST_ID)
        loadComments(postId)

        return view
    }


    companion object{
        fun newInstance(post: Post) : CommentsFragment{
            val newFragment = CommentsFragment()

            val bundle = Bundle()
            bundle.putInt(POST_ID, post.id)
            bundle.putString(POST_TITLE, post.title)
            newFragment.arguments = bundle

            return newFragment
        }
    }
}