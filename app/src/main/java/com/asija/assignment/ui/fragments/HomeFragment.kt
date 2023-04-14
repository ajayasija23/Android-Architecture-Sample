package com.asija.assignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.asija.assignment.databinding.FragmentHomeBinding
import com.asija.assignment.ui.adapter.ProductAdapter
import com.asija.assignment.ui.bottom_sheet.BonusBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObserver()
        viewModel.getProducts()
    }

    private fun bindObserver() {
        viewModel.productsLiveData.observe(viewLifecycleOwner){
            binding.rvPost.adapter=ProductAdapter(requireContext(),it){
                val bonusBottomSheet=BonusBottomSheet()
                bonusBottomSheet.show(parentFragmentManager,"")
            }

        }
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


}