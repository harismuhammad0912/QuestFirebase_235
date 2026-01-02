package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.DetailSiswa
import com.example.myfirebase.modeldata.UIStateSiswa
import com.example.myfirebase.modeldata.toDataSiswa
import com.example.myfirebase.modeldata.toUiStateSiswa
import com.example.myfirebase.repositori.RepositorySiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set

    // Mengambil ID siswa dari argument navigasi
    private val _siswaId: String = checkNotNull(savedStateHandle["idSiswa"])

    init {
        viewModelScope.launch {
            // Mengambil data lama dari Firebase untuk ditampilkan di form edit
            siswaUiState = repositorySiswa.getSiswaById(_siswaId)
                .filterNotNull()
                .first()
                .toUiStateSiswa()
        }
    }
