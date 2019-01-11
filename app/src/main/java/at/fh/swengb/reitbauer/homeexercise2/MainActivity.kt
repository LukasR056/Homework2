package at.fh.swengb.reitbauer.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveuser(v:View)
    {
        if ( main_name.text.isNotEmpty() && main_age.text.isNotEmpty() ) {

            var name = main_name.text.toString()
            var age = main_age.text.toString()


            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

            sharedPreferences.edit().putString("Name", name).apply()
            sharedPreferences.edit().putString("Age", age).apply()

            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
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
