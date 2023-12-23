package ir.arminapp.myapplication.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [LoginData::class],
    version = DBHandler.DATABASE_VERSION
)
abstract class DBHandler : RoomDatabase() {

    abstract fun NotesDAO(): Dao

    companion object {

        private const val DATABASE_NAME = "Notes"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Login"
        const val id = "ID"


        private var INSTANTCE : DBHandler? = null

        fun getDataBase(context: Context): DBHandler {
            if (INSTANTCE == null) {
                INSTANTCE = Room.databaseBuilder(
                    context,
                    DBHandler::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANTCE!!
        }
    }
}