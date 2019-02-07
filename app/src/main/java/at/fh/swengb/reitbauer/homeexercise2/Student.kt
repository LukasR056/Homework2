package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.*
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "students")
class Student(var name: String, @ColumnInfo(name = "age", index = true) val age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
