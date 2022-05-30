package com.example.jerakitchen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jerakitchen.R
import com.example.jerakitchen.model.Recipe
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class RecipeAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recipes : MutableList<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_recipe, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is RecipesViewHolder ->{
                holder.bind(recipes[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun submitList (recipesList : List<Recipe>) {
        recipes.addAll(recipesList)
        notifyDataSetChanged()
    }

    class RecipesViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val recipeName : TextView = itemView.findViewById(R.id.name_recipe_textView)
        private val imageRecipe : ImageView = itemView.findViewById(R.id.image_recipe_imageView)
        private val cookTime : TextView = itemView.findViewById(R.id.cook_time_recipe_textView)

        fun bind(recipe: Recipe){
            val recipeModelCookTime = recipe.cookTime
            if (recipeModelCookTime != null) {
                cookTime.text = "${recipe.cookTime} min"
            } else{
                cookTime.text = "Receita imediata"
            }
            recipeName.text = recipe.name
            Picasso.get().load(recipe.thumbnail).into(imageRecipe)
        }
    }

}
