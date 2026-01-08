package com.example.myfirebase.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myfirebase.AplikasiDataSiswa

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(siswaApp().container.repositorySiswa) }
        initializer { EntryViewModel(siswaApp().container.repositorySiswa) }
        initializer { DetailViewModel(createSavedStateHandle(), siswaApp().container.repositorySiswa) }
        initializer { EditViewModel(createSavedStateHandle(), siswaApp().container.repositorySiswa) }
