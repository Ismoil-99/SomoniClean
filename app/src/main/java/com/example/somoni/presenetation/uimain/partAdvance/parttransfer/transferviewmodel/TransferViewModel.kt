package com.example.somoni.presenetation.uimain.partAdvance.parttransfer.transferviewmodel

import com.example.somoni.data.model.Regular
import com.example.somoni.data.repo.Repository
import com.example.somoni.extensions.base.BaseViewModel
import com.example.somoni.extensions.utils.Resourse
import com.example.somoni.extensions.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var transferRus: MutableStateFlow<Resourse<List<Regular>>> = MutableStateFlow(Resourse(null,
        State.ERROR))
    fun getTransferRus() {
        transferRus = repository.getTransferRusNew()
    }
}