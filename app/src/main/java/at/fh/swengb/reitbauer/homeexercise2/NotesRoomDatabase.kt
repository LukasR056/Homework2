package at.fh.swengb.reitbauer.homeexercise2

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
/*
@Database(entities = [Note::class], version = 1)
abstract class NotesRoomDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    companion object {
        fun getDatabase(context: Context): NotesRoomDatabase {
            return Room.databaseBuilder(context, NotesRoomDatabase::class.java, "note-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}*/
@Database(entities = [Student::class,Note::class], version = 1)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao
    abstract val NoteDao: NoteDao

    companion object {
        private var INSTANCE: NotesRoomDatabase? = null

        fun getDatabase(context: Context): NotesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): NotesRoomDatabase {
            return Room.databaseBuilder(context, NotesRoomDatabase::class.java, "student-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

