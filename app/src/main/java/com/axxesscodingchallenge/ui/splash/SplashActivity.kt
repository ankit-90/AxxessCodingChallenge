package com.axxesscodingchallenge.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axxesscodingchallenge.ui.search.SearchActivity
import com.axxesscodingchallenge.utils.createIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTimer()
    }

    private fun setTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            createIntent(SearchActivity::class.java)
            finish()
        }
    }

}