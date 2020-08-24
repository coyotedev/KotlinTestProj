package com.example.coyotedev.testproj.view

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.coyotedev.testproj.R

class PostView(ctx:Context): LinearLayout(ctx) {

    private var m_textViewTitle: TextView
    private var m_textViewBody: TextView

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.post_view, this, true)

        m_textViewTitle = rootView.findViewById(R.id.postView_title)
        m_textViewBody = rootView.findViewById(R.id.postView_body)
    }

    fun setTitleString(string: String) {
        m_textViewTitle.text = Html.fromHtml(context.getString(R.string.postView_titleText).format(string), Html.FROM_HTML_MODE_LEGACY)
    }

    fun setBodyString(string: String) {
        m_textViewBody.text = Html.fromHtml(context.getString(R.string.postView_bodyText).format(string), Html.FROM_HTML_MODE_LEGACY)

    }
}