package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class NotesAndStudent() {
    @Embedded
    lateinit var student: Student
    @Relation(entity = Note::class, entityColumn = "studentId", parentColumn = "id")
    lateinit var note: List<Note>
}