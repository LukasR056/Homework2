package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(student: Note)

    @Update
    fun update(student: Note)

    @Query("DELETE FROM Note where title = :title ")
    fun delete(title: String)

    @Query("SELECT * FROM Note")
    fun findAll(): List<Note>

    @Query("SELECT title FROM Note")
    fun gettitles(): List<String>


}