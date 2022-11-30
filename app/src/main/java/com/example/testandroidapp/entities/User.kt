package com.example.testandroidapp.entities

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(
    val name1: String,
    val name2: String,
    val email: String,
    var phone: String,
) {
}