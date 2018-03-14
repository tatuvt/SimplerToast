package com.hailer.simplertoast

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mData = parseJson(getJsonData())

        message_list.layoutManager = LinearLayoutManager(this)
        message_list.adapter = MovieAdapter(mData, {part : Message -> messageItemClicked(part)})



        secret_btn.setOnClickListener {
            toastAllSecrets(this)

            getJsonData()
            parseJson(getJsonData())
        }


    }

    private fun getJsonData(): JSONArray
    {
        val jsonString = assets.open("data.txt").bufferedReader().use { it.readLine()}
        val jsonArray = JSONArray(jsonString)
        return jsonArray
    }

    private fun parseJson(data: JSONArray) : ArrayList<Message> {
        val messageList = ArrayList<Message>()
        for (i in 0 until data.length()){
            //Create a new Message object and populate it with the data from data
            //Take note: data is a JSONArray that holds JSONObjects.
            val curMsg = data[i] as JSONObject
            val newMsg = Message(curMsg.getLong("id"), curMsg.getString("message"), curMsg.getString("secret"), curMsg.getString("created"))
            //Add to messageList
            messageList.add(newMsg)
        }
        messageList.sortedWith(compareBy({it.id}, {it.created}))
        return messageList
    }

    private fun messageItemClicked(part: Message) {
        Toast.makeText(this, "Message ID: ${part.id}", Toast.LENGTH_SHORT).show()
    }

    private fun createMovieList() : List<Movies> {
        var movieList = ArrayList<Movies>()
        movieList.add(Movies(1, "The Phantom Menace"))
        movieList.add(Movies(2, "Attack of the Clones"))
        movieList.add(Movies(3, "Revenge of the Sith"))
        movieList.add(Movies(4, "A New Hope"))
        movieList.add(Movies(5, "The Empire Strikes Back"))
        movieList.add(Movies(6, "Return of the Jedi"))
        movieList.add(Movies(7, "The Force Awakens"))
        movieList.add(Movies(8, "The Last Jedi"))
        movieList.add(Movies(9, "Coming Soon"))
        movieList.add(Movies(1, "Test"))
        return movieList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item?.itemId == R.id.menu_btn_exit)
        {
            goodbye(this)
            finish()
        }
        else if (item?.itemId == R.id.menu_btn_ping)
        {
            ping(this)
            return true
        }
        return false
    }



}