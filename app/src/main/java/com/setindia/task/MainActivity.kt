package com.setindia.task

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.setindia.task.repository.TAG
import com.setindia.task.viewmodel.LanguageViewModel

const val LANGUAGE_CODE_ENG = "en_IN"
const val LANGUAGE_CODE_CHN = "zh_CN"
const val LANGUAGE_CODE_HIN = "hi_IN"

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

    fun setEnglish(view: View) {
        translateAndSetText(LANGUAGE_CODE_ENG)
    }

    private fun translateAndSetText(languageCode: String) {
        val languageCodeIndex = languageViewModel.getLanguageIndex(languageCode)
        Log.d(TAG, "The index of selected laguage is $languageCode")
    }

    fun setChinese(view: View) {
        translateAndSetText(LANGUAGE_CODE_CHN)
    }

    fun setHindi(view: View) {
        translateAndSetText(LANGUAGE_CODE_HIN)
    }

}