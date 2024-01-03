package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.Adapters.NotesAdapter
import com.example.notes.Database.dbHelper
import com.example.notes.Entity.NotesEntity
import com.example.notes.databinding.ActivityAddNotesBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch

import java.text.SimpleDateFormat
import java.util.Date

class AddNotesActivity : AppCompatActivity() {

   lateinit var binding : ActivityAddNotesBinding
   lateinit var db : dbHelper
   lateinit var adapter: NotesAdapter
   var choosecolor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = dbHelper.init(this)

        binding.edtSave.setOnClickListener {
            var title = binding.edtTitle.text.toString()
            var text = binding.edtText.text.toString()
            var format = SimpleDateFormat("DD/MM/YYYY hh:mm:ss a ")
            var current = format.format(Date())
            var data = NotesEntity(title, text, current,false,choosecolor)
            db.notes().addNotes(data)
            adapter.update(filternote(db.notes().getNotes()))
            finish()
        }
        binding.chooseColor.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setColorSwatch(ColorSwatch._300)
                .setColorListener { color : Int, colorHex : String ->
                    this.choosecolor = color
                }
                .show()
        }

    }
    private fun filternote(list : List<NotesEntity>) : ArrayList<NotesEntity>{
        var newlist = ArrayList<NotesEntity>()
        for (l in list) {
            if (l.pin) {
                newlist.add(l)
            }
        }
        for (l in list) {
            if (!l.pin) {
                newlist.add(l)
            }
        }
        return newlist
    }

}