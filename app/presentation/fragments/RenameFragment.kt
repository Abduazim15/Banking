package uz.gita.lesson40.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.lesson40.R
import uz.gita.lesson40.databinding.FagmentUpdateBinding
import uz.gita.lesson40.domain.entity.UpdateCardEntity
import uz.gita.lesson40.domain.entity.getResponse.Data
import uz.gita.lesson40.presentation.CardViewModel
import uz.gita.lesson40.presentation.adapter.CardAdapter

@AndroidEntryPoint
class RenameFragment : Fragment(R.layout.fagment_update) {
    private val binding: FagmentUpdateBinding by viewBinding()
    private val adapter by lazy { CardAdapter() }
    private val viewmodel: CardViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentTheme = arguments?.getInt("src")
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
        val id = arguments?.getInt("id")
        binding.recycler.adapter = adapter
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
        for (i in 0 until adapter.currentList.size) {
            if (adapter.currentList[i].theme == currentTheme) {
                binding.recycler.layoutManager?.scrollToPosition(i)
                break
            }
        }

        binding.save.setOnClickListener {
            val layoutManager = binding.recycler.layoutManager
            val visibleView = snapHelper.findSnapView(layoutManager)
            var currentId: Int
            if (visibleView != null) {
                currentId = binding.recycler.getChildAdapterPosition(visibleView)
                viewmodel.updateCard(
                    id!!,
                    UpdateCardEntity(
                        binding.firstName.text.toString(),
                        adapter.currentList[currentId].theme!!
                    )
                )
            }
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}