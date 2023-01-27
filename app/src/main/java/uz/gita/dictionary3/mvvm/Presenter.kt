package uz.gita.dictionary3.mvvm

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dictionary3.data.entity.DictionaryEntity

interface Presenter {

    val cursorLiveData: LiveData<Cursor>
    val showWordInfoLiveData: LiveData<DictionaryEntity>
    val updateItemLiveData: LiveData<Int>
    val openFavouriteLiveData:LiveData<Unit>
    val backLiveData:LiveData<Unit>


    fun changeFavourite(dictionaryEntity: DictionaryEntity)

    fun getDataById(id:Long)

    fun openFavourites()

    fun back()
}