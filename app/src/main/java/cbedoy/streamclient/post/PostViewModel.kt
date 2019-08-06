package cbedoy.streamclient.post

import androidx.lifecycle.MutableLiveData
import cbedoy.streamclient.base.NotificationStateViewModel
import io.getstream.core.models.Activity
import io.getstream.core.models.EnrichedActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class PostViewModel : NotificationStateViewModel(), AnkoLogger{

    var result : MutableLiveData<Activity> = MutableLiveData()
    var activities : MutableLiveData<List<EnrichedActivity>> = MutableLiveData()
    var selectedActivity: MutableLiveData<Boolean> = MutableLiveData()

    var counter = 1
    private var onListener = false

    init {
        runListener()
    }


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

    fun openCommentingFromActivity(activity: EnrichedActivity) {
        selectedActivity.value = PostRepository.selectActivity(activity)
    }

    private fun runListener(){
        scope.launch {
            while (onListener){
                _state.postValue(NotificationState.LOADING)

                val loadEnrichedActivities = PostRepository.loadEnrichedActivities()


                _state.postValue(NotificationState.DONE)
                delay(15_00)
            }
        }
    }

    fun subscribe() {
        onListener = true
    }

    fun unsubscribe(){
        onListener = false
    }
}