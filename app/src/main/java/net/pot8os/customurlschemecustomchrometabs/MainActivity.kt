package net.pot8os.customurlschemecustomchrometabs

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fi.iki.elonen.NanoHTTPD
import net.pot8os.customurlschemecustomchrometabs.databinding.ActivityMainBinding
import net.pot8os.customurlschemecustomchrometabs.server.LocalServer

/**
 * A main activity
 *
 * @author So Nakamura, 2017/09/22
 */
class MainActivity : AppCompatActivity() {

    private lateinit var server: NanoHTTPD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        server = LocalServer()

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main
        )
        binding.chromeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ChromeStarterActivity::class.java)
            startActivity(intent)
        }

        // Set a listener that runs when I get a token.
        Singleton.listener = object : CompletionHandler {
            override fun onCompleted(token: String) {
                binding.tokenText.text = "Hey! I got a token [$token]."
            }
        }
    }

    override fun onDestroy() {
        server.stop()
        super.onDestroy()
    }
}
