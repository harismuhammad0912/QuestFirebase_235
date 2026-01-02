package com.example.myfirebase.repositori

import com.example.myfirebase.modeldata.Siswa
import kotlinx.coroutines.flow.Flow

interface RepositorySiswa {
    // Fungsi untuk mengambil semua data siswa
    fun getAllSiswa(): Flow<List<Siswa>>
