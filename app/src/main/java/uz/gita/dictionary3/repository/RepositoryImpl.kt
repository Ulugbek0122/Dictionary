package uz.gita.dictionary3.repository

import android.database.Cursor
import uz.gita.dictionary3.data.AppDatabase
import uz.gita.dictionary3.data.entity.DictionaryEntity

class RepositoryImpl private constructor():Repository {
    private var _database = AppDatabase.getInstanse()

    companion object{
        private var repository:RepositoryImpl? = null
        fun init(){
            if (repository == null) {
                repository = RepositoryImpl()
            }
        }

        fun getInstanse() = repository!!
    }

    override fun getCursorDictionary():Cursor {
       return  _database.dictionaryDao().getDictionaryCursor()
    }

    override fun getCursorSearchEnglish(query:String):Cursor {
        return _database.dictionaryDao().getSearchEnglish(query)
    }

    override fun getCursorSearchUzbek(query:String):Cursor {
        return _database.dictionaryDao().getSearchUzbek(query)
    }

    override fun getFilterDictionaryCursor(query: String):Cursor {
        return _database.dictionaryDao().getFilterCursor(query)
    }

    override fun update(dictionaryEntity: DictionaryEntity) {
        if (dictionaryEntity.isFavourite == 0){
            dictionaryEntity.isFavourite = 1
        }else{
            dictionaryEntity.isFavourite = 0
        }
        _database.dictionaryDao().update(dictionaryEntity)
    }

    override fun getDataById(id: Long): DictionaryEntity {
        return _database.dictionaryDao().getDataById(id)
    }


    override fun getFavourites(query: String) = _database.dictionaryDao().getFavourites(query)

}