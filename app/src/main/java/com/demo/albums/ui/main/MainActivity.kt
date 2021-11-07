package com.demo.albums.ui.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.demo.albums.R
import com.demo.albums.databinding.ActivityMainBinding
import com.demo.albums.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
/*
*  @AndroidEntryPoint - Means,Hilt should provide all the dependencies to this class that it asks for.
* */
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}
