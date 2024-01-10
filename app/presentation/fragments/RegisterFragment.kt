package uz.gita.lesson40.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.lesson40.R
import uz.gita.lesson40.data.constants.ErrorCodes
import uz.gita.lesson40.databinding.FragmentRegisterBinding
import uz.gita.lesson40.presentation.RegisterViewModel

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val binding: FragmentRegisterBinding by viewBinding()
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openVerifyLiveData.observe(viewLifecycleOwner, openVerifyLiveDataObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.noNetworkLiveData.observe(viewLifecycleOwner, noNetworkLiveDataObserver)

        binding.register.setOnClickListener {
            val firstName = binding.firstName.text?.toString()
            val lastName = binding.lastName.text?.toString()
            val phone = "+998"+binding.phone.text?.toString()
            val password = binding.password.text?.toString()
            viewModel.signUp(firstName, lastName, password, phone)
        }
    }

    private val openVerifyLiveDataObserver: Observer<Unit> = Observer {
        Toast.makeText(requireContext(), "Verifyni och", Toast.LENGTH_SHORT).show()
        parentFragmentManager.beginTransaction().replace(R.id.container, VerifyAccountFragment()).addToBackStack("RegisterFragment").commit()
    }

    private val errorLiveDataObserver: Observer<Int> = Observer { error ->
        when (error) {
            ErrorCodes.FIRST_NAME_ERROR -> {
                if(binding.firstName.text.toString().length>=3){
                    Toast.makeText(requireContext(), "This number already exist", Toast.LENGTH_SHORT).show()
                }else{
                    binding.firstName.error = "Incorrect"
                }

            }
            ErrorCodes.LAST_NAME_ERROR -> binding.lastName.error = "Incorrect"
            ErrorCodes.PHONE_NUMBER -> binding.phone.error = "Incorrect"
            ErrorCodes.PASSWORD -> binding.password.error = "Incorrect"
        }
    }

    private val noNetworkLiveDataObserver: Observer<Unit> = Observer {
        Toast.makeText(requireContext(), "Internet yo'q", Toast.LENGTH_SHORT).show()
    }
}