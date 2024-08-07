package by.ssrlab.fishpits

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.ssrlab.fishpits.app.Application
import by.ssrlab.fishpits.databinding.ActivityLaunchBinding
import by.ssrlab.fishpits.utils.retrofit.common.Common
import by.ssrlab.fishpits.utils.retrofit.`interface`.RetrofitServices
import by.ssrlab.fishpits.utils.vm.main.MainVM
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class LaunchActivity : AppCompatActivity() {

    private lateinit var application: Application

    private lateinit var binding: ActivityLaunchBinding
    private val mediaJob = Job()
    private val mediaScope = CoroutineScope(Dispatchers.Main + mediaJob)

    private val activityVM: MainVM by viewModels()
    private lateinit var mService: RetrofitServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermission()

        application = Application()
        application.setContext(this)

        startActivity()
    }

    private suspend fun startApp() {
        val animationName = AnimationUtils.loadAnimation(this, R.anim.logo_appear_anim)
        val animationLogo = AnimationUtils.loadAnimation(this, R.anim.logo_appear_anim)

        binding.appName.startAnimation(animationName)
        binding.appName.visibility = View.VISIBLE
        delay(1500)
        binding.appLogo.startAnimation(animationLogo)
        binding.appLogo.visibility = View.VISIBLE
    }

    private fun startActivity(){
        mediaScope.launch {
            startApp()
        }

        mService = Common.retrofitService
        activityVM.setServices(mService)
        activityVM.loadData(application)

        mediaScope.launch {
            delay(5000)
            checkPermission {
                val intent = Intent(this@LaunchActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }

    private fun checkPermission(onSuccess: () -> Unit){
        while (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            mediaScope.launch {
                delay(1000)
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) onSuccess()
    }
}