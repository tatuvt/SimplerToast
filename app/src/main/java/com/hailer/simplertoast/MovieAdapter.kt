package com.hailer.simplertoast

import android.content.Context
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hailer.simplertoast.R.layout.list_item
import kotlinx.android.synthetic.main.list_item.view.*

class MovieAdapter (val data: ArrayList<Message>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(list_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MessageViewHolder).bind(data[position])
    }

    override fun getItemCount() = data.size

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(part: Message) {
            itemView.movie_name.text = part.message
            itemView.movie_id.text = part.id.toString() }
        }
    }

fun ping(context: Context) = Toast.makeText(context, "Pong", Toast.LENGTH_SHORT).show()

fun toastAllSecrets(context: Context) = Toast.makeText(context, "All secrets", Toast.LENGTH_SHORT).show()

fun goodbye(context: Context) = Toast.makeText(context, "Goodbye", Toast.LENGTH_LONG).show()