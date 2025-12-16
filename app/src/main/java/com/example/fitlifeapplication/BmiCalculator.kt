package com.example.fitlifeapplication.logic

object BmiCalculator {

    enum class Category { UNDERWEIGHT, OPTIMAL, OVERWEIGHT, OBESE }

    fun calculateBmi(weightKg: Float, heightCm: Float): Float {
        val heightM = heightCm / 100f
        return weightKg / (heightM * heightM)
    }

    fun getCategory(bmi: Float): Category =
        when {
            bmi < 18.5f -> Category.UNDERWEIGHT
            bmi < 25f -> Category.OPTIMAL
            bmi < 30f -> Category.OVERWEIGHT
            else -> Category.OBESE
        }

    data class Recommendation(
        val workoutType: String,
        val diet: String,
        val workoutPlan: List<ExerciseItem>
    )

    data class ExerciseItem(
        val name: String,
        val detail: String
    )

    /** Smart Logic → Otomatis menghasilkan Workout Plan */
    fun getRecommendations(category: Category): Recommendation {

        val plan = when (category) {
            Category.UNDERWEIGHT -> listOf(
                ExerciseItem("Push Ups", "3 x 12"),
                ExerciseItem("Squat", "3 x 15"),
                ExerciseItem("Plank", "45 sec")
            )
            Category.OPTIMAL -> listOf(
                ExerciseItem("Jogging", "20 min"),
                ExerciseItem("Bodyweight Circuit", "3 sets"),
                ExerciseItem("Crunches", "20 reps")
            )
            Category.OVERWEIGHT -> listOf(
                ExerciseItem("Brisk Walk", "30 min"),
                ExerciseItem("Cardio Low Impact", "20 min")
            )
            Category.OBESE -> listOf(
                ExerciseItem("Chair Exercise", "15 min"),
                ExerciseItem("Walking", "20 min")
            )
        }

        val workoutType = when (category) {
            Category.UNDERWEIGHT -> "Strength & Hypertrophy"
            Category.OPTIMAL -> "Balanced Training"
            Category.OVERWEIGHT -> "Cardio Focus"
            Category.OBESE -> "Low Impact Starter"
        }

        val diet = when (category) {
            Category.UNDERWEIGHT -> "High Calorie, High Protein"
            Category.OPTIMAL -> "Balanced Meal"
            Category.OVERWEIGHT -> "Low Carb, High Fiber"
            Category.OBESE -> "High Protein, Controlled Carb"
        }

        return Recommendation(workoutType, diet, plan)
    }
}
