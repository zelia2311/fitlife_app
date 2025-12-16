package com.example.fitlifeapplication // <-- PACKAGE DIPERBAIKI

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Int? = null, // Berat bisa jadi opsional
    val imageUrl: String, // URL atau ID drawable untuk gambar
    val bodyPartImageUrl: String // URL atau ID drawable untuk ikon bagian tubuh
) : Parcelable
