package uz.gita.lesson40.presentation.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import uz.gita.lesson40.R
import uz.gita.lesson40.data.settings.SettingsImpl

class Splash : Fragment(R.layout.fragment_splash1) {
    private val settingsImpl: SettingsImpl by lazy { SettingsImpl(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (settingsImpl.policy==-1){
                    parentFragmentManager.beginTransaction().replace(R.id.container, Splash1()).commit()
                }
                else if(!settingsImpl.sigInToken.isNullOrEmpty()){
                    parentFragmentManager.beginTransaction().replace(R.id.container, PincodeFragment()).commit()
                }
                else{
                    parentFragmentManager.beginTransaction().replace(R.id.container, SignInScreen()).commit()
                }

            }

        }.start()
    }
}