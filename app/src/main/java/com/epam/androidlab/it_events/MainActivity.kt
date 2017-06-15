package com.epam.androidlab.it_events

import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var mTextView: TextView
    val rssUrl = "https://events.dev.by/rss"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView = findViewById(R.id.tv1) as TextView
        mTextView.movementMethod = ScrollingMovementMethod()


        /*val httpClient = OkHttpClient()
        val request = Request.Builder().url(rssUrl).build()
        var resp = ""

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("Unexpected code " + response)

                val responseHeaders = response.headers()
                for (i in 0..responseHeaders.size() - 1) {
                    Log.w("MainActivity","responseHeaders ---> ${responseHeaders.name(i)}: ${responseHeaders.value(i)}")
                }

                resp = response.body()?.string() as String
                Log.w("MainActivity","response.body -> $resp")
                this@MainActivity.runOnUiThread { mTextView.text = resp }
            }
        })*/

        if (isNetworkAvailable())
            EPAM_data_helper.getData()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}
