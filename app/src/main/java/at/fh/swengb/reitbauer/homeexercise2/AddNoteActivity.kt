package at.fh.swengb.reitbauer.homeexercise2

import android.content.Intent
import android.icu.util.ValueIterator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var db: NotesRoomDatabase
    private lateinit var listofnotes: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        db = NotesRoomDatabase.getDatabase(this)
        listofnotes = db.noteDao.gettitles()

    }

    fun save_note(v:View)
    {
        val note = Note(addnote_title.text.toString(),addnote_content.text.toString())

        if ( note.title in listofnotes )
        {
            db.noteDao.update(note)
            finish()
        }

        else {

            db.noteDao.insert(note)
            finish()
        }
    }
}
