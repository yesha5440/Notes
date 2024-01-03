package com.example.notes.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.Entity.NotesEntity


@Dao
interface NotesDao {

       @Insert
       fun addNotes(notesEntity: NotesEntity)

       @Query("SELECT * FROM notes")
       fun getNotes() : List<NotesEntity>

       @Update
       fun updateNotes(data : NotesEntity)

       @Query("DELETE FROM notes")
       fun allDelete()

}