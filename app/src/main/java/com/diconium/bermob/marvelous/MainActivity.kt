package com.diconium.bermob.marvelous

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/9 compose
// TODO: https://github.com/diconium/bermob-android-marvelous/issues/8 nav-component
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.frame_main).navigateUp() || super.onSupportNavigateUp()
    }
}
