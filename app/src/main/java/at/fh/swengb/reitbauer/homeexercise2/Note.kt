package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes",
    foreignKeys = [ForeignKey(
        entity = Student::class,
        parentColumns = ["id"],
        childColumns = ["studentId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class Note(@PrimaryKey(autoGenerate = true) val id:Long=0,val title: String,val content :String, val studentId: Int) {


}

