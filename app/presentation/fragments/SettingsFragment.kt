package uz.gita.lesson40.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.lesson40.R
import uz.gita.lesson40.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private val binding: SettingsFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.updatePassword.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, UpdatePasswordScreen())
                .addToBackStack("UpdatePassword").commit()

        }

        binding.upateNumber.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container,UpdateNumberFragment())
                .addToBackStack("UpdateNumber").commit()
        }


        binding.openTg.setOnClickListener {
            openTelegramChat()
        }


    }
    private fun openTelegramChat() {
        val telegramChatUrl =
            "https://t.me/Rasuljonov_Nurali"

        val telegramIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramChatUrl))
        if (telegramIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(telegramIntent)
        } else {

        }
    }

}
