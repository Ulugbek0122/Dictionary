package uz.gita.dictionary3.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "dictionary")
data class DictionaryEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var english:String,
    var type:String,
    var transcript:String,
    var uzbek:String,
    var countable:String,
    @ColumnInfo(name = "is_favourite")
    var isFavourite:Int
):Serializable
