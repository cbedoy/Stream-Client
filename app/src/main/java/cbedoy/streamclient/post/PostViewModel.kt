package cbedoy.streamclient.post

import androidx.lifecycle.MutableLiveData
import cbedoy.streamclient.base.NotificationStateViewModel
import io.getstream.core.models.Activity
import io.getstream.core.models.EnrichedActivity
import kotlinx.coroutines.launch

class PostViewModel : NotificationStateViewModel(){

    var result : MutableLiveData<Activity> = MutableLiveData()
    var activities : MutableLiveData<List<EnrichedActivity>> = MutableLiveData()

    fun sendTweet(tweet: String){
        val activity = PostRepository.addActivity(tweet)

        result.value = activity
    }

    fun loadActivities() {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            activities.postValue(PostRepository.loadEnrichedActivities())
            _state.postValue(NotificationState.DONE)
        }
    }

    fun handleReactionFromActivity(reaction: String, activityId: String) {
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            PostRepository.addReactionToActivity(reaction, activityId)
            _state.postValue(NotificationState.DONE)
        }
    }
}