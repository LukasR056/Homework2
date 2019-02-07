package at.fh.swengb.reitbauer.homeexercise2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_delete_note.*

class DeleteNoteActivity : AppCompatActivity() {




    /* Ich kann diese Klasse nicht löschen weil sonst die Activity AddNote nicht funktioniert, weiß aber nicht
        warum das so ist.
       Bitte diese Klasse und Aktivity einfach ignorieren. Der User kann sie auch nicht aufrufen.
    */




    lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_note)

        db = NotesRoomDatabase.getDatabase(this)

    }

    fun deletenote (v:View){

        db.NoteDao.delete(txt_deletenote_title.text.toString())
        finish()


    }
}
