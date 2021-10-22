package com.example.hw_3.view_model

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.hw_3.model.*

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val userData: UserData = UserData()

    //private var userDao: UserDataBaseDao

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    private val _userListLiveData = MutableLiveData<List<User>>()
    val userListLiveData: LiveData<List<User>> = _userListLiveData


    val dataSource = UserDataBase.getDatabase(application).userDataBaseDao()



    //init{
        //userDao = UserDataBase.getDatabase(application).userDataBaseDao()


    //}
    fun insertUserToDB(){
        for (user in userData.userList)
            dataSource.insert(user)
    }


    fun loadListUsers() {
        _userListLiveData.value = dataSource.getAllUsers()
    }

    fun openUserDetails(id: Int) {
        _userId.value = id
    }


}