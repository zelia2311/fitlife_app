package com.example.fitlifeapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel : ViewModel() {

    // Data pengguna yang sedang login (contoh)
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    // Daftar latihan untuk ditampilkan
    private val _exerciseList = MutableLiveData<List<Exercise>>()
    val exerciseList: LiveData<List<Exercise>> = _exerciseList

    // Data latihan yang selesai untuk dikirim ke recovery
    private val _completedWorkoutData = MutableLiveData<List<Exercise>>()
    val completedWorkoutData: LiveData<List<Exercise>> = _completedWorkoutData

    init {
        // Simulasi mengambil data pengguna
        fetchUserData()
    }

    private fun fetchUserData() {
        // Di sini Anda akan mengambil nama pengguna yang sebenarnya
        _userName.value = "Jesse" // Contoh nama
    }

    // Fungsi baru untuk mengatur daftar latihan dari luar
    fun setExerciseList(exercises: List<Exercise>) {
        _exerciseList.value = exercises
    }

    // Dipanggil saat item latihan diklik
    fun onExerciseClicked(exercise: Exercise) {
        // Logika untuk memulai latihan spesifik atau membuka detail
        println("Exercise clicked: ${exercise.name}")
    }

    // Dipanggil saat tombol "Start Workout" atau "Finish" diklik
    fun finishWorkout() {
        _completedWorkoutData.value = _exerciseList.value
    }
}
