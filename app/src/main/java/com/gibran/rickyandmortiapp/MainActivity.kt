package com.gibran.rickyandmortiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gibran.rickyandmortiapp.navigation.NavigationHost

import com.gibran.rickyandmortiapp.ui.theme.RickyAndMortiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickyAndMortiAppTheme {
                NavigationHost()
            }
        }
    }
}

