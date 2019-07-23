package cbedoy.streamclient.commenting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cbedoy.streamclient.base.NotificationStateViewModel
import cbedoy.streamclient.models.Message
import kotlinx.coroutines.launch

class CommentingViewModel : NotificationStateViewModel(){

    private val _currentActivity = MutableLiveData<List<Any>>()
    val currentActivity: LiveData<List<Any>> = _currentActivity

    private val _receivedMessage = MutableLiveData<Message>()
    val receivedMessage: LiveData<Message> = _receivedMessage

    fun loadActivity() {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            _currentActivity.postValue(CommentingRepository.prepareActivity())
            _state.postValue(NotificationState.DONE)
        }
    }

    fun sendMessage(messageText: String) {
        if (messageText.length > 3) {
            scope.launch {
                _receivedMessage.postValue(CommentingRepository.sendMessage(messageText))
            }
        }
    }

}