package at.fh.swengb.reitbauer.homeexercise2

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private  lateinit var myAdapter: NoteAdapter

    private lateinit var db: NotesRoomDatabase

    companion object {
        val EXTRA_NOTE_ID = "EXTRA_NOTE_ID"

        val SELECT_NOTE_ID = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setTitle("Notes")
        db = NotesRoomDatabase.getDatabase(applicationContext)

        val sharedPreferences = getSharedPreferences(packageName,Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("ID","nope")
       // Log.i("test",id) zum Testen verwendet worden
        val user = db.studentDao.getStudentByid(id)
       // Log.i("test",id+"1")
        note_text.text= "Notes of ${user.name}, ${user.age} "
       // Log.i("test",id+"2")
        val notesfromstudent = db.NoteDao.findNotessbyStudentid(user.id)

        val list = mutableListOf<Note>(Note(0,"keine Notizen vorhanden","Bitte Notizen hinzufügen",8))

        myAdapter = NoteAdapter(clickListener = {
            val  intent = Intent(this,  AddNoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE_ID,it.id.toInt())
            startActivityForResult(intent, SELECT_NOTE_ID)

        },onlongClickListener = {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Delete Note")
            dialogBuilder.setMessage("Are you sure you want to delete this note ${it.title}?")
            dialogBuilder.setPositiveButton("Yes") { _, _ ->
                db.NoteDao.delete(it.title)
                val notesfromstudenttemp = db.NoteDao.findNotessbyStudentid(user.id)
                myAdapter.updateData(if (notesfromstudenttemp?.note != null) notesfromstudenttemp.note else list )
            }
            dialogBuilder.setNegativeButton("No", null)
            dialogBuilder.show()
        })

        if (notesfromstudent?.note != null )
        {
            NoterecylceView.adapter = myAdapter
            NoterecylceView.layoutManager = LinearLayoutManager(this)
            myAdapter.updateData(if (notesfromstudent?.note != null) notesfromstudent.note else list )}

        else
        {
            myAdapter.updateData(list)
        }

      //  Log.i("TEST", notesfromstudent?.student?.name + notesfromstudent?.student?.id + notesfromstudent?.note?.first()?.title)

    }

   override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences(packageName,Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("ID","nope")
        val user = db.studentDao.getStudentByid(id)
        val notesfromstudent = db.NoteDao.findNotessbyStudentid(user.id)
        val list = listOf<Note>(Note(0,"keine Notizen vorhanden","Bitte Notizen hinzufügen",8))

        if (notesfromstudent?.note != null) {
            myAdapter.updateData(if (notesfromstudent?.note != null) notesfromstudent.note else list )
        }
        else
        {   myAdapter.updateData((list))}
    }

    fun addnote(v:View){


        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)

    }

    fun logout (v:View)
    {
        val sharedPreferences = getSharedPreferences(packageName,Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("ID").apply()
        val intent = Intent(this, MainActivity ::class.java)
        startActivity(intent)
        finish()

    }
}
