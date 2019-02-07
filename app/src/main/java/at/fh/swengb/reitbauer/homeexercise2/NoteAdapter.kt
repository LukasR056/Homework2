package at.fh.swengb.reitbauer.homeexercise2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.FieldPosition

class NoteAdapter(val clickListener: (note: Note) -> Unit,val onlongClickListener:(note:Note) -> Unit ): RecyclerView.Adapter<NoteViewHolder>(){

    var noteList = listOf<Note>()

    fun updateData(list: List<Note>)
    {
        noteList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_note,parent,false)
        return NoteViewHolder(itemView,clickListener,onlongClickListener)
        }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        viewHolder.bindnote(note)
    }

}

class NoteViewHolder(itemView:View,val clickListener: (note: Note) -> Unit,val onlongClickListener: (note:Note) -> Unit):RecyclerView.ViewHolder(itemView){
    fun bindnote (note:Note)
    {
        itemView.item_title.text = note.title
        itemView.item_content.text = note.content
        itemView.setOnClickListener {
            clickListener(note)
        }
        itemView.setOnLongClickListener{
            onlongClickListener(note)
            true
        }
    }
}