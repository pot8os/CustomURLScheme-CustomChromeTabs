package net.pot8os.customurlschemecustomchrometabs

/**
 * @author So Nakamura, 2017/09/23
 */
object Singleton {
    var listener: CompletionHandler? = null
}

interface CompletionHandler {
    fun onCompleted(token: String)
}
