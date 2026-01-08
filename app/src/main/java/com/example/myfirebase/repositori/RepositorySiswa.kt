package com.example.myfirebase.repositori

import com.example.myfirebase.modeldata.Siswa
import kotlinx.coroutines.flow.Flow

interface RepositorySiswa {
    fun getAllSiswa(): Flow<List<Siswa>>
    fun getSiswaById(id: String): Flow<Siswa?>
    suspend fun insertSiswa(siswa: Siswa)
    suspend fun updateSiswa(id: String, siswa: Siswa)
    suspend fun deleteSiswa(id: String)
}
