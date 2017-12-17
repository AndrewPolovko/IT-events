package com.epam.androidlab.it_events

import com.epam.androidlab.it_events.data.models.EpamEventsResponse
import com.epam.androidlab.it_events.util.EpamUtil
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    //var mockedResponse = mock(EpamEventResponse::class.java)

    @Test
    @Throws(Exception::class)
    fun addSpacesToEventIsCorrect() {

        val urlString = "https://events.epam.com/events/paginated.json"
        var json: String = ""
        var inputStream: InputStream? = null
        try {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            inputStream.bufferedReader().use { json = it.readText() }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<EpamEventsResponse>(EpamEventsResponse::class.java)
        val noramlResponse = jsonAdapter.fromJson(json)
        EpamUtil.fixSomthing(noramlResponse)
        noramlResponse.events.forEach {
            assertEquals(it.topics.contains("A"), false)
        }

        /*mockedResponse.topics = "java,html,js,git,css,kotlin"
        EpamUtil.addSpacesToEventTopics(mockedResponse)
        assertEquals(mockedResponse.topics, "java, html, js, git, css, kotlin")*/
    }
}