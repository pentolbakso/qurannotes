package com.kodebonek.qurannotes.data.db.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.kodebonek.qurannotes.data.db.entity.Pet
import com.kodebonek.qurannotes.data.db.entity.User

/**
 * @author <@Po10cio> on 11/5/17 for AndroidKotlinBoilerplate
 *
 */
class UserAndAllPets {
    @Embedded
    var user: User? = null

    @Relation(parentColumn = "id", entityColumn = "user_id")
    var pets: List<Pet>? = null
}