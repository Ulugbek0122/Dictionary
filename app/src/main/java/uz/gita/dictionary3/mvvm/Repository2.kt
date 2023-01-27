package uz.gita.dictionary3.mvvm

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dictionary3.data.entity.DictionaryEntity

interface Repository2 {

    val cursorLiveData: LiveData<Cursor>
    val openSecondLiveData:LiveData<DictionaryEntity>
    val openMainLiveData:LiveData<Unit>

    fun changeFavourite(dictionaryEntity: DictionaryEntity)
    fun openMain()

    fun filter(query:String)
}