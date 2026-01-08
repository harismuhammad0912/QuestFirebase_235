package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.repositori.RepositorySiswa
import com.example.myfirebase.view.route.DestinasiDetail
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed interface StatusUIDetail {
    data class Success(val siswa: Siswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    private val idSiswa: String = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var statusUIDetail: StatusUIDetail by mutableStateOf(StatusUIDetail.Loading)
        private set

    init { getSatuSiswa() }

    fun getSatuSiswa() {
        viewModelScope.launch {
            try {
                val siswa = repositorySiswa.getSiswaById(idSiswa).filterNotNull().first()
                statusUIDetail = StatusUIDetail.Success(siswa)
            } catch (e: Exception) { statusUIDetail = StatusUIDetail.Error }
        }
    }

    suspend fun deleteSiswa() {
        repositorySiswa.deleteSiswa(idSiswa)
    }
}
