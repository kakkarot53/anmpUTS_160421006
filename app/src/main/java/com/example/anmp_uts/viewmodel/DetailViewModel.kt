package com.example.anmp_uts.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_uts.model.Hobby
import com.google.gson.Gson

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val hobbyLD = MutableLiveData<Hobby>()
    private var queue: RequestQueue? = null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/hobbies/hobby.php?id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                hobbyLD.value  = Gson().fromJson(response, Hobby::class.java)
                Log.e("showvoley",response)
            },
            {
                Log.d("showvoley", it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}

