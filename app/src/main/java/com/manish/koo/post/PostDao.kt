package com.manish.koo.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostData(list : List<PostData>)

    @Query("SELECT * FROM postData Where masterKey = :id")
    fun fetchPostData(id : Int): List<PostData>
}