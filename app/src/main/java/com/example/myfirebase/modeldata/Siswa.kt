package com.example.myfirebase.modeldata

// Model Data untuk Firebase
data class Siswa(
    val id: String = "",
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// State untuk UI
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: String = "",
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)
