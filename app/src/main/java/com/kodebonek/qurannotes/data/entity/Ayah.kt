package com.kodebonek.qurannotes.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [
        ForeignKey(entity = Surah::class, parentColumns = ["number"], childColumns = ["surahNumber"], onDelete = ForeignKey.CASCADE)
])
data class Ayah (
        @PrimaryKey var number: Int?,
        var text: String?,
        var numberInSurah: Int?,
        var juz: Int?,
        var manzil: Int?,
        var page: Int?,
        var ruku: Int?,
        var hizbQuarter: Int?,
        //var sajda: Boolean?, TODO: add retrofit converter if you want to support sajda
        /*
                Boolean or Object
                "sajda": {
                    "id": 6,
                    "recommended": true,
                    "obligatory": false
                }
         */
        var surahNumber: Int    //foreignkey to parent
)