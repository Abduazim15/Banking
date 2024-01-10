package uz.gita.lesson40.presentation.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.lesson40.R
import uz.gita.lesson40.databinding.HistoryFragmentBinding
import uz.gita.lesson40.domain.entity.Card
import uz.gita.lesson40.domain.entity.CardHistoryEntity
import uz.gita.lesson40.domain.entity.DataX
import uz.gita.lesson40.presentation.DataBaseViewModel
import uz.gita.lesson40.presentation.HistoryViewModel
import uz.gita.lesson40.presentation.adapter.HistoryAdapter

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {
    private val binding: HistoryFragmentBinding by viewBinding()
    private val viewModel: HistoryViewModel by viewModels()
    private val dataBaseViewModel: DataBaseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = HistoryAdapter()
        dataBaseViewModel.getAll()
        dataBaseViewModel.livedata.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                adapter.submitList(converter(list))
            }
        }
        viewModel.history()
        binding.recycler.adapter = adapter
        adapter.setOnClickClickListener { index ->
            parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                .addToBackStack("HistoryFragment")
                .replace(
                    R.id.container, BillFragment::class.java, bundleOf(
                        "amount" to adapter.currentList[index].amount,
                        "card" to adapter.currentList[index].card.pan,
                        "id" to adapter.currentList[index].id,
                        "is_out" to adapter.currentList[index].is_output,
                        "phone" to adapter.currentList[index].card.phone_number,
                        "name" to adapter.currentList[index].card.owner,

                        )
                ).commit()
        }
        binding.all.setOnClickListener {
            binding.allT.setTextColor(Color.WHITE)
            binding.serviceT.setTextColor(Color.BLACK)
            binding.panT.setTextColor(Color.BLACK)
            binding.all.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3629B7"))
            binding.service.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F2F1F9"))
            binding.pan.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F2F1F9"))

        }
        binding.pan.setOnClickListener {
            binding.panT.setTextColor(Color.WHITE)
            binding.serviceT.setTextColor(Color.BLACK)
            binding.allT.setTextColor(Color.BLACK)
            binding.pan.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3629B7"))
            binding.all.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F2F1F9"))
            binding.service.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F2F1F9"))
        }
        binding.service.setOnClickListener {
            binding.serviceT.setTextColor(Color.WHITE)
            binding.allT.setTextColor(Color.BLACK)
            binding.panT.setTextColor(Color.BLACK)
            binding.service.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3629B7"))
            binding.all.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F2F1F9"))
            binding.pan.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F2F1F9"))
        }
        lifecycleScope.launch {
            viewModel.openErrorFlow.collect {

            }
        }
        lifecycleScope.launch {
            viewModel.openSuccessScreenFlow.collect { data ->
                dataBaseViewModel.insertAll(converterToEntity(data))
                adapter.submitList(data)
            }
        }
        lifecycleScope.launch {
            viewModel.openNetworkFlow.collect {

            }
        }
    }

    private fun converter(list: List<CardHistoryEntity>) : ArrayList<DataX>{
        val a = ArrayList<DataX>()
        for (i in list) {
            a.add(DataX(
                    i.amount,
                    Card(owner = i.name!!, pan = i.cardNumber!!, phone_number = i.phoneNumber!!),
                    i.id,
                    i.isSuccess
                ))
        }
        return a
    }
    private fun converterToEntity(list: List<DataX>) : ArrayList<CardHistoryEntity>{
        val a = ArrayList<CardHistoryEntity>()
        for (i in list) {
            a.add(CardHistoryEntity(
                0,
                i.card.owner,
                i.card.phone_number,
                i.card.pan,
                i.is_output,
                i.id,
                i.amount
            ))
        }
        return a
    }
}
