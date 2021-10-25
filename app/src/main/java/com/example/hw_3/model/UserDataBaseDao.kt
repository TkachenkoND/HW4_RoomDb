package com.example.hw_3.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDataBaseDao {
    @Insert
    fun insert(night: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from users WHERE userId = :key")
    fun get(key: Int): User?

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users LIMIT 1")
    fun voidСheckDb(): User?
}