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
import com.example.myfirebase.modeldata.toUIStateSiswa
import com.example.myfirebase.repositori.RepositorySiswa
import com.example.myfirebase.view.route.DestinasiEdit
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {
    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set

    private val siswaId: String = checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init {
        viewModelScope.launch {
            siswaUiState = repositorySiswa.getSiswaById(siswaId)
                .filterNotNull()
                .first()
                .toUIStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        siswaUiState = UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = true)
    }

    suspend fun updateSiswa() {
        repositorySiswa.updateSiswa(siswaUiState.detailSiswa.toDataSiswa())
    }
}