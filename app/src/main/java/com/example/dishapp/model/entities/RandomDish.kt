package com.example.dishapp.model.entities
import com.google.gson.annotations.SerializedName


object RandomDish {
    data class Recipes(
    @SerializedName("recipes")
    val recipes: List<Recipe?>? = null
)

data class Recipe(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int? = null,
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction?>? = null,
    @SerializedName("cheap")
    val cheap: Boolean? = null,
    @SerializedName("cookingMinutes")
    val cookingMinutes: Int? = null,
    @SerializedName("creditsText")
    val creditsText: String? = null,
    @SerializedName("cuisines")
    val cuisines: List<Any?>? = null,
    @SerializedName("dairyFree")
    val dairyFree: Boolean? = null,
    @SerializedName("diets")
    val diets: List<String?>? = null,
    @SerializedName("dishTypes")
    val dishTypes: List<String?>? = null,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient?>? = null,
    @SerializedName("gaps")
    val gaps: String? = null,
    @SerializedName("glutenFree")
    val glutenFree: Boolean? = null,
    @SerializedName("healthScore")
    val healthScore: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("imageType")
    val imageType: String? = null,
    @SerializedName("instructions")
    val instructions: String? = null,
    @SerializedName("lowFodmap")
    val lowFodmap: Boolean? = null,
    @SerializedName("occasions")
    val occasions: List<Any?>? = null,
    @SerializedName("originalId")
    val originalId: Any? = null,
    @SerializedName("preparationMinutes")
    val preparationMinutes: Int? = null,
    @SerializedName("pricePerServing")
    val pricePerServing: Double? = null,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int? = null,
    @SerializedName("servings")
    val servings: Int? = null,
    @SerializedName("sourceName")
    val sourceName: String? = null,
    @SerializedName("sourceUrl")
    val sourceUrl: String? = null,
    @SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("sustainable")
    val sustainable: Boolean? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vegan")
    val vegan: Boolean? = null,
    @SerializedName("vegetarian")
    val vegetarian: Boolean? = null,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean? = null,
    @SerializedName("veryPopular")
    val veryPopular: Boolean? = null,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int? = null
)

data class AnalyzedInstruction(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("steps")
    val steps: List<Step?>? = null
)

data class ExtendedIngredient(
    @SerializedName("aisle")
    val aisle: String? = null,
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("consistency")
    val consistency: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("measures")
    val measures: Measures? = null,
    @SerializedName("meta")
    val meta: List<String?>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nameClean")
    val nameClean: String? = null,
    @SerializedName("original")
    val original: String? = null,
    @SerializedName("originalName")
    val originalName: String? = null,
    @SerializedName("unit")
    val unit: String? = null
)

data class Step(
    @SerializedName("equipment")
    val equipment: List<Equipment?>? = null,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient?>? = null,
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("step")
    val step: String? = null
)

data class Equipment(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("localizedName")
    val localizedName: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Ingredient(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("localizedName")
    val localizedName: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Measures(
    @SerializedName("metric")
    val metric: Metric? = null,
    @SerializedName("us")
    val us: Us? = null
)

data class Metric(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("unitLong")
    val unitLong: String? = null,
    @SerializedName("unitShort")
    val unitShort: String? = null
)

data class Us(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("unitLong")
    val unitLong: String? = null,
    @SerializedName("unitShort")
    val unitShort: String? = null
)
}