package com.example.rogaandroidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rogaandroidkotlin.adapter.PostAdapter
import com.example.rogaandroidkotlin.adapter.PostAdapterListener
import com.example.rogaandroidkotlin.api.Api
import com.example.rogaandroidkotlin.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), PostAdapterListener {
    private lateinit var postsRecycleView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var postAdapter: RecyclerView.Adapter<*>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.recent_posts)
        manager = LinearLayoutManager(this)
        loadPosts()
    }

     private fun loadPosts() {
         Api.retrofitService.getPosts().enqueue(object: Callback<List<Post>>{

             override fun onResponse(
                 call: Call<List<Post>>,
                 response: Response<List<Post>>
             ) {
                 if(response.isSuccessful){
                     postsRecycleView = findViewById<RecyclerView>(R.id.recycler_view).apply{
                         postAdapter = PostAdapter(this@MainActivity, response.body()!!)
                         layoutManager = manager
                         adapter = postAdapter
                     }
                 }
             }

             override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                 t.printStackTrace()
             }
         })
     }

    override fun onClick( post:Post) {
        val transaction = supportFragmentManager.beginTransaction()
        val newFragment = CommentsFragment.newInstance(post)
        newFragment.show(transaction, "showComments")

    }
}