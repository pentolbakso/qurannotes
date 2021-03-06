package com.kodebonek.qurannotes.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kodebonek.qurannotes.data.entity.Ayah
import com.kodebonek.qurannotes.data.entity.Surah

@Dao
interface QuranDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(list: List<Surah>?)

    @Query("DELETE FROM quran")
    fun clear()

    @Query("SELECT COUNT(number) FROM quran")
    fun getSurahCount(): Int

    @Query("SELECT * FROM quran ORDER BY number ASC")
    fun getSurahs(): List<Surah>

    @Query("SELECT * FROM ayah WHERE surahNumber = :surahNumber ORDER BY numberInSurah ASC")
    fun getAyahs(surahNumber: Int): List<Ayah>
}
