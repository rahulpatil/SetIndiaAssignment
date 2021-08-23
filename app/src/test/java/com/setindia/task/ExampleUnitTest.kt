package com.setindia.task

import com.setindia.task.repository.RemoteRepository
import com.setindia.task.viewmodel.LanguageViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testFileDownload() {
        val map = RemoteRepository.downloadTranslatorFile()
        print("$map")

        assertNotNull(map)
    }

    @Test
    fun testGetLanguageIndex() {
        val model = LanguageViewModel()
        val map = RemoteRepository.downloadTranslatorFile()
        assertNotNull(map)
        model.setMap(map)
        val index = model.getLanguageIndex("hi_IN")
        assertEquals(3, index)
    }
}