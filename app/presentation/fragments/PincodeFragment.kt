package uz.gita.lesson40.presentation.fragments

import android.app.Dialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.lesson40.R
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.databinding.AccountPincodeBinding
import javax.inject.Inject

@AndroidEntryPoint
class PincodeFragment : Fragment(R.layout.account_pincode) {
    private val binding: AccountPincodeBinding by viewBinding()
    private var input: String = ""
    private var isCreating = false
    @Inject
    lateinit var settings : Settings

    private var cancellation:CancellationSignal?=null
    private val authentionCallback:BiometricPrompt.AuthenticationCallback
        get()= @RequiresApi(Build.VERSION_CODES.P)
        object :BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication error: $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Authentication success!")
                //
                parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.container, AccountFragment()).commit()

            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkBiometricSupport()
    }



    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fingerTouch.setOnClickListener {
            val biometricPrompt=BiometricPrompt.Builder(requireContext())
                .setTitle("Title of prompt")
                .setSubtitle("Authentication is required")
                .setDescription("This app uses fingerprint protection to keep your data secure")
                .setNegativeButton("Cancel",requireContext().mainExecutor,DialogInterface.OnClickListener { dialog, which ->
                    notifyUser("Authentication cancelled")
                }).build()

            biometricPrompt.authenticate(getCancellationSignal(),requireContext().mainExecutor,authentionCallback)
        }

        reset()
        loadViews()
    }

    private fun reset() {
        isCreating = settings.screenPassword.isNullOrEmpty()
        for (i in 0 until binding.tv2.childCount) {
            val img = binding.tv2.getChildAt(i) as ImageView
            img.setImageResource(R.drawable.empty)
        }
        input = ""
    }

    private fun loadViews() {
        for (i in 0 until binding.numbers.childCount) {
            val linear = binding.numbers.getChildAt(i) as LinearLayout
            for (j in 0 until linear.childCount) {
                if (i == 3 && (j == 0 || j == 2)) continue
                val cardView = linear.getChildAt(j) as CardView
                val text = cardView.getChildAt(0) as TextView
                text.setOnClickListener {
                    if (input.length < 4) {
                        val img = binding.tv2.getChildAt(input.length) as ImageView
                        img.setImageResource(R.drawable.full)
                        input += text.text
                    }
                    if (input.length == 4){
                        if (isCreating)
                            settings.screenPassword = input
                        else if (input == settings.screenPassword) {
                            Toast.makeText(requireContext(), "True", Toast.LENGTH_SHORT).show()
                            parentFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.container, AccountFragment()).commit()
                        }
                        else Toast.makeText(requireContext(), "False", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.back.setOnClickListener {
            back()
        }
    }

    private fun back() {
        if (input.isNotEmpty()) {
            val img = binding.tv2.getChildAt(input.length-1) as ImageView
            img.setImageResource(R.drawable.empty)
            input = input.substring(0, input.length - 1)
        }
    }
    private fun notifyUser(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal():CancellationSignal{
        cancellation= CancellationSignal()
        cancellation?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellation as CancellationSignal
    }
    private fun checkBiometricSupport():Boolean {
        val keyguardManeger: KeyguardManager =context?.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManeger.isKeyguardLocked){
            notifyUser("Finger authentication has not been enabled in settings")
            return false
        }
        if (ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.USE_BIOMETRIC)!=PackageManager.PERMISSION_GRANTED){
            notifyUser("Finger authentication permission is not enabled")
            return false
        }
        return  if (requireContext().packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){true}else true

    }

}