package cbedoy.streamclient.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.getstream.core.models.EnrichedActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class NotificationStateViewModel : ViewModel(){

    private var job = Job()
    private var listenerJob = Job()

    val _state = MutableLiveData<NotificationState>()
    val state: LiveData<NotificationState> = _state
    val scope = CoroutineScope(job + Dispatchers.IO )
    val listenerScope = CoroutineScope(listenerJob + Dispatchers.Main)

    enum class NotificationState {
        LOADING, NONE, ERROR, DONE
    }

    init {
        _state.postValue(NotificationState.NONE)
    }

    override fun onCleared() {
        super.onCleared()

        scope.cancel()
    }
}