package at.fh.swengb.reitbauer.homeexercise2

import android.content.Context
import android.content.Intent
import android.icu.util.ValueIterator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowId
import at.fh.swengb.reitbauer.homeexercise2.NoteListActivity.Companion.EXTRA_NOTE_ID
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var db: NotesRoomDatabase
    private lateinit var listofnotes: List<String>
    private lateinit var userid:String
    var noteid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        db = NotesRoomDatabase.getDatabase(this)
        setTitle("Add Note")

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        var id = sharedPreferences.getString("ID","nope")
        userid = id

       // Log.i("NOTE",noteid.toString()) zum Testen verwendet worden
        noteid = intent.getIntExtra(EXTRA_NOTE_ID, 0)

        if (noteid > 0)
        {
            var selectedNote = db.NoteDao.findNoteById(noteid.toLong())
            addnote_title.setText(selectedNote.title)
            addnote_content.setText(selectedNote.content)
        }
        else {
            //Log.i("NOTE",noteid.toString())
        }
        //Log.i("NOTE",noteid.toString())

    }

    fun save_note(v:View)
    {
        val note = Note(noteid.toLong() ,addnote_title.text.toString(),addnote_content.text.toString(),userid.toInt())
        listofnotes = db.NoteDao.gettitles()
        if ( noteid == 0  )
        {

            db.NoteDao.insert(note)
            finish()


        }

        else {
            db.NoteDao.update(note)
            finish()

        }
    }
    fun share(v:View)
    {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, addnote_content.text)
        intent.type = "text/plain"
        val chooserIntent = Intent.createChooser(intent, "Select an App you want to share with")
        startActivity(intent)
    }
}
