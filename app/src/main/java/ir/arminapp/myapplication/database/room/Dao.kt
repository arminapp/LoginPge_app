package ir.arminapp.myapplication.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow



@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(data: LoginData):Long

    @Query("SELECT * FROM ${DBHandler.TABLE_NAME} where username = :username")
    fun getDataWithUsername(username:String ):Flow<List<LoginData>>

    @Query("SELECT * FROM ${DBHandler.TABLE_NAME} where email = :email")
    fun getDataWithEmail(email:String ):Flow<List<LoginData>>

    @Query("SELECT * FROM ${DBHandler.TABLE_NAME} ")
    fun getallData():Flow<List<LoginData>>



}