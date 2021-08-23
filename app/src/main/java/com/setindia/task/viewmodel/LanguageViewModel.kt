package com.setindia.task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.setindia.task.repository.RemoteRepository
import kotlin.concurrent.thread

const val RESOURCE_ID = "resource_id" // Column header for String id

//ViewModel is optional, can be avoided . Added for future enhancements
class LanguageViewModel(application: Application) : AndroidViewModel(application) {

    //TODO :- Convert to LIVE data
    private var myMap: HashMap<String, List<String>>? = HashMap()

     fun getLanguageMap() {
         thread(start = true) {
             myMap = RemoteRepository.downloadTranslatorFile()
         }
     }

    //TODO :- Move to Model class once created
    fun getLanguageIndex(languageCode: String?): Int {
        return myMap?.get(RESOURCE_ID)?.indexOf(languageCode) ?: 1
    }

    fun getTranslatedString(value:String, index:Int):String? {
        return myMap?.get(value)?.get(index)
    }

}