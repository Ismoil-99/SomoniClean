package com.example.somoni.data.apiservice

import com.example.somoni.data.model.Regular
import com.example.somoni.data.model.TransferNbt
import retrofit2.Call
import retrofit2.http.GET

interface Gets {

    @GET("npcr_bank_rates")
    fun regularTransfer(): Call<List<Regular>>
    @GET("c2c_bank_rates")
    fun transferRus(): Call<List<Regular>>
    @GET("nbt_rates")
    fun transferNbt(): Call<List<TransferNbt>>
}