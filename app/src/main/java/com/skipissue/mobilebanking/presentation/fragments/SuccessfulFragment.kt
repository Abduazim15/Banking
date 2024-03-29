package com.skipissue.mobilebanking.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.mobilebanking.R
import com.skipissue.mobilebanking.databinding.FragmentSuccesfulBinding

class SuccessfulFragment:Fragment(R.layout.fragment_succesful) {
    private val biding:FragmentSuccesfulBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        biding.button.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.container, AccountFragment()).commit()
            }
        })
    }

}