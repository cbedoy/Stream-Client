package cbedoy.streamclient.explorer

import androidx.lifecycle.MutableLiveData
import cbedoy.streamclient.base.NotificationStateViewModel
import io.getstream.core.models.Activity
import kotlinx.coroutines.launch

class ExplorerViewModel : NotificationStateViewModel(){
    var activities : MutableLiveData<List<Activity>> = MutableLiveData()

    fun loadExplorer(users: List<String>){
        scope.launch {
            _state.postValue(NotificationState.LOADING)
            activities.postValue(ExplorerRepository.loadExplorer(users))
            _state.postValue(NotificationState.DONE)
        }
    }
}