package com.asija.assignment.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asija.assignment.databinding.BottomSheetBonusBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BonusBottomSheet:BottomSheetDialogFragment() {
    private lateinit var binding:BottomSheetBonusBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= BottomSheetBonusBinding.inflate(layoutInflater)
        return binding.root
    }
}