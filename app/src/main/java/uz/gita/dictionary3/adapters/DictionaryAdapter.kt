package uz.gita.dictionary3.adapters

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionary3.R
import uz.gita.dictionary3.data.entity.DictionaryEntity

class DictionaryAdapter:RecyclerView.Adapter<DictionaryAdapter.Holder>() {

    private var cursor: Cursor? = null
    private var listener:((DictionaryEntity) -> Unit)? = null
    private var soundListener:((DictionaryEntity) -> Unit)? = null

    fun setItemClickListener(l:(DictionaryEntity) -> Unit){
        listener = l
    }
    fun setSoundClickListener(l:(DictionaryEntity) -> Unit){
        soundListener = l
    }

    fun submitCursor(cursor: Cursor){
        this.cursor = cursor
        notifyDataSetChanged()
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view){

        private var text = itemView.findViewById<TextView>(R.id.word_tv)
        private var image = itemView.findViewById<ImageView>(R.id.star)

        init {
            itemView.setOnClickListener {
                cursor!!.moveToPosition(adapterPosition)
                val dictionaryEntity = cursor!!.getDictionaryEntity()
                listener?.invoke(dictionaryEntity)
            }
            var sound = itemView.findViewById<ImageView>(R.id.sound)
            sound.setOnClickListener {
                cursor!!.moveToPosition(adapterPosition)
                val dictionaryEntity = cursor!!.getDictionaryEntity()
                soundListener?.invoke(dictionaryEntity)
            }
        }

        fun onBind(){
            cursor!!.moveToPosition(adapterPosition)
            val dictionaryEntity = cursor!!.getDictionaryEntity()
            text.setText(dictionaryEntity.english)
            if (dictionaryEntity.isFavourite == 0){
                image.visibility = View.INVISIBLE
            }else{
                image.visibility = View.VISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.onBind()
    }

    override fun getItemCount(): Int {
        return cursor?.let{
            return it.count
        } ?: 0
    }

}

fun Cursor.getDictionaryEntity():DictionaryEntity{
    return DictionaryEntity(
        getLong(0),
        getString(1),
        getString(2),
        getString(3),
        getString(4),
        getString(5),
        getInt(6)
    )
}