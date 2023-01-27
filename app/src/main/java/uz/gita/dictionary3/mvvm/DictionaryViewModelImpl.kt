package uz.gita.dictionary2.mvvm

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.dictionary3.repository.Repository
import uz.gita.dictionary3.data.entity.DictionaryEntity
import uz.gita.dictionary3.repository.RepositoryImpl

class DictionaryViewModelImpl() :DictionaryViewModel,ViewModel() {
    private var repository: Repository = RepositoryImpl.getInstanse()


    override val cursorLiveData = MutableLiveData<Cursor>()
    override val showWordInfoLiveData = MutableLiveData<DictionaryEntity>()
    override val updateItemLiveData = MutableLiveData<Int>()

    private var query:String = ""

    init {
        cursorLiveData.value = repository.getFilterDictionaryCursor(query)
    }

    override fun showInfo(dictionaryEntity: DictionaryEntity) {
        showWordInfoLiveData.value = dictionaryEntity
    }

    override fun changeFavourite(dictionaryEntity: DictionaryEntity) {
        repository.update(dictionaryEntity)

    }

    override fun filter(query: String) {
        if (query != "1"){
            this.query = query
        }
        cursorLiveData.value = repository.getFilterDictionaryCursor(this.query)
    }

//    override fun showCursors() {
//        cursorLiveData.value = repository.getFilterDictionaryCursor("")
//    }

    init {
        cursorLiveData.value = repository.getCursorDictionary()
    }



}