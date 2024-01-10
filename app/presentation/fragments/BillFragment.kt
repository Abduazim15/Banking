package uz.gita.lesson40.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.lesson40.R
import uz.gita.lesson40.databinding.ElectricBillFragmentBinding
import uz.gita.lesson40.domain.entity.DataX

class BillFragment : Fragment(R.layout.electric_bill_fragment) {
    private val binding: ElectricBillFragmentBinding by viewBinding()
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val amount = arguments?.getInt("amount")
        val card1 = arguments?.getString("card")
        val id = arguments?.getInt("id")
        val isOuted = arguments?.getBoolean("is_out")
        val phone1 = arguments?.getString("phone")
        val name1 = arguments?.getString("name")
        binding.apply {
            deposit.text = "$${amount.toString()}"
            total.text = "$${amount.toString()}"
            name.text = name1
            phone.text = phone1
            card.text = card1
            code.text = "#${id.toString()}"
        }
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}