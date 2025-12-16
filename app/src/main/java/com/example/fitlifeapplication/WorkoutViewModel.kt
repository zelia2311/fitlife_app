package com.example.fitlifeapplication // <-- PACKAGE DIPERBAIKI

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
        // Simulasi mengambil data pengguna dan daftar latihan
        fetchUserData()
        fetchWorkoutPlan()
    }

    private fun fetchUserData() {
        // Di sini Anda akan mengambil nama pengguna yang sebenarnya
        // dari SharedPreferences, database, atau API.
        _userName.value = "Jesse" // Contoh nama
    }

    private fun fetchWorkoutPlan() {
        // Di sini Anda akan mengambil rencana latihan pengguna.
        // Ini bisa dari database lokal atau dari remote server.
        val dummyExercises = listOf(
            Exercise("Dumbbell Row", 4, 12, null, "@drawable/ic_fitness", "@drawable/ic_fitness"),
            Exercise("Dumbbell Bicep Curl", 4, 11, 20, "@drawable/ic_fitness", "@drawable/ic_fitness"),
            Exercise("Hip Thrust", 3, 21, null, "@drawable/ic_fitness", "@drawable/ic_fitness"),
            Exercise("Hammer Curls", 4, 12, 15, "@drawable/ic_fitness", "@drawable/ic_fitness")
        )
        _exerciseList.value = dummyExercises
    }

    // Dipanggil saat item latihan diklik
    fun onExerciseClicked(exercise: Exercise) {
        // Logika untuk memulai latihan spesifik atau membuka detail
        // Untuk saat ini, kita bisa log saja
        println("Exercise clicked: ${exercise.name}")
    }

    // Dipanggil saat tombol "Start Workout" atau "Finish" diklik
    fun finishWorkout() {
        _completedWorkoutData.value = _exerciseList.value
    }
}
