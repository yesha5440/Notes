package com.example.notes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.Dao.NotesDao
import com.example.notes.Entity.NotesEntity


@Database(entities = [NotesEntity::class], version = 1)
abstract class dbHelper : RoomDatabase()  {


    companion object{
        fun init(context: Context) : dbHelper{
            var db = Room.databaseBuilder(context,dbHelper::class.java,"Notes")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

    abstract fun notes() : NotesDao

}