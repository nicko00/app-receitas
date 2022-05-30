package com.example.jerakitchen.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerakitchen.R
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jerakitchen.adapters.RecipeAdapter
import com.example.jerakitchen.databinding.FragmentListBinding
import com.example.jerakitchen.model.Recipe
import com.example.jerakitchen.network.ApiService
import com.example.jerakitchen.network.RecipeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {
    private lateinit var binding : FragmentListBinding
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var listRecipes : List<Recipe>
    private lateinit var recipeAdapter : RecipeAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        setupAdapter()
        getRecipe()
        return binding.root
    }
    fun getRecipe() {
        val call = ApiService().recipeService().getRecipes(page)
        call.enqueue(object : Callback<RecipeResult> {
            override fun onResponse(call: Call<RecipeResult>, response: Response<RecipeResult>) {
                val apiResponse = response.body()
                if(apiResponse != null){
                    listRecipes = apiResponse.recipeResult
                    recipeAdapter.submitList(listRecipes)
                    getPage()
                }

            }
            override fun onFailure(call: Call<RecipeResult>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
        })
    }

    private fun setupAdapter(){
        val recyclerView = binding.recipesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recipeAdapter = RecipeAdapter()
        recyclerView.adapter = recipeAdapter
    }

    fun getPage(){
        binding.recipesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = recipeAdapter.itemCount
                if ((visibleItemCount + pastVisibleItem) >= total){
                    binding.recipesRecyclerView.removeOnScrollListener(this)
                    page++
                    getRecipe()
                }

                super.onScrolled(recyclerView, dx, dy)

            }

        })

    }
}