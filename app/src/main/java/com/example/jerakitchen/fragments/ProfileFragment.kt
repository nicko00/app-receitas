package com.example.jerakitchen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerakitchen.activity.MainActivity
import com.example.jerakitchen.R
import com.example.jerakitchen.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var layoutManager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mAuth = FirebaseAuth.getInstance()

        (activity as MainActivity).supportActionBar?.show()

        binding.profilesRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        return binding.root
    }

}