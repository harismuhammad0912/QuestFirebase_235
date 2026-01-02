package com.example.myfirebase.repositori

import android.app.Application

// Interface untuk mendefinisikan penyedia data
interface ContainerApp {
    val repositorySiswa: RepositorySiswa
}

// Implementasi nyata yang menggunakan Firebase
class DefaultContainerApp : ContainerApp {
    override val repositorySiswa: RepositorySiswa by lazy {
        FirebaseRepositorySiswa()
    }
}

// Class Application untuk inisialisasi saat aplikasi pertama kali jalan
class AplikasiDataSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // Membuat instance container agar bisa dipakai di ViewModel
        container = DefaultContainerApp()
    }
}