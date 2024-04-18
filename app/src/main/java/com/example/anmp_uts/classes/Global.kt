package com.example.anmp_uts.classes

object Global {
    val accountArr = arrayListOf(
        Account("len","Valencio", "Nathanael", "s160421006@student.ubaya.ac.id", "asdf"),
        Account("a","b", "c", "alphabet@gmail", "a123"),

        )
    var cachedAcc: Account? = null // Declare cachedAcc as a mutable variable

    fun saveAccount(account: Account) {
        cachedAcc = account
    }
}