package uz.gita.lesson40.presentation.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.lesson40.R
import uz.gita.lesson40.databinding.FragmentHomeBinding
import uz.gita.lesson40.domain.entity.FastPayModel
import uz.gita.lesson40.domain.entity.getResponse.Data
import uz.gita.lesson40.presentation.DataBaseViewModel
import uz.gita.lesson40.presentation.HomeViewModel
import uz.gita.lesson40.presentation.adapter.CardAdapter
import uz.gita.lesson40.presentation.adapter.FastAdapter

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()
    private val databaseViewModel : DataBaseViewModel by viewModels()
    private val dataList: ArrayList<Data> by lazy { ArrayList() }
    private val adapter by lazy { CardAdapter() }
    private val adapterFast by lazy { FastAdapter() }
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        databaseViewModel.getAllCards()
        databaseViewModel.livedataCards.observe(viewLifecycleOwner){data ->
            if (!data.isNullOrEmpty())
                adapter.submitList(data)
        }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.isDraggable = true
        bottomSheetBehavior.peekHeight = 700
        adapterFast.submitList(
            listOf(
                FastPayModel(true, "8600332968787451", null),
                FastPayModel(true, "8600332968787456", null),
                FastPayModel(false, "941302228", 1),
                FastPayModel(false, "998001252", 2),
            )
        )
        binding.swipe.setOnRefreshListener {
            viewModel.getCards()
        }
        binding.notification.setOnClickListener {
            if (binding.notification.tag == "on"){
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.VIBRATE)
                    == PackageManager.PERMISSION_GRANTED) {
                    vibratePhone()
                } else {
                    ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(android.Manifest.permission.VIBRATE),
                        123)
                }
                binding.notification.tag = "off"
                binding.notification.setImageResource(R.drawable.mute)
            }
            else {
                binding.notification.tag = "on"
                binding.notification.setImageResource(R.drawable.notifications)
            }
        }
        adapterFast.setOnClickClickListener { index ->
            if (!adapterFast.currentList[index].isCard)
                parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .addToBackStack("HomeFragment")
                    .replace(
                        R.id.container,
                        PayFragment::class.java,
                        bundleOf(
                            "id" to adapterFast.currentList[index].serviceId,
                            "number" to adapterFast.currentList[index].number
                        )
                    ).commit()
            else
                parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .addToBackStack("HomeFragment")
                    .replace(
                        R.id.container,
                        TransferFragment::class.java,
                        bundleOf("number" to adapterFast.currentList[index].number)
                    ).commit()
        }
        binding.apply {
            recycler.adapter = adapter
            mainRecycler.adapter = adapterFast

            addCard.setOnClickListener {
                parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .addToBackStack("HomeFragment").replace(R.id.container, AddCardFragment())
                    .commit()
            }
            send.setOnClickListener {
                parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .addToBackStack("HomeFragment").replace(R.id.container, TransferFragment())
                    .commit()
            }
            pay.setOnClickListener {
                parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .addToBackStack("HomeFragment").replace(R.id.container, PaymentFragment())
                    .commit()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.openSuccesFlow.collect { data ->
                binding.swipe.isRefreshing = false
                databaseViewModel.insertCards(data)
                dataList.clear()
                dataList.addAll(data)
                adapter.submitList(dataList)
            }
        }
        adapter.setOnClickClickListener { index ->
            parentFragmentManager.beginTransaction().setReorderingAllowed(true).addToBackStack("HomeFragment")
                .replace(R.id.container, RenameFragment::class.java, bundleOf("src" to R.drawable.card_back,"id" to adapter.currentList[index].id,)).commit()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.openErrorFlow.collect { error ->
                binding.swipe.isRefreshing = false
                if (error == 1) {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.openNetworkFlow.collect {
                binding.swipe.isRefreshing = false
                Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCards()
    }
    private fun vibratePhone() {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (vibrator.hasVibrator()) {
            vibrator.vibrate(500L)
        }
    }
}