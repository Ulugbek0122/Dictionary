package uz.gita.dictionary3.mvvm

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.dictionary3.data.entity.DictionaryEntity
import uz.gita.dictionary3.repository.Repository
import uz.gita.dictionary3.repository.RepositoryImpl

class PresenterImpl :Presenter,ViewModel() {
    private var repository: Repository = RepositoryImpl.getInstanse()

    override val cursorLiveData = MutableLiveData<Cursor>()
    override val showWordInfoLiveData = MutableLiveData<DictionaryEntity>()
    override val updateItemLiveData = MutableLiveData<Int>()
    override val openFavouriteLiveData = MutableLiveData<Unit>()
    override val backLiveData = MutableLiveData<Unit>()



    override fun changeFavourite(dictionaryEntity: DictionaryEntity) {
        repository.update(dictionaryEntity)
    }

    override fun getDataById(id: Long) {
      showWordInfoLiveData.value =  repository.getDataById(id)
    }

    override fun openFavourites() {
        openFavouriteLiveData.value = Unit
    }

    override fun back() {
        backLiveData.value = Unit
    }

}