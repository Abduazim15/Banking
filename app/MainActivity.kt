package uz.gita.lesson40

 import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.lesson40.presentation.fragments.Splash

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val timer =object : CountDownTimer(10000,1000){
        override fun onTick(p0: Long) {

        }

        override fun onFinish() {
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, Splash()).commit()
    }

    override fun onStop() {
        timer.start()
        super.onStop()
    }

    override fun onResume() {
        timer.cancel()
        super.onResume()
    }
}