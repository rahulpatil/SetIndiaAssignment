package com.setindia.task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.setindia.task.repository.RemoteRepository
import kotlin.concurrent.thread

const val RESOURCE_ID = "resource_id" // Column header for String id

//ViewModel is optional, can be avoided . Added for future enhancements
class LanguageViewModel(application: Application) : AndroidViewModel(application) {

    //TODO :- Convert to LIVE data
    // Improve this data structure,write model to getId based on TAG
    private var languageMap: HashMap<String, List<String>> = HashMap()

    fun getLanguageMap() {
        thread(start = true) {
            languageMap = RemoteRepository.downloadTranslatorFile()
        }
    }

    //TODO :- Move to Model class once created
    fun getLanguageIndex(languageCode: String?): Int {
        return languageMap[RESOURCE_ID]?.indexOf(languageCode) ?: 1
    }

}