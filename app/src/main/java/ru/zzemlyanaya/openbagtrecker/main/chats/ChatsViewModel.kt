package ru.zzemlyanaya.openbagtrecker.main.chats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.zzemlyanaya.openbagtrecker.data.model.Resource

class ChatsViewModel : ViewModel() {
    //private val remoteRepository = RemoteRepository()

    var chats = MutableLiveData<List<ChatShortView>>()

//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            chats.postValue(localRepository.getAllUserChats(curUserId))
//        }
//    }

    fun fetchAllChatsRemotely() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.error(data = null, message = "Сервер временно недоступен"))

//        try {
//            val result: Result<List<ChatShortView>> = remoteRepository.getChatsByUser(curUserId)
//            if (result.error == null) {
//                emit(Resource.success(data = result.data))
//                localRepository.insertChat(result.data!!)
//                chats.postValue(result.data)
//            }
//            else
//                emit(Resource.error(data = null, message = result.error))
//        } catch (e: Exception){
//            emit(Resource.error(data = null, message = e.message.toString()))
//        }
    }
}