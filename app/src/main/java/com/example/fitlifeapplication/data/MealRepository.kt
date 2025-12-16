package com.example.fitlifeapplication.data

import android.content.Context

class MealRepository(context: Context) {

    private val mealDao = AppDatabase.getDatabase(context).mealDao()

    suspend fun getMealSuggestions(bmiCategory: String): List<Meal> {
        ensureInitialDataPopulated()
        return mealDao.getMealSuggestions(bmiCategory)
    }

    private suspend fun ensureInitialDataPopulated() {
        if (mealDao.getMealCount() == 0) {
            mealDao.insertAll(getInitialMealData())
        }
    }

    private fun getInitialMealData(): List<Meal> {
        return listOf(
            // === Kurus (Higher Calorie, Protein-Rich) - 6 items ===
            Meal(name="Nasi Goreng Spesial", description="Nasi goreng kaya rasa dengan ayam, udang, dan telur.", calories=650, portion="1 Piring", category="Kurus", imagePlaceholder="img_nasi_goreng"),
            Meal(name="Alpukat & Telur Toast", description="Roti gandum panggang dengan alpukat lumat dan telur.", calories=450, portion="2 potong", category="Kurus", imagePlaceholder="img_avocado_toast"),
            Meal(name="Smoothie Gainer", description="Smoothie pisang, selai kacang, dan protein powder.", calories=550, portion="1 Gelas Besar", category="Kurus", imagePlaceholder="img_banana_smoothie"),
            Meal(name="Pasta Carbonara", description="Pasta dengan saus krim, keju, dan potongan daging.", calories=700, portion="1 Mangkok", category="Kurus", imagePlaceholder="img_carbonara"),
            Meal(name="Kari Ayam Kentang", description="Kari ayam kaya rempah dengan santan dan kentang.", calories=600, portion="1 Mangkok", category="Kurus", imagePlaceholder="img_chicken_curry"),
            Meal(name="Rendang Daging", description="Daging sapi yang dimasak lama dengan santan dan rempah.", calories=550, portion="1 Potong Besar", category="Kurus", imagePlaceholder="img_rendang"),

            // === Ideal (Balanced, Nutrient-Dense) - 6 items ===
            Meal(name="Salad Ayam Panggang", description="Salad segar dengan ayam panggang dan saus vinaigrette.", calories=400, portion="1 Mangkok", category="Ideal", imagePlaceholder="img_chicken_salad"),
            Meal(name="Ikan Salmon & Brokoli", description="Salmon panggang dengan brokoli kukus dan perasan lemon.", calories=450, portion="150g Ikan", category="Ideal", imagePlaceholder="img_salmon_broccoli"),
            Meal(name="Nasi Merah & Tumis Sayur", description="Nasi merah dengan tumisan buncis, wortel, dan jagung.", calories=380, portion="1 Piring", category="Ideal", imagePlaceholder="img_red_rice_vegetables"),
            Meal(name="Buddha Bowl Quinoa", description="Bowl berisi quinoa, ubi jalar, buncis, dan sayuran segar.", calories=420, portion="1 Mangkok", category="Ideal", imagePlaceholder="img_buddha_bowl"),
            Meal(name="Sate Ayam Bumbu Kacang", description="Sate ayam panggang disajikan dengan bumbu kacang.", calories=450, portion="10 Tusuk", category="Ideal", imagePlaceholder="img_sate_ayam"),
            Meal(name="Capcay Kuah Seafood", description="Aneka sayuran yang dimasak dengan udang dan cumi.", calories=350, portion="1 Mangkok", category="Ideal", imagePlaceholder="img_capcay"),

            // === Gemuk (Lower Calorie, High Fiber) - 6 items ===
            Meal(name="Oatmeal & Buah Beri", description="Oatmeal hangat dengan stroberi, blueberry, dan sedikit madu.", calories=350, portion="1 Mangkok", category="Gemuk", imagePlaceholder="img_oatmeal_berries"),
            Meal(name="Sup Lentil Sayuran", description="Sup lentil merah hangat dengan wortel dan seledri.", calories=300, portion="1 Mangkok", category="Gemuk", imagePlaceholder="img_lentil_soup"),
            Meal(name="Dada Ayam Kukus & Sayur", description="Dada ayam kukus dengan sayuran hijau (bayam/brokoli).", calories=380, portion="1 Piring", category="Gemuk", imagePlaceholder="img_chicken_quinoa"),
            Meal(name="Gado-Gado Tanpa Lontong", description="Salad sayuran indonesia dengan saus kacang (sedikit).", calories=320, portion="1 Piring", category="Gemuk", imagePlaceholder="img_gado_gado"),
            Meal(name="Ikan Pepes Kemangi", description="Ikan kukus dibungkus daun pisang dengan bumbu rempah.", calories=320, portion="1 Ekor", category="Gemuk", imagePlaceholder="img_pepes_ikan"),
            Meal(name="Asinan Sayur Jakarta", description="Salad sayuran segar dengan kuah asam, manis, dan pedas.", calories=250, portion="1 Mangkok", category="Gemuk", imagePlaceholder="img_asinan"),

            // === Obesitas (Low-Carb, High-Protein) - 6 items ===
            Meal(name="Steak & Asparagus", description="Steak sapi panggang tanpa lemak dengan asparagus tumis.", calories=450, portion="150g Steak", category="Obesitas", imagePlaceholder="img_steak_asparagus"),
            Meal(name="Telur Orak-Arik & Bayam", description="Orak-arik putih telur dengan bayam segar dan jamur.", calories=300, portion="3 butir telur", category="Obesitas", imagePlaceholder="img_scrambled_eggs_spinach"),
            Meal(name="Salad Udang Alpukat", description="Salad segar dengan udang rebus, alpukat, dan saus lemon.", calories=380, portion="1 Mangkok", category="Obesitas", imagePlaceholder="img_shrimp_avocado_salad"),
            Meal(name="Tongseng Tanpa Santan", description="Tongseng kambing/sapi dengan kuah bening kaya rempah.", calories=400, portion="1 Mangkok Kecil", category="Obesitas", imagePlaceholder="img_tongseng"),
            Meal(name="Tahu Telur Saus Kacang", description="Tahu dan telur goreng dengan saus kacang encer.", calories=350, portion="1 Piring", category="Obesitas", imagePlaceholder="img_tahu_telur"),
            Meal(name="Sop Iga Sapi Bening", description="Sop iga dengan kuah kaldu bening, wortel, dan kentang.", calories=420, portion="1 Mangkok", category="Obesitas", imagePlaceholder="img_sop_iga")
        )
    }
    companion object {
        @Volatile
        private var INSTANCE: MealRepository? = null

        fun getInstance(context: Context): MealRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = MealRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
