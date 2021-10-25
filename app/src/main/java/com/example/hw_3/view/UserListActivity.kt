package com.example.hw_3.view
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hw_3.R
import com.example.hw_3.model.User
import com.example.hw_3.view_model.UserViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var vm: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list_activity)

        vm = ViewModelProvider(this).get(UserViewModel::class.java)

        initObservers()

        vm.insertUserToDB()

        vm.loadListUsers()

    }

    private fun initObservers() {

        val user1: LinearLayout = findViewById(R.id.layout1)
        val user2: LinearLayout = findViewById(R.id.layout2)
        val user3: LinearLayout = findViewById(R.id.layout3)
        val user4: LinearLayout = findViewById(R.id.layout4)
        val user5: LinearLayout = findViewById(R.id.layout5)
        val user6: LinearLayout = findViewById(R.id.layout6)
        val user7: LinearLayout = findViewById(R.id.layout7)

        val userName1: TextView = findViewById(R.id.userName1)
        val userName2: TextView = findViewById(R.id.userName2)
        val userName3: TextView = findViewById(R.id.userName3)
        val userName4: TextView = findViewById(R.id.userName4)
        val userName5: TextView = findViewById(R.id.userName5)
        val userName6: TextView = findViewById(R.id.userName6)
        val userName7: TextView = findViewById(R.id.userName7)

        val userTime1: TextView = findViewById(R.id.timeToOnline1)
        val userTime2: TextView = findViewById(R.id.timeToOnline2)
        val userTime3: TextView = findViewById(R.id.timeToOnline3)
        val userTime4: TextView = findViewById(R.id.timeToOnline4)
        val userTime5: TextView = findViewById(R.id.timeToOnline5)
        val userTime6: TextView = findViewById(R.id.timeToOnline6)
        val userTime7: TextView = findViewById(R.id.timeToOnline7)

        val userPhoto1: ImageView = findViewById(R.id.userPhoto1)
        val userPhoto2: ImageView = findViewById(R.id.userPhoto2)
        val userPhoto3: ImageView = findViewById(R.id.userPhoto3)
        val userPhoto4: ImageView = findViewById(R.id.userPhoto4)
        val userPhoto5: ImageView = findViewById(R.id.userPhoto5)
        val userPhoto6: ImageView = findViewById(R.id.userPhoto6)
        val userPhoto7: ImageView = findViewById(R.id.userPhoto7)

        vm.userListLiveData.observe(this, Observer {

            setUserData(userName1, userTime1, userPhoto1, it[0])
            setLayoutListener(user1, 0)

            setUserData(userName2, userTime2, userPhoto2, it[1])
            setLayoutListener(user2, 1)

            setUserData(userName3, userTime3, userPhoto3, it[2])
            setLayoutListener(user3, 2)

            setUserData(userName4, userTime4, userPhoto4, it[3])
            setLayoutListener(user4, 3)

            setUserData(userName5, userTime5, userPhoto5, it[4])
            setLayoutListener(user5, 4)

            setUserData(userName6, userTime6, userPhoto6, it[5])
            setLayoutListener(user6, 5)

            setUserData(userName7, userTime7, userPhoto7, it[6])
            setLayoutListener(user7, 6)

        })

        vm.userId.observe(this, Observer {
            val intent = Intent(this, DetailsUserActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        })

    }

    private fun setLayoutListener(lout: LinearLayout, id: Int) {
        lout.setOnClickListener {
            vm.openUserDetails(id)
        }

    }

    private fun setUserData(
        userName: TextView,
        userTime: TextView,
        userPhoto: ImageView,
        it: User,
    ) {
        userName.text = it.name
        userTime.text = it.time
        Glide.with(this)
            .load(it.photoUri)
            .error(R.drawable.ic_launcher_foreground)
            .into(userPhoto)
    }



}