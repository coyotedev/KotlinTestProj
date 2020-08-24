package com.example.coyotedev.testproj.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.Html
import android.view.LayoutInflater
import android.widget.TextView
import com.example.coyotedev.testproj.R

class CommentView(ctx: Context): ConstraintLayout(ctx) {

    private var m_textViewName: TextView
    private var m_textViewEmail: TextView
    private var m_textViewBody: TextView

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.view_comment, this, true)

        m_textViewName = rootView.findViewById(R.id.view_comment_name_textview)
        m_textViewEmail = rootView.findViewById(R.id.view_comment_email_textview)
        m_textViewBody = rootView.findViewById(R.id.view_comment_body_textview)
    }

    fun setName(string: String) {
        m_textViewName.text = Html.fromHtml(context.getString(R.string.viewComment_nameText).format(string), Html.FROM_HTML_MODE_LEGACY)
    }

    fun setEmail(string: String) {
        m_textViewEmail.text = Html.fromHtml(context.getString(R.string.viewComment_emailText).format(string), Html.FROM_HTML_MODE_LEGACY)
    }

    fun setBody(string: String) {
        m_textViewBody.text = Html.fromHtml(context.getString(R.string.viewComment_bodyText).format(string), Html.FROM_HTML_MODE_LEGACY)
    }
}