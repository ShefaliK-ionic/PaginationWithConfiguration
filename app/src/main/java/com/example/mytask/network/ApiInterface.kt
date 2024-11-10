package com.example.mytask.network

import com.example.mytask.model.PostData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface
{

     @GET("posts?")
     suspend fun getPost(@Query("skip") skip:Int,@Query("limit") limit:Int):PostData

}