package com.example.hw_3.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hw_3.model.User
import com.example.hw_3.model.UserDataBase
import com.example.hw_3.model.UserDataBaseDao

class DetailsUserViewModel(application: Application) : AndroidViewModel(application) {

    private val _userDetailsLiveData = MutableLiveData<User>()
    val userDetailsLiveData: LiveData<User> = _userDetailsLiveData

    private var userDao: UserDataBaseDao

    init {
        userDao = UserDataBase.getDatabase(application).userDataBaseDao()
    }

    fun loadDetailsUser(id: Int) {
        _userDetailsLiveData.value = userDao.get(id)
    }
}