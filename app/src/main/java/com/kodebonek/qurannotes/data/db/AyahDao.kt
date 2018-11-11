package com.kodebonek.qurannotes.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kodebonek.qurannotes.data.entity.Ayah

@Dao
interface AyahDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(list: List<Ayah>?)

    @Query("DELETE FROM ayah")
    fun clear()

}