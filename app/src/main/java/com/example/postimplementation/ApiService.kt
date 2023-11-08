package com.example.postimplementation

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Body as Body1
import retrofit2.http.Path as Path

interface ApiService {
   @GET("api/users/{id}")
   suspend fun getUserByid(@Path("id") id:String):Response<BaseResponse>

   @PUT("api/users/{id}")
   suspend fun updataUser(@Path("id") id: String, @Body1 body: JSONObject):Response<JsonObject>

   @DELETE("api/users/{id}")
   suspend fun deleteUser(@Path("id")id: String):Response<JsonObject>

   @POST("api/users/{id}")
   suspend fun CreateUser(@Body body: JSONObject):Response<JsonObject>
}