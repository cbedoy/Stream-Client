package cbedoy.streamclient.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cbedoy.streamclient.StreamRepository
import io.getstream.core.http.Token

class LoginViewModel : ViewModel(){

    var userToken : MutableLiveData<Token> = MutableLiveData()

    fun performLoginWithUser(nickname: String){
        userToken.value = StreamRepository.createToken(nickname)
    }
}