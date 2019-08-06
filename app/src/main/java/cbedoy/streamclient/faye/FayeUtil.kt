package cbedoy.streamclient.faye

import com.elirex.fayeclient.FayeClient
import com.elirex.fayeclient.FayeClientListener
import com.elirex.fayeclient.MetaMessage
import org.jetbrains.anko.AnkoLogger

object FayeUtil : AnkoLogger {
    lateinit var client : FayeClient

    fun connect(listener: FayeClientListener) {
        if (!::client.isInitialized){
            val meta = MetaMessage()
            client = FayeClient("wss://faye.getstream.io/faye", meta)
            client.listener = listener
        }
        if (!client.isConnectedServer)
            client.connectServer()
    }

    fun pause() {
        if (client.isConnectedServer)
            client.disconnectServer()
    }

    fun subscribe(channel: String) {
        if (!client.isConnectedServer) client.subscribeChannel(channel)
    }
}