package uz.gita.dictionary3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import uz.gita.dictionary3.data.dao.DictionaryDao
import uz.gita.dictionary3.data.entity.DictionaryEntity

@Database(entities = [DictionaryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao

    companion object{
        private var _database:AppDatabase? = null

        fun init(context:Context){
            if (_database == null){
                _database = Room.databaseBuilder(context,AppDatabase::class.java,"app_database.dbb")
                    .createFromAsset("dictionary.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstanse():AppDatabase{
            return _database!!
        }
    }
}