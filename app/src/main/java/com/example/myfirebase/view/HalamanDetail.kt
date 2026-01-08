package com.example.myfirebase.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.viewmodel.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateToEditItem: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.statusUIDetail
    val coroutineScope = rememberCoroutineScope()

    // State untuk mengontrol tampilan Dialog Konfirmasi
    var deleteConfirmationRequired by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = "Detail Siswa",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            if (uiState is StatusUIDetail.Success) {
                FloatingActionButton(
                    onClick = { navigateToEditItem(uiState.siswa.id) },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Siswa")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (uiState) {
                is StatusUIDetail.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is StatusUIDetail.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Gagal memuat data.")
                    }
