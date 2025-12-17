package com.example.fitlifeapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class untuk merepresentasikan sebuah latihan (Exercise).
 * Dibuat Parcelable agar mudah dikirim antar Activity.
 */
@Parcelize
data class Exercise(
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Int? = null, // Berat bisa jadi opsional, jadi dibuat nullable
    val imageUrl: String,      // ID drawable untuk gambar latihan
    val bodyPartImageUrl: String // ID drawable untuk ikon bagian tubuh
) : Parcelable
