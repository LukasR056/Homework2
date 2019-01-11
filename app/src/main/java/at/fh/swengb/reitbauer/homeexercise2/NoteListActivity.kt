package at.fh.swengb.reitbauer.homeexercise2

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        db = NotesRoomDatabase.getDatabase(applicationContext)
        myAdapter = NoteAdapter()

        NoterecylceView.layoutManager = LinearLayoutManager(this)
        NoterecylceView.adapter = myAdapter

        myAdapter.updateData(db.noteDao.findAll())

        //myAdapter.updateData(myDb.questiondao.findAllQuestions())

        val listofnotes = db.noteDao.findAll()

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val name = sharedPreferences.getString("Name","NoName")
        val age = sharedPreferences.getString("Age","NoAge")


        note_text.text = "Note for ${name}, ${age}"
    }

    fun addnote(v:View){

        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)

    }
    fun deletenote (v:View)
    {
        val intent = Intent(this, DeleteNoteActivity ::class.java)
        startActivity(intent)

    }
}
