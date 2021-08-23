package com.setindia.task.repository

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.*
import java.nio.charset.Charset

const val TAG = "RemoteRepository"
const val downloadFileUrl =
    "https://docs.google.com/spreadsheets/d/e/2PACX-1vRgAkuJOteBzfIBCuedMAFxIOXonw012HIVAMkzWmTzCDBQ62yUbXj3_wGbjVIQAjhmidH62239jJar/pub?gid=273134001&single=true&output=csv"

class RemoteRepository {

    companion object {

        private var languageMap: HashMap<String, List<String>> = HashMap()

        fun downloadTranslatorFile(): HashMap<String, List<String>> {

            var stream: InputStream? = null

            try {

                val url = URL(downloadFileUrl)
                val con: URLConnection = url.openConnection()

                stream = con.getInputStream()

                val bufferedReader = BufferedReader(
                    InputStreamReader(stream, Charset.forName("UTF-8"))
                )

                var line: String?

                do {

                    line = bufferedReader.readLine()

                    if (line == null)
                        break

                    val columns: List<String> = line.split(",")

                    languageMap[columns[0]] = columns


                } while (true)

                return languageMap

            } catch (e: MalformedURLException) {
                Log.e(TAG, " MalformedURLException")
                e.printStackTrace()
            } catch (se: SocketTimeoutException) {
                Log.e(TAG, "SocketTimeoutException")
                se.printStackTrace()
            } catch (e: IOException) {
                Log.e(TAG, "IOException")
                e.printStackTrace()
            } catch (u: URISyntaxException) {
                Log.e(TAG, "URISyntaxException")
                u.printStackTrace()
            } catch (ne: NullPointerException) {
                Log.e(TAG, "NullPointerException cannot retrieve URL")
                ne.printStackTrace()
            } finally {
                try {
                    stream?.close()
                } catch (e: IOException) {
                    Log.e(TAG, "stream close", e.cause)
                }
            }

            return languageMap

        }
    }
}