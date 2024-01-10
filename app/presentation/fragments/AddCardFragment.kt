package uz.gita.lesson40.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.lesson40.R
import uz.gita.lesson40.data.constants.ErrorCodes
import uz.gita.lesson40.databinding.FragmentAddCardBinding
import uz.gita.lesson40.domain.entity.AddCardEntity
import uz.gita.lesson40.domain.entity.getResponse.Data
import uz.gita.lesson40.presentation.CardViewModel
import uz.gita.lesson40.presentation.adapter.CardAdapter

@AndroidEntryPoint
class AddCardFragment : Fragment(R.layout.fragment_add_card) {
    private val biding: FragmentAddCardBinding by viewBinding()
    private val viewModel: CardViewModel by viewModels()
    private lateinit var addCardEntity: AddCardEntity
    private val adapter by lazy { CardAdapter() }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        biding.apply {
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            recyclerView.adapter = adapter
            back.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            submit.setOnClickListener {
                if (biding.eDate.text.length == 5) {
                    addCardEntity = AddCardEntity(
                        biding.eDate.text.substring(0, 1).toInt(),
                        biding.eDate.text.substring(1, biding.eDate.text.length).toInt(),
                        biding.eName.text.toString(),
                        biding.eNumber.text.toString()
                    )
                } else if (biding.eDate.text.length == 6) {
                    addCardEntity = AddCardEntity(
                        biding.eDate.text.substring(0, 2).toInt(),
                        biding.eDate.text.substring(2, biding.eDate.text.length).toInt(),
                        biding.eName.text.toString(),
                        biding.eNumber.text.toString()
                    )
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Nimadir Nato`g`ri kiritildi",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.addCard(addCardEntity)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SuccessfulFragment()).commit()
            }
        }
        adapter.submitList(
            listOf(
                Data(
                    0,
                    "100000",
                    2,
                    30,
                    0,
                    "Noman Manzoor",
                    "9860228412344567",
                    "+998941234567",
                    R.drawable.group_2
                ),
                Data(
                    0,
                    "100000",
                    2,
                    30,
                    0,
                    "Noman Manzoor",
                    "9860228412344567",
                    "+998941234567",
                    R.drawable.group_4
                ),
                Data(
                    0,
                    "100000",
                    2,
                    30,
                    0,
                    "Noman Manzoor",
                    "9860228412344567",
                    "+998941234567",
                    R.drawable.group_5
                ),
                Data(
                    0,
                    "100000",
                    2,
                    30,
                    0,
                    "Noman Manzoor",
                    "9860228412344567",
                    "+998941234567",
                    R.drawable.card_back
                ),
            )
        )
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.openSuccessScreenFlow.collect { it ->
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().replace(R.id.container, HomeFragment())
                        .commit()

                }

            }
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.openErrorFlow.collect { error ->
                    when (error) {
                        ErrorCodes.CARD_NAME -> biding.eName.error = "Wrong name"
                        ErrorCodes.PEN_ERROR -> biding.eNumber.error = "Wrong Card Number"
                        ErrorCodes.MONTH -> biding.eDate.error = "Wrong Month"
                        ErrorCodes.YEAR -> biding.eDate.error = "Wrong Year"
                    }

                }

            }
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.openNetworkFlow.collect {
                    Toast.makeText(requireContext(), "Internetizni yangilang", Toast.LENGTH_SHORT)
                        .show()

                }

            }
        }


    }
}