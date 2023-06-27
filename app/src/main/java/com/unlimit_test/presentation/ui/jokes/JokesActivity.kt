package com.unlimit_test.presentation.ui.jokes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.unlimit_test.R
import com.unlimit_test.data.model.JokeModel
import com.unlimit_test.databinding.ActivityMainBinding
import com.unlimit_test.databinding.ItemJokeBinding
import com.unlimit_test.presentation.base.adapter.RecyclerCallback
import com.unlimit_test.presentation.base.adapter.RecyclerViewGenericAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class JokesActivity : AppCompatActivity() {

    var binding : ActivityMainBinding?=null
    val jokesViewModel : JokesViewModel by viewModel()
    var jokeList = ArrayList<JokeModel>()
    var jokeAdapter: RecyclerViewGenericAdapter<JokeModel, ItemJokeBinding>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this@JokesActivity,R.layout.activity_main)
        binding!!.executePendingBindings()

        fetchJokes()
        countDownTimer()
        setAdapter()

    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun countDownTimer() {

        val handler = Handler()
        val delayMillis: Long = 5000
        val runnable = object : Runnable {
            override fun run() {
                Log.e(">>>>check","working")
                fetchJokes()
                handler.postDelayed(this, delayMillis)
            }
        }
        handler.postDelayed(runnable, delayMillis)
    }

    private fun setAdapter() {
        if (jokeAdapter == null) {

            jokeAdapter = RecyclerViewGenericAdapter(jokeList, R.layout.item_joke,
                object : RecyclerCallback<ItemJokeBinding, JokeModel> {
                    override fun bindData(
                        binder: ItemJokeBinding,
                        model: JokeModel,
                        position: Int,
                        itemView: View?
                    ) {
                        binder.apply {

                            tvJoke.text= model.joke

                        }
                    }
                })

            binding!!.jokeRV.adapter = jokeAdapter
        }
    }

    private fun fetchJokes() {
        val responseLiveData = jokesViewModel.getJokes()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                jokeList.add(it)
                jokeAdapter!!.notifyDataSetChanged()
                if (jokeList.size > 10){
                    jokeList.removeAt(0)
                    jokeAdapter?.notifyItemRemoved(0)
                }
            } else {
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }
}