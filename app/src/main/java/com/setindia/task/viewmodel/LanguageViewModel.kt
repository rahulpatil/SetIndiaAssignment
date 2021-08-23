package com.setindia.task.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.setindia.task.repository.RemoteRepository
import kotlin.concurrent.thread

const val RESOURCE_ID = "resource_id" // Column header for String id

//ViewModel is optional, can be avoided . Added for future enhancements
class LanguageViewModel : ViewModel() {

    //TODO :- Convert to LIVE data
    // Improve this data structure,write model to getId based on TAG
    private var languageMap: HashMap<String, List<String>> = HashMap()

    fun getLanguageMap() {
        thread(start = true) {
            languageMap = RemoteRepository.downloadTranslatorFile()
            Log.d("Rahul", "nap is $languageMap")
        }
    }

    //TODO :- Move to Model class once created
    fun getLanguageIndex(languageCode: String?): Int {
        return languageMap[RESOURCE_ID]?.indexOf(languageCode) ?: 1
    }

    fun getTranslatedString(text: String, index: Int): String {
        return languageMap[getMapKey(text)]?.get(index) ?: text
    }

    //TODO :- Ideally if we get resource name of String Set to view, then this logic not required
    private fun getMapKey(text: String): String? {
        for ((key, valueList) in languageMap.entries) {
            for (value in valueList) {
                if (value == text) {
                    return key
                }
            }

        }
        return null
    }

    @VisibleForTesting
    fun setMap(map: HashMap<String, List<String>>) {
        languageMap = map
    }

}