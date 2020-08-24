package com.example.coyotedev.testproj

import android.databinding.Observable
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.coyotedev.testproj.adapter.PostsAdapter
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.fragment.CommentsFragment
import com.example.coyotedev.testproj.mvvm.viewmodel.MainActivityVM
import com.google.gson.GsonBuilder

class MainActivity : FragmentActivity() {

    private lateinit var m_recyclerView: RecyclerView
    private lateinit var m_swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var m_placeholderTextView: TextView
    private lateinit var m_viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        m_viewModel = MainActivityVM(this.applicationContext)
        m_recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = PostsAdapter(emptyList())
        }
        m_recyclerView.addItemDecoration(DividerItemDecoration(m_recyclerView.context, DividerItemDecoration.VERTICAL))
        m_swipeRefreshLayout = findViewById(R.id.swipeContainer)
        m_swipeRefreshLayout.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                m_viewModel.update()
            }
        })
        m_placeholderTextView = findViewById(R.id.mainActivity_placeholderTextView)

        m_viewModel.m_posts.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                try {
                    val list = (sender as ObservableField<List<UserPost>>).get()
                    if (list!!.isNotEmpty())
                    {
                        m_recyclerView.visibility = View.VISIBLE
                        m_placeholderTextView.visibility = View.GONE
                        val postAdapter = PostsAdapter(list)
                        postAdapter.setDelegateOnUserPostClick(object: PostsAdapter.DelegateOnUserPostClick {
                            override fun onClick(post: UserPost) {
                                // create and fill up fragment and show it up
                                val frag = CommentsFragment()
                                val gson = GsonBuilder().create()
                                val bundle = Bundle()
                                bundle.putString(frag.KEY_DATA, gson.toJson(post))
                                frag.arguments = bundle
                                val ft = supportFragmentManager.beginTransaction()
                                ft.add(R.id.mainActivity_fragmentContainer, frag)
                                ft.addToBackStack(null)
                                ft.commit()
                            }
                        })
                        m_recyclerView.swapAdapter(postAdapter, true)
                    } else {
                        m_recyclerView.visibility = View.GONE
                        m_placeholderTextView.visibility = View.VISIBLE
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        })
        m_viewModel.m_isLoading.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                try {
                    val isLoading = (sender as ObservableField<Boolean>).get()
                    m_swipeRefreshLayout.isRefreshing = isLoading!!
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        })
    }
}
