package com.example.coyotedev.testproj.fragment

import android.databinding.Observable
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coyotedev.testproj.R
import com.example.coyotedev.testproj.adapter.CommentsAdapter
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.data.UserPostComment
import com.example.coyotedev.testproj.mvvm.viewmodel.CommentsFragmentVM
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class CommentsFragment(): Fragment() {

    val KEY_DATA: String = "CommentsFragmentKeyData"
    private lateinit var m_recyclerView: RecyclerView
    private lateinit var m_viewModel: CommentsFragmentVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        run {
            val gson = GsonBuilder().create()
            val data = arguments?.get(KEY_DATA) as String
            val post = gson.fromJson(data, object: TypeToken<UserPost>(){}.type) as UserPost
            m_viewModel = CommentsFragmentVM(context!!, post)
        }
        m_recyclerView = view.findViewById<RecyclerView>(R.id.fragmentComments_recycleView).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = CommentsAdapter(emptyList())
        }
        m_recyclerView.addItemDecoration(DividerItemDecoration(m_recyclerView.context, DividerItemDecoration.VERTICAL))
        m_viewModel.m_comments.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                try {
                    val list = (sender as ObservableField<List<UserPostComment>>).get()
                    m_recyclerView.swapAdapter(CommentsAdapter(list!!), true)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        })
        m_viewModel.update()
    }
}