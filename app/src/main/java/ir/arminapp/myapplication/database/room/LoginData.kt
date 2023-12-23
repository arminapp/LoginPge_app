package ir.arminapp.myapplication.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBHandler.TABLE_NAME)
data class LoginData(
    @PrimaryKey(autoGenerate = true)  val id:Int = 0,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "email") var email:String,
    @ColumnInfo(name = "password") val password:String

)
