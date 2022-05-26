package com.example.somoni.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.somoni.data.apiservice.Api
import com.example.somoni.data.model.Regular
import com.example.somoni.data.model.TransferNbt
import com.example.somoni.extensions.utils.Resourse
import com.example.somoni.extensions.utils.ResourseNbt
import com.example.somoni.extensions.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {
    private val retrofit = Api.getRetrofitRequest()
    fun regularTransferNew(): MutableStateFlow<Resourse<List<Regular>>> {
        val data = MutableStateFlow<Resourse<List<Regular>>>(Resourse(null, State.LOADING))
        CoroutineScope(Dispatchers.IO).launch {
            retrofit.regularTransfer()
                .enqueue(object : Callback<List<Regular>> {
                    override fun onResponse(
                        call: Call<List<Regular>>,
                        response: Response<List<Regular>>
                    ) {
                        if (response.body() != null) {
                            data.value =  Resourse(response.body(), State.SUCCESS)
                        } else {
                            data.value =  Resourse(null, State.ERROR)
                        }
                    }

                    override fun onFailure(call: Call<List<Regular>>, t: Throwable) {
                        data.value =  Resourse(null, State.ERROR)
                    }
                })
        }
        return data
    }

    fun getTransferRusNew(): MutableStateFlow<Resourse<List<Regular>>> {
        val dataRus = MutableStateFlow<Resourse<List<Regular>>>(Resourse(null, State.LOADING))
        CoroutineScope(Dispatchers.IO).launch {
            retrofit.transferRus()
                .enqueue(object : Callback<List<Regular>> {
                    override fun onResponse(
                        call: Call<List<Regular>>,
                        response: Response<List<Regular>>
                    ) {
                        if (response.body() != null) {
                            dataRus.value = (Resourse(response.body(), State.SUCCESS))
                        } else {
                            dataRus.value = (Resourse(null, State.ERROR,))
                        }
                    }

                    override fun onFailure(call: Call<List<Regular>>, t: Throwable) {
                        dataRus.value = (
                                Resourse(
                                    null,
                                    State.ERROR,
                                )
                                )
                    }
                })
        }
        return dataRus
    }

    fun getTransferNbtNew(): MutableStateFlow<ResourseNbt<List<TransferNbt>>> {
        val dataNbt =
            MutableStateFlow<ResourseNbt<List<TransferNbt>>>(ResourseNbt(null, State.LOADING))
        CoroutineScope(Dispatchers.IO).launch {
            retrofit.transferNbt()
                .enqueue(object : Callback<List<TransferNbt>> {
                    override fun onResponse(
                        call: Call<List<TransferNbt>>,
                        response: Response<List<TransferNbt>>
                    ) {
                        if (response.body() != null) {
                            dataNbt.value = ResourseNbt(response.body(), State.SUCCESS)
                        } else {
                            dataNbt.value = ResourseNbt(null, State.ERROR,)
                        }
                    }

                    override fun onFailure(call: Call<List<TransferNbt>>, t: Throwable) {
                        dataNbt.value =
                            ResourseNbt(
                                null,
                                State.ERROR,
                            )

                    }
                })
        }
        return dataNbt
    }
}