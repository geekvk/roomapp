package com.example.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class NoteAdapter(
    val context:Context,
    val noteOnClick: NoteOnClick,
    val noteOnClickDelete: OnClickDelete

) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val noteList = ArrayList<Note>()

    inner class NoteViewHolder(itemVew:View) : ViewHolder(itemVew){
        val noteTitle = itemVew.findViewById<TextView>(R.id.noteTitle)
        val desc = itemVew.findViewById<TextView>(R.id.desc)
        val delete = itemVew.findViewById<ImageView>(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemVew = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemVew)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteTitle.text = noteList[position].title
        holder.desc.text = noteList[position].description

        holder.delete.setOnClickListener {
            noteOnClickDelete.onDeleteIconClicked(noteList[position])
        }
        holder.itemView.setOnClickListener {
            noteOnClick.onIconClicked(noteList[position])
        }
    }

    fun updateList(newList: List<Note>){
        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }
    interface NoteOnClick{
        fun onIconClicked(note:Note)

    }
    interface OnClickDelete{
        fun onDeleteIconClicked(note:Note)
    }
}