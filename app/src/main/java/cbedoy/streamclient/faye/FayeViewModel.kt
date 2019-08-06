package cbedoy.streamclient.faye

import androidx.lifecycle.MutableLiveData
import cbedoy.streamclient.base.NotificationStateViewModel
import com.elirex.fayeclient.FayeClient
import com.elirex.fayeclient.FayeClientListener
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class FayeViewModel : NotificationStateViewModel(), AnkoLogger {

    val status = MutableLiveData<Boolean>()
    val messages = MutableLiveData<String>()
    private val channels = HashSet<String>()

    fun connect() {
        scope.launch {
            FayeRepository.connect(listener)
        }
    }

    fun disconnect() {
        FayeRepository.disconnect()
    }

    fun subscribe(channel: String) {
        channels.add(channel)

        FayeRepository.subscribe(channel)
    }

    fun online() {
        FayeRepository.online()
    }

    private val listener = object : FayeClientListener {
        override fun onReceivedMessage(_client: FayeClient?, message: String?) {
            info(_client)

            messages.postValue(message)
        }

        override fun onConnectedServer(_client: FayeClient?) {
            info(_client)

            status.postValue(true)
        }

        override fun onDisconnectedServer(_client: FayeClient?) {
            info(_client)

            status.postValue(false)
        }
    }

}