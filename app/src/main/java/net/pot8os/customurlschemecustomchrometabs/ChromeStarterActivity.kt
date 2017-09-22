package net.pot8os.customurlschemecustomchrometabs

import android.app.Activity
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity

/**
 * An activity that starts Custom Chrome Tabs
 * @author So Nakamura, 2017/09/22
 */
class ChromeStarterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY = "key"
        const val CLOSE_ME = "CLOSE_ME"
    }

    override fun onResume() {
        super.onResume()

        if (intent.getStringExtra(EXTRA_KEY) == CLOSE_ME) {
            // It fires from ReceiverActivity
            setResult(Activity.RESULT_CANCELED)
            finish()
            return
        }

        CustomTabsIntent
                .Builder()
                .build()
                .launchUrl(this, Uri.parse("http://localhost:8080/"))
    }
}
