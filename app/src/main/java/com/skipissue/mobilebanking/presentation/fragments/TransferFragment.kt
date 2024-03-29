package com.skipissue.mobilebanking.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.skipissue.mobilebanking.R
import com.skipissue.mobilebanking.data.constants.ErrorCodes
import com.skipissue.mobilebanking.databinding.FragmentTransferBinding
import com.skipissue.mobilebanking.domain.entity.TransferEntity
import com.skipissue.mobilebanking.presentation.TransferViewModel

@AndroidEntryPoint
class TransferFragment : Fragment(R.layout.fragment_transfer) {
    private val binding: FragmentTransferBinding by viewBinding()
    private val viewModel: TransferViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        if (!arguments?.getString("number" ).isNullOrEmpty())
            binding.number.setText(arguments?.getString("number"))
        binding.send.setOnClickListener {
            val a = binding.amount.text.toString()
            var amount = 0
            if (!a.isNullOrEmpty())
                amount = Integer.parseInt(a)
            val card = binding.number.text.toString()
            val entity = TransferEntity(amount, 57, card)
            viewModel.transfer(entity)
        }
        lifecycleScope.launch {
            viewModel.openSuccessScreenFlow.collect {
                viewModel.transferVerify()
            }
        }
        lifecycleScope.launch {
            viewModel.openSuccessScreenFlow.collect {message ->
                Toast.makeText(requireContext(),message , Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().replace(R.id.container,SuccessfulFragment()).commit()
            }
        }
        lifecycleScope.launch {
            viewModel.openErrorFlow.collect {
                when(it) {
                    ErrorCodes.CARD_NOT_GIVEN -> {Toast.makeText(requireContext(), "Select your card", Toast.LENGTH_SHORT).show() }
                    ErrorCodes.AMOUNT -> {Toast.makeText(requireContext(), "Amount must be at least 1000", Toast.LENGTH_SHORT).show() }
                    ErrorCodes.INCORRECT_CARD -> {Toast.makeText(requireContext(), "Invalid card", Toast.LENGTH_SHORT).show() }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.openNetworkFlow.collect {
                Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT).show()
            }
        }
    }
}