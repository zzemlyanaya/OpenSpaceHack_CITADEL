package ru.zzemlyanaya.openbagtrecker.main.tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.zzemlyanaya.openbagtrecker.data.model.Bug
import ru.zzemlyanaya.openbagtrecker.data.model.Resource
import ru.zzemlyanaya.openbagtrecker.data.model.UserShortView

class TrackerViewModel : ViewModel() {
//    private val repository = RemoteRepository()
//    private val localRepository: LocalRepository

    var isUpdated = false

    private val list = listOf(
        Bug(0, 0, "CITADEL", 0, "Security", "12.10.20", "12:22", "CIT AD EL",
            "Google Pixel 3XL", "Android 10", "v12.2.45",
            "Видно персональные данные", "Open profile"),
        Bug(1, 1, "OpenSpace Bank",0, "Spelling", "6.10.20", "07:20", "Open Space Bank",
            "Samsung S20", "Android 9", "v12.2.45",
            "Опечатка в настройках", "In the 2nd settings"),
        Bug(2, 3, "Oreo", 1, "Minor", "9.11.20", "18:34", "One More FIO",
            "Xiomi Mi7 Pro", "Android 7.1.1", "v12.2.47",
            "Истории не закрываются и блокируют приложение", "When opening stories"),
        Bug(3, 2, "Fus Ro Da", 1, "Major", "14.11.20", "13:27", "One More FIO",
            "Xiomi Mi9", "Android 7.1.1", "v12.2.47",
            "При смены пароля исчезают сохранённые истории ", "When changing the password")
    )

    fun fetchTop3LeaderBoard() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.success(data = listOf(
            UserShortView(10, "OpenSpaceCat", 1, 1193032),
            UserShortView(0, "CITADEL", 0, 15934),
            UserShortView(12, "Oreo", 0, 12117)
        )))
    }

    fun fetchAllBugs() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.success(data = list))
//        try {
//            val result: Result<List<Bug>> = repository.getAllBugs()
//            if (result.error == null) {
//                isUpdated = true
//                emit(Resource.success(data = result.data))
//                localRepository.insertProject(result.data!!)
//            }
//            else {
//                emit(Resource.error(data = null, message = result.error))
//                isUpdated = false
//            }
//        } catch (e: Exception){
//            isUpdated = false
//            emit(Resource.error(data = null, message = e.message.toString()))
//        }
    }

//    fun fetchBugsByUser(id: Int) = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            val result: Result<List<Project>> = repository.getBugsByUser(id)
//            if (result.error == null) {
//                isUpdated = true
//                emit(Resource.success(data = result.data))
//            }
//            else {
//                emit(Resource.error(data = null, message = result.error))
//                isUpdated = false
//            }
//        } catch (e: Exception){
//            isUpdated = false
//            emit(Resource.error(data = null, message = e.message.toString()))
//        }
//    }


    fun fetchAllBugsLocally() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.success(data = list))

//        try {
//            emit(Resource.success(localRepository.getAllBugs()))
//        } catch (e: Exception){
//            emit(Resource.error(data = null, message = e.message.toString()))
//        }
    }

    fun fetchMyBugsLocally(id: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        emit(Resource.success(data =  list[0]))
//        try {
//            emit(Resource.success(localRepository.getUserWithTheirBugs(id)))
//        } catch (e: Exception){
//            emit(Resource.error(data = null, message = e.message.toString()))
//        }
    }


//    fun updateMyBugs(userId: Int, projects: List<Bug>) = run {
//        CoroutineScope(Dispatchers.IO).launch {
//            for(i in projects)
//                localRepository.insertUserWithTheirProject(UserWithTheirBugs(userId, i.id))
//        }
//    }
}