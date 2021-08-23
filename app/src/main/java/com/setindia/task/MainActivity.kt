package com.setindia.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.setindia.task.viewmodel.LanguageViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var languageViewModel: LanguageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()

    }

    private fun initViewModel() {
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        languageViewModel.getLanguageMap()
    }
}