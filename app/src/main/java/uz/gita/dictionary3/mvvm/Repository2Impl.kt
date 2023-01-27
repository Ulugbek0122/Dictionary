package uz.gita.dictionary3.mvvm

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.dictionary3.data.entity.DictionaryEntity
import uz.gita.dictionary3.repository.Repository
import uz.gita.dictionary3.repository.RepositoryImpl

class Repository2Impl:Repository2,ViewModel() {

    private var repository: Repository = RepositoryImpl.getInstanse()

    override val cursorLiveData = MutableLiveData<Cursor>()
    override val openSecondLiveData = MutableLiveData<DictionaryEntity>()
    override val openMainLiveData = MutableLiveData<Unit>()

    private var query:String = ""


   init {
       cursorLiveData.value = repository.getFavourites(query)
   }

    override fun changeFavourite(dictionaryEntity: DictionaryEntity) {
        openSecondLiveData.value = dictionaryEntity
    }



    override fun openMain() {
       openMainLiveData.value = Unit
    }

    override fun filter(query: String) {
        if (query != "1"){
            this.query = query
        }
        cursorLiveData.value = repository.getFavourites(this.query)
    }


}