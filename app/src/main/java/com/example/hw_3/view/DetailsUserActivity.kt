package com.example.hw_3.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3.R
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hw_3.view_model.DetailsUserViewModel

class DetailsUserActivity : AppCompatActivity() {

    private lateinit var vm: DetailsUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        vm = ViewModelProvider(this).get(DetailsUserViewModel::class.java)

        val arg = intent.extras
        val id: Int = arg?.getInt("id")!!.toInt()

        initObservers()
        vm.loadDetailsUser(id)
    }

    private fun initObservers() {

        val detailsImage: ImageView = findViewById(R.id.detailsImage)
        val detailsUserName: TextView = findViewById(R.id.detailsUserName)
        val detailsTextStatus: TextView = findViewById(R.id.detailsUserStatus)
        val detailsFollowers: TextView = findViewById(R.id.detailsFollowers)
        val detailsFollowing: TextView = findViewById(R.id.detailsFollowing)
        val detailsScore: TextView = findViewById(R.id.detailsScore)
        val detailsSharemeter: TextView = findViewById(R.id.detailsSharemeter)
        val detailsReach: TextView = findViewById(R.id.detailsReach)
        val detailsPosts: TextView = findViewById(R.id.detailsPosts)

        vm.userDetailsLiveData.observe(this, Observer {

            title = it.name
            detailsUserName.text = it.name
            detailsTextStatus.text = it.status
            detailsFollowers.text = it.followers.toString()
            detailsFollowing.text = it.following.toString()
            detailsScore.text = it.socialScore.toString()
            detailsSharemeter.text = it.sharemeter.toString()
            detailsReach.text = it.reach
            detailsPosts.text = it.posts

            Glide.with(this)
                .load(it.photoUri)
                .error(R.drawable.ic_launcher_foreground)
                .into(detailsImage)


        })
    }
}