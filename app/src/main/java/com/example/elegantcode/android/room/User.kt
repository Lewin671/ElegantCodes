package com.example.elegantcode.android.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(val firstName: String, var lastName: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String {
        return "User(id=$id,firstName=$firstName,lastName=$lastName,age=$age)"
    }
}