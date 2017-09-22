package net.pot8os.customurlschemecustomchrometabs.server

import fi.iki.elonen.NanoHTTPD

/**
 * A minimal server specification.
 *
 * @author So Nakamura, 2017/09/22
 */
class LocalServer : NanoHTTPD(8080) {

    private val token = "_super_confidential_token_"

    init {
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
    }

    override fun serve(session: IHTTPSession?): Response {
        // Returned static web page just for test
        val response = """
            <!doctype html>
            <html class="no-js">
            <head>
              <meta name="viewport" content="width=device-width, initial-scale=1">
              <style type="text/css">
              <!--
                div { text-align: center; }
                input { width: 15em; height: 2em; }
              -->
              </style>
            </head>
            <body>
              <h3>Welcome to the foobar token provider.</h3>
              <div>
                <form>
                  <input type="button" value="Give me token" onClick="onButtonClicked()" />
                </form>
              </div>
              <script type="text/javascript">
                function onButtonClicked() {
                  location.href = "pot8os://result?token=$token";
                }
              </script>
            </body>
            </html>
            """
        return newFixedLengthResponse(response)
    }
}