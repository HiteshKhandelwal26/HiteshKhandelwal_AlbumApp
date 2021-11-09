package com.demo.albums.ui.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

   private lateinit var scenario : ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun tearDown() {
    }
}