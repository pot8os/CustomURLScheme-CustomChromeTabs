package net.pot8os.customurlschemecustomchrometabs

import android.app.Activity
import android.content.Intent
import android.net.UrlQuerySanitizer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * An activity that runs when it detects an url that has custom scheme
 *
 * @author So Nakamura, 2017/09/22
 */
class ReceiverActivity : AppCompatActivity() {

    private val code = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nextIntent = Intent(this, ChromeStarterActivity::class.java).apply {
            // Here is the key function to close Custom Chrome Tabs from code
            // that it can reuse existing activity and let it finish
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(ChromeStarterActivity.EXTRA_KEY, ChromeStarterActivity.CLOSE_ME)
        }
        startActivityForResult(nextIntent, code)
    }

    // It runs after ChromeStarterActivity finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == code && resultCode == Activity.RESULT_CANCELED) {
            val callback = UrlQuerySanitizer(intent.data.toString())
            if (callback.hasParameter("token")) {
                // Run the listener that I set at MainActivity
                Singleton.listener?.onCompleted(callback.getValue("token"))
            }
            finish()
        }
    }
}
