package com.example.somoni.presenetation.uimain.partAdvance.partusually.viewmodelusually

import com.example.somoni.data.model.Regular
import com.example.somoni.data.repo.Repository
import com.example.somoni.extensions.base.BaseViewModel
import com.example.somoni.extensions.utils.Resourse
import com.example.somoni.extensions.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UsuallyViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var regular: MutableStateFlow<Resourse<List<Regular>>> = MutableStateFlow(Resourse(null, State.ERROR))
    fun regularTransfer() {
        regular = repository.regularTransferNew()
    }
}