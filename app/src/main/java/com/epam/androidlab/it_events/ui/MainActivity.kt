package com.epam.androidlab.it_events.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.epam.androidlab.it_events.R


class MainActivity : AppCompatActivity() {
    lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.my_fragment_container) as FrameLayout
        if (savedInstanceState == null)
            show_EPAM_fragment()
    }

    fun show_EPAM_fragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.my_fragment_container, EpamFragment())
                .commit()
    }
}
