package com.kodebonek.qurannotes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Surah

@Database(entities = [Surah::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun quranDao(): QuranDao
}