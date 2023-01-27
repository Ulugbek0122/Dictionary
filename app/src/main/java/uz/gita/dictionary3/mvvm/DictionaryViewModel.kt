package uz.gita.dictionary2.mvvm

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dictionary3.data.entity.DictionaryEntity

interface DictionaryViewModel {
    val cursorLiveData: LiveData<Cursor>
    val showWordInfoLiveData:LiveData<DictionaryEntity>
    val updateItemLiveData:LiveData<Int>

    fun showInfo(dictionaryEntity: DictionaryEntity)

    fun changeFavourite(dictionaryEntity: DictionaryEntity)

    fun filter(query:String)

//    fun showCursors()

}