package com.example.anmp_uts.model

import com.google.gson.annotations.SerializedName

data class Hobby(
    val id:String?,
    val title:String?,
    val synopsis:String?,
    val writer:String?,
    val details:List<String>?,
    val images:String?,
)
