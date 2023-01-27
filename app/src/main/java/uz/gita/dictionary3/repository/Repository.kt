package uz.gita.dictionary3.repository

import android.database.Cursor
import uz.gita.dictionary3.data.entity.DictionaryEntity

interface Repository {
    fun getCursorDictionary():Cursor

    fun getCursorSearchEnglish(query:String):Cursor

    fun getCursorSearchUzbek(query:String):Cursor

    fun getFilterDictionaryCursor(query:String):Cursor

    fun update(dictionaryEntity: DictionaryEntity)

    fun getDataById(id:Long):DictionaryEntity

    fun getFavourites(query:String):Cursor
}