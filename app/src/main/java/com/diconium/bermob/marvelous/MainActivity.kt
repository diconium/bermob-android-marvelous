package com.diconium.bermob.marvelous

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/9 compose
// TODO: https://github.com/diconium/bermob-android-marvelous/issues/8 nav-component
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(android.R.id.content, SearchFragment())
            }
        }
    }
}
