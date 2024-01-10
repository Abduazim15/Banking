package uz.gita.lesson40.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.lesson40.R
import uz.gita.lesson40.data.constants.ErrorCodes
import uz.gita.lesson40.databinding.PayFragmentBinding
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.presentation.DataBaseViewModel
import uz.gita.lesson40.presentation.PaymentViewModel
import uz.gita.lesson40.presentation.adapter.CardAdapter

@AndroidEntryPoint
class PayFragment : Fragment(R.layout.pay_fragment) {
    private val binding: PayFragmentBinding by viewBinding()
    private val viewModel: PaymentViewModel by viewModels()
    private val adapter by lazy { CardAdapter() }
    private val databaseViewModel: DataBaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
        databaseViewModel.getAllCards()
        databaseViewModel.livedataCards.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
        val id = arguments?.getInt("id")
        if (!arguments?.getString("number").isNullOrEmpty())
            binding.phone.setText(arguments?.getString("number"))


        binding.recycler.adapter = adapter


        binding.send.setOnClickListener {
            val layoutManager = binding.recycler.layoutManager
            val visibleView = snapHelper.findSnapView(layoutManager)
            var currentId = 57
            if (visibleView != null) {
                currentId = binding.recycler.getChildAdapterPosition(visibleView)
                Toast.makeText(
                    requireContext(),
                    adapter.currentList[currentId].id.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                val amountEdt = binding.amount.text
                val amount =
                    if (amountEdt.isNullOrEmpty()) 0 else Integer.parseInt(amountEdt.toString())
                val phoneEdt = binding.phone.text
                val phone = if (phoneEdt.isNullOrEmpty()) "" else "+998$phoneEdt"
                val entity = PayEntity(amount, adapter.currentList[currentId].id, id!!, phone)
                viewModel.pay(entity)
            }
            binding.back.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.openErrorFlow.collect { error ->
                    when (error) {
                        ErrorCodes.CARD_NOT_GIVEN -> {
                            Toast.makeText(requireContext(), "Select your card", Toast.LENGTH_SHORT)
                                .show()
                        }

                        ErrorCodes.AMOUNT -> {
                            Toast.makeText(
                                requireContext(),
                                "Amount must be at least 1000",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        ErrorCodes.PHONE_NUMBER -> {
                            Toast.makeText(requireContext(), "Invalid number", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.openSuccessScreenFlow.collect {
                    viewModel.payVerify()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, SuccessfulFragment()).commit()
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.openNetworkFlow.collect {
                    Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}