package com.hailer.simplertoast

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import org.json.JSONException

class MainActivity : AppCompatActivity()
{

    val movies: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mData = createMovieList()

        message_list.layoutManager = LinearLayoutManager(this)
        message_list.adapter = MovieAdapter(mData)



        secret_btn.setOnClickListener {
            toastAllSecrets(this)
        }


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