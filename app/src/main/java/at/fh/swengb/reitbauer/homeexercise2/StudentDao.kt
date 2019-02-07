package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student): Long

    @Query("SELECT * from students")
    fun selectAllStudents(): List<Student>

    @Query("SELECT * from students where name LIKE '%' || :searchString || '%'")
    fun findByName(searchString: String): Student?

    @Query("SELECT * from students where name LIKE '%' || :searchString || '%'")
    fun getId(searchString: String): Student

    @Query("SELECT * from students where id == :searchString ")
    fun getStudentByid(searchString: String): Student


}