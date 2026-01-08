package com.example.myfirebase.repositori

import com.example.myfirebase.modeldata.Siswa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class NetworkRepositorySiswa(
    private val firestore: FirebaseFirestore
) : RepositorySiswa {

    private val siswaCollection = firestore.collection("siswa")

    override fun getAllSiswa(): Flow<List<Siswa>> = callbackFlow {
        val subscription = siswaCollection.addSnapshotListener { snapshot, error ->
            if (error != null) { close(error); return@addSnapshotListener }
            if (snapshot != null) {
                val items = snapshot.documents.mapNotNull { it.toObject(Siswa::class.java)?.copy(id = it.id) }
                trySend(items)
            }
        }
        awaitClose { subscription.remove() }
    }

    override fun getSiswaById(id: String): Flow<Siswa?> = callbackFlow {
        val subscription = siswaCollection.document(id).addSnapshotListener { snapshot, error ->
            if (error != null) { close(error); return@addSnapshotListener }
            if (snapshot != null && snapshot.exists()) {
                val item = snapshot.toObject(Siswa::class.java)?.copy(id = snapshot.id)
                trySend(item)
            } else { trySend(null) }
        }
        awaitClose { subscription.remove() }
    }

    override suspend fun insertSiswa(siswa: Siswa) {
        siswaCollection.add(siswa).await()
    }

    override suspend fun updateSiswa(id: String, siswa: Siswa) {
        siswaCollection.document(id).set(siswa).await()
    }

    override suspend fun deleteSiswa(id: String) {
        siswaCollection.document(id).delete().await()
    }
}
