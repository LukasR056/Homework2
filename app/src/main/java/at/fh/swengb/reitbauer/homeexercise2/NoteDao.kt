package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(student: Note)

    @Update
    fun update(student: Note)

    @Query("DELETE FROM notes where title = :title ")
    fun delete(title: String)

    @Query("SELECT * FROM notes")
    fun findAll(): List<Note>

    @Query("SELECT title FROM notes")
    fun gettitles(): List<String>

    @Query("SELECT * from students where id = :searchString")
    fun findNotessbyStudentid(searchString: Long): NotesAndStudent?

    @Query("SELECT * from notes WHERE id = :noteid")
    fun findNoteById(noteid:Long):Note


}