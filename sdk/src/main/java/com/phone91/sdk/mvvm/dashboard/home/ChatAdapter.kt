package com.phone91.sdk.mvvm.dashboard.home

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.phone91.sdk.R
import com.phone91.sdk.model.ChatObject
import com.phone91.sdk.model.MessageObject
import kotlinx.android.synthetic.main.item_chat.view.*
import kotlinx.android.synthetic.main.item_team.view.*

//import kotlinx.android.synthetic.main.item_post.view.*


class ChatAdapter(var context: Context?, public var teamList: ArrayList<ChatObject>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    var requestOptions: RequestOptions = RequestOptions()
        .placeholder(R.drawable.ic_image_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_image_placeholder)

    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.item_chat, parent, false))
    }

    override fun getItemCount(): Int = teamList.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var chatObject=teamList.get(position)

       // holder.txtMe.text=chatObject.content

        holder.txtMe.visibility=View.GONE
        holder.imgMe.visibility=View.GONE
        holder.txtYou.visibility=View.GONE
        holder.imgYou.visibility=View.GONE
        if(chatObject.sender==null){
            holder.txtMe.text=chatObject.content
            if(chatObject.content==null)
                holder.txtMe.visibility=View.GONE
            else
                holder.txtMe.visibility=View.VISIBLE
            holder.txtYou.visibility=View.GONE
            if(chatObject.attachment_url!=null && !chatObject.attachment_url.equals("")) {
                holder.imgMe.visibility=View.VISIBLE
                Glide.with(context!!)
                    .load(chatObject.attachment_url)
                    .apply(requestOptions)
                    .into(holder.imgMe)
            }
            else
                holder.imgMe.visibility=View.GONE

        }else{
            holder.txtYou.text=chatObject.content
            if(chatObject.content==null)
                holder.txtYou.visibility=View.GONE
            else
               holder.txtYou.visibility=View.VISIBLE
            holder.txtMe.visibility=View.GONE

            if(chatObject.attachment_url!=null && !chatObject.attachment_url.equals("")) {
                holder.imgYou.visibility=View.VISIBLE
                Glide.with(context!!)
                    .load(chatObject.attachment_url)
                    .apply(requestOptions)
                    .into(holder.imgYou)
            }
            else
                holder.imgYou.visibility=View.GONE
        }

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtMe = view.txtMe!!
        var txtYou = view.txtYou!!
        var imgYou = view.imgYou!!
        var imgMe = view.imgMe!!
    }

    fun add(message: ChatObject) {
        teamList.add(message)
        (context as Activity?)!!.runOnUiThread {

            notifyDataSetChanged()


        }
    }


}