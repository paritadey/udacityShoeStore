package com.parita.shoestore.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.parita.shoestore.model.Shoes

@Dao
interface ShoeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoe(shoes: Shoes)

    @Query("Select * from shoes")
    fun getShoeList(): LiveData<List<Shoes>>

}