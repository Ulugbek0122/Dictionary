package uz.gita.dictionary3.data.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

import uz.gita.dictionary3.data.entity.DictionaryEntity

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary")
    fun getDictionaryCursor():Cursor

    @Query("SELECT * FROM dictionary WHERE english LIKE :query")
    fun getSearchEnglish(query:String):Cursor

    @Query("SELECT * FROM dictionary WHERE uzbek LIKE :query")
    fun getSearchUzbek(query: String):Cursor

    @Query ("SELECT * FROM dictionary WHERE english LIKE  :query || '%'")
    fun getFilterCursor(query: String):Cursor

    @Update
    fun update(dictionaryEntity: DictionaryEntity)


    @Query("SELECT * FROM dictionary WHERE is_favourite = 1 and english Like :query || '%'")
    fun getFavourites(query:String):Cursor

    @Query("SELECT * FROM dictionary WHERE id = :id")
    fun getDataById(id:Long):DictionaryEntity
}