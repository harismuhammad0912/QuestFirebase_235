package com.example.myfirebase.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.repositori.RepositorySiswa
import com.example.myfirebase.view.route.DestinasiDetail
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {
    private val siswaId: String = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    val uiState: StateFlow<Siswa> = repositorySiswa.getSiswaById(siswaId)
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Siswa()
        )

    fun deleteSiswa() {
        viewModelScope.launch {
            repositorySiswa.deleteSiswa(uiState.value)
        }
    }
}