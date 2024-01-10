package com.skipissue.mobilebanking.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.mobilebanking.R
import com.skipissue.mobilebanking.data.settings.SettingsImpl
import com.skipissue.mobilebanking.databinding.FragmentSplash2Binding

class Splash1 : Fragment(R.layout.fragment_splash2) {
    private val binding: FragmentSplash2Binding by viewBinding()
    private val settingsImpl: SettingsImpl by lazy { SettingsImpl(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            checkbox.setOnClickListener {
                if (checkbox.isChecked) {
                    bottom.setBackgroundResource(R.color.purple_700)
                } else if (!checkbox.isChecked) {
                    bottom.setBackgroundResource(R.color.purple_200)
                }
            }

            bottom.setOnClickListener {
                if (checkbox.isChecked) {
                    settingsImpl.policy = 1
                    parentFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.container, SignInScreen())
                        .commit()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Do you Agree Privacy Policy",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }
}