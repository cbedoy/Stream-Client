package cbedoy.streamclient.post

import androidx.lifecycle.MutableLiveData
import cbedoy.streamclient.base.NotificationStateViewModel
import io.getstream.core.models.Activity
import kotlinx.coroutines.launch

class PostViewModel : NotificationStateViewModel(){

    var result : MutableLiveData<Activity> = MutableLiveData()
    var activities : MutableLiveData<List<Activity>> = MutableLiveData()

    fun sendTweet(tweet: String){
        val activity = PostRepository.addActivity(tweet)

        result.value = activity
    }

    fun loadActivities() {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            activities.postValue(PostRepository.loadActivities())
            _state.postValue(NotificationState.DONE)
        }
    }

    fun handleReactionFromActivity(reaction: String, activity: Activity) {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            PostRepository.addReactionToActivity(reaction, activity)
            _state.postValue(NotificationState.DONE)
        }
    }
}