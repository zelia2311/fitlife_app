package com.example.fitlifeapplication

object BmiManager {

    enum class Category { UNDERWEIGHT, NORMAL, OVERWEIGHT, OBESE }

    fun getCategory(bmi: Double): Category {
        return when {
            bmi < 18.5 -> Category.UNDERWEIGHT
            bmi < 25 -> Category.NORMAL
            bmi < 30 -> Category.OVERWEIGHT
            else -> Category.OBESE
        }
    }

    private val preset = mapOf(
        Category.UNDERWEIGHT to listOf(
            arrayOf("Push Ups", "20 reps"),
            arrayOf("Squats", "3x12")
        ),
        Category.NORMAL to listOf(
            arrayOf("Jogging", "20 min"),
            arrayOf("Circuit", "3 sets")
        ),
        Category.OVERWEIGHT to listOf(
            arrayOf("Brisk Walk", "30 min"),
            arrayOf("Low Impact Cardio", "20 min")
        ),
        Category.OBESE to listOf(
            arrayOf("Bike/Walk", "20 min"),
            arrayOf("Chair Exercises", "15 min")
        )
    )

    fun getPlanFor(category: Category): List<Array<String>> {
        return preset[category] ?: preset[Category.NORMAL]!!
    }
}
