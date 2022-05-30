package com.example.somoni.presenetation.uimain.partNbt.nbtviewmodel

import com.example.somoni.data.model.TransferNbt
import com.example.somoni.data.repo.Repository
import com.example.somoni.extensions.base.BaseViewModel
import com.example.somoni.extensions.utils.ResourseNbt
import com.example.somoni.extensions.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NbtViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var transferNbt: MutableStateFlow<ResourseNbt<List<TransferNbt>>> = MutableStateFlow(ResourseNbt(null,State.ERROR))
    fun getNbt() {
        transferNbt = repository.getTransferNbtNew()
    }
}