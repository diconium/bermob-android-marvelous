package com.diconium.bermob.marvelous

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

// TODO: Refactor to use Compose
// TODO: refactor to use Navigation component
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
