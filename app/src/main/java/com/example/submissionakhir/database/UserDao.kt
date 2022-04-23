package com.example.submissionakhir.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("DELETE FROM User WHERE User.id = :id")
    fun deleteUser(id: Int): Int

    @Query("SELECT * FROM User ORDER BY login ASC")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT count(*) FROM User WHERE User.id = :id")
    fun checkUser(id: Int): Int
}