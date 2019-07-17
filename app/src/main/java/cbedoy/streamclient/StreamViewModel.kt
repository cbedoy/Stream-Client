package cbedoy.streamclient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.getstream.core.http.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class StreamViewModel : ViewModel(){

    private var job = Job()
    private var scope = CoroutineScope(job + Dispatchers.IO)

    var userToken : MutableLiveData<Token> = MutableLiveData()

    fun generateToken(userId: String){
        userToken.value = StreamRepository.createToken(userId)
    }
}