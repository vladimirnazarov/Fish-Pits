package by.ssrlab.fishpits.utils.vm

import android.content.Context
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import by.ssrlab.fishpits.MainActivity
import by.ssrlab.fishpits.R
import by.ssrlab.fishpits.databinding.FragmentPreviewBinding
import by.ssrlab.fishpits.utils.base.BaseVM
import kotlinx.coroutines.*

class PreviewVM : BaseVM() {

    private val mediaJob = Job()
    private val mediaScope = CoroutineScope(Dispatchers.Main + mediaJob)

    private suspend fun startApp(binding: FragmentPreviewBinding, context: Context) {
        val animationName = AnimationUtils.loadAnimation(context, R.anim.logo_appear_anim)
        val animationLogo = AnimationUtils.loadAnimation(context, R.anim.logo_appear_anim)

        binding.appName.startAnimation(animationName)
        binding.appName.visibility = View.VISIBLE
        delay(1500)
        binding.appLogo.startAnimation(animationLogo)
        binding.appLogo.visibility = View.VISIBLE
    }

    fun startFragmentEnter(binding: FragmentPreviewBinding, context: Context, onFinish: () -> Unit) {
        mediaScope.launch {
            startApp(binding, context)
        }

        mediaScope.launch {
            delay(5000)
            onFinish()
        }
    }

    fun hideUI(activity: MainActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.decorView.windowInsetsController!!.hide(
                android.view.WindowInsets.Type.statusBars()
            )
        } else {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}