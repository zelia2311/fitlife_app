package com.example.fitlifeapplication.ui.meal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitlifeapplication.data.Meal
import com.example.fitlifeapplication.data.MealRepository
import kotlinx.coroutines.launch

class MealSuggestionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MealRepository(application)

    private val _suggestions = MutableLiveData<List<Meal>>()
    val suggestions: LiveData<List<Meal>> = _suggestions

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchSuggestions(bmiCategory: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val mealSuggestions = repository.getMealSuggestions(bmiCategory)
                _suggestions.value = mealSuggestions
            } catch (e: Exception) {
                _error.value = "Failed to load meal suggestions: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
