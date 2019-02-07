package at.fh.swengb.reitbauer.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Notes")
        /*val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val test = sharedPreferences.getString("ID","hat funktioniert")
        Log.i("TEST",test) zum Testen verwendet worden */

        val sharedPreferences = getSharedPreferences(packageName,Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("ID","-1")

        if (id?.toInt() != -1)
        {
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
1
        }


    }



    fun saveuser(v:View)
    {
        if ( main_name.text.isNotEmpty() && main_age.text.isNotEmpty() ) {

            var name = main_name.text.toString()
            var age = main_age.text.toString()
            db = NotesRoomDatabase.getDatabase(applicationContext)

            var usercheck = db.studentDao.findByName(name)


            if (usercheck == null) {

                val newuser = Student(name,age.toInt())
                db.studentDao.insert(newuser)

                val loginuser = db.studentDao.getId(newuser.name)
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("ID", loginuser.id.toString()).apply()
                Log.i("TEST","neuer user")
                Log.i("TEST",loginuser.id.toString())
                }
            else{
                val loginuser = db.studentDao.getId(usercheck.name)
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("ID", loginuser.id.toString()).apply()
                Log.i("TEST","schon da user")
                Log.i("TEST",loginuser.id.toString())
            }

            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            finish()

        }
        else
        {
           if (main_name.text.isEmpty() ){

               main_name.setHint("Bitte Namen eingeben")
           }
            if (main_age.text.isEmpty()){
                main_age.setHint("Bitte Alter eingeben")
            }

        }

    }
}
