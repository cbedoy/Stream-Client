package cbedoy.streamclient.faye

import cbedoy.streamclient.providers.UtilsProvider
import cbedoy.streamclient.util.StreamUtil
import com.elirex.fayeclient.FayeClientListener

object FayeRepository {
    fun connect(listener: FayeClientListener) {
        FayeUtil.connect(listener)
    }

    fun disconnect() {
        FayeUtil.pause()
    }

    fun subscribe(channel: String) {
        FayeUtil.subscribe(channel)
    }

    fun online() {
        val nickname = UtilsProvider.getNickname()
        val type = "notifications"

        val flatFeed = StreamUtil.flatFeed(type, nickname)

    }
}