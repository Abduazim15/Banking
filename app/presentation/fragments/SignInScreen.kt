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
import uz.gita.lesson40.data.settings.SettingsImpl
import uz.gita.lesson40.databinding.SignInScreenBinding
import uz.gita.lesson40.presentation.SignInviewModel

@AndroidEntryPoint
class SignInScreen : Fragment(R.layout.sign_in_screen) {
    private val settingsImpl: SettingsImpl by lazy { SettingsImpl(requireContext()) }
    private val binding: SignInScreenBinding by viewBinding()
    private val viewModel: SignInviewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  viewLifecycleOwner.lifecycleScope.launch {
        //      repeatOnLifecycle(Lifecycle.State.RESUMED) {
        //          viewModel.openVerifyFlow.collect() {
        //              Log.d("Ali", "1: ")
        //              Toast.makeText(requireContext(), "Verifyni och flow", Toast.LENGTH_SHORT).show()
        //          }
        //      }
        //      repeatOnLifecycle(Lifecycle.State.RESUMED) {
        //          viewModel.errorFlow.collect() {
        //              Log.d("Ali", "2: ")

        //              Toast.makeText(requireContext(), "nomer flow", Toast.LENGTH_SHORT).show()

        //          }
        //      }
        //      repeatOnLifecycle(Lifecycle.State.RESUMED) {
        //          viewModel.noNetworkFlow.collect() {
        //              Log.d("Ali", "3: ")
        //              Toast.makeText(requireContext(), "Internet yo'q flow", Toast.LENGTH_SHORT)
        //                  .show()

        //          }
        //      }
        //  }


        viewModel.openVerifyLiveData.observe(viewLifecycleOwner, openVerifyLiveDataObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.noNetworkLiveData.observe(viewLifecycleOwner, noNetworkLiveDataObserver)


        binding.signUpTv.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, RegisterFragment())
                .addToBackStack("sinUp").commit()
        }


        binding.signin.setOnClickListener {
            val phone = "+998" + binding.phone.text?.toString()
            val password = binding.password.text?.toString()

            viewModel.signIn(password, phone)
        }


    }

    private val openVerifyLiveDataObserver: Observer<Unit> = Observer {
        parentFragmentManager.beginTransaction().replace(R.id.container, PincodeFragment()).commit()
        settingsImpl.auth=1
    }


    private val errorLiveDataObserver: Observer<Int> = Observer { error ->
        when (error) {
            ErrorCodes.PHONE_NUMBER -> {
                binding.phone.error = "Incorrect"
            }

            ErrorCodes.PASSWORD -> {
                Toast.makeText(requireContext(), "Password or nomer incorrect", Toast.LENGTH_SHORT)
                    .show()
            }


        }

    }

    private val noNetworkLiveDataObserver: Observer<Unit> = Observer {
        Toast.makeText(requireContext(), "Check your internet connection.", Toast.LENGTH_SHORT)
            .show()
    }


}