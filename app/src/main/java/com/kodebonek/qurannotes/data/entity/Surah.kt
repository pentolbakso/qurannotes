package com.kodebonek.qurannotes.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "quran")
@Parcelize
data class Surah (
        @PrimaryKey
        var number: Int? = 0,
        var name: String? = "",
        @ColumnInfo(index = true) var englishName: String? = "",
        var englishNameTranslation: String? = "",
        var revelationType: String? = "",
        @Ignore var ayahs: List<Ayah>? = null
        //var edition: Edition?
) : Parcelable
