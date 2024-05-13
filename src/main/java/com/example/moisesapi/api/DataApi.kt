package com.example.moisesapi.api

import com.example.moisesapi.model.SendData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DataApi {
    @Headers("Cookie: moisesComposerTempJwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJwYUMxNVlJenRuTWl3bUN0V1BhaFliUVJ5R0gzIiwiaWF0IjoxNjk1NzM3NjYxLCJleHAiOjE3MjcyNzM2NjF9.jmGqDAVYFFLxNd1Nar1qht-HRrrf7hT2LlusWA3agbI; Path=/; Domain=moises.ai; Secure; HttpOnly; Expires=Thu, Wed, 25 Sep 2024 14:14:21 GMT;")
    @POST("word")
    fun getDataWord(@Body sendData: SendData): Call<List<String>>

    @Headers("Cookie: moisesComposerTempJwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJwYUMxNVlJenRuTWl3bUN0V1BhaFliUVJ5R0gzIiwiaWF0IjoxNjk1NzM3NjYxLCJleHAiOjE3MjcyNzM2NjF9.jmGqDAVYFFLxNd1Nar1qht-HRrrf7hT2LlusWA3agbI; Path=/; Domain=moises.ai; Secure; HttpOnly; Expires=Thu, Wed, 25 Sep 2024 14:14:21 GMT;")
    @POST("line")
    fun getDataLine(@Body sendData: SendData):Call<List<String>>
}