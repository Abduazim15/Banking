package uz.gita.lesson40.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.lesson40.R
import uz.gita.lesson40.databinding.AccountFragmentBinding

class AccountFragment : Fragment(R.layout.account_fragment) {
    private val binding : AccountFragmentBinding by viewBinding()
    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = Color.WHITE
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.my_container, HomeFragment()).commit()
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_item_1 -> {parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.my_container, HomeFragment()).commit()}
                R.id.navigation_item_2 -> {parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.my_container, HistoryFragment()).commit()}
                R.id.navigation_item_3 -> {parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.my_container, CashBackFragment()).commit()}
                R.id.navigation_item_4 -> {parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.my_container, SettingsFragment()).commit()}
            }
            return@setOnItemSelectedListener true
        }
    }
}
