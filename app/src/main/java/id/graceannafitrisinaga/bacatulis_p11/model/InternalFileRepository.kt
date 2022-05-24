package id.graceannafitrisinaga.bacatulis_p11.model

import android.content.Context
import java.io.File

class InternalFileRepository(var context: Context) :
    //pembuatan kelas NoteRepository
    NoteRepository {

    //deklarasi fungsi noteFile menggunakan akses private yang mengambil teks fileName yang diinput user sebagai string untuk disimpan kedalam File penyimpanan internal yang menggunakan fungsi noteDirectory
    private fun noteFile(fileName: String): File = File(noteDirectory(), fileName)
    //deklarasi fungsi noteDirectory menggunakan akses private dimana context sebagai string
    private fun noteDirectory(): String = context.filesDir.absolutePath

    //mendefinisikan fungsi addNote yang digunakan untuk menambahkan catatan dengan id note ketika button Write di klik
    //dan context beserta fileName yang diinput user dapat menampilkan file output
    override fun addNote(note: Note) {
        context.openFileOutput(note.fileName, Context.MODE_PRIVATE).use { output ->
            output.write(note.noteText.toByteArray())
        }
    }

    //mendefinisikan fungsi getNote yang digunakan untuk mendapatkan catatan dengan id fileName sebagai string ketika button Read di klik
    // teks yang ada dalam variabel text akan ditampilkan sesuai dengan fileName yang diinput user untuk dibuka
    override fun getNote(fileName: String): Note {
        val note = Note(fileName, "")
        context.openFileInput(fileName).use { stream ->
            val text = stream.bufferedReader().use {
                it.readText()
            }
            note.noteText = text
        }
        return note
    }

    //mendefinisikan fungsi deleteNote yang digunakan untuk menghapus catatan dengan id fileName sebagai boolean ketika button Delete di klik
    override fun deleteNote(fileName: String): Boolean {
        //menghapus file berdasarkan fileName yang diinput user menggunakan fungsi delete.
        return noteFile(fileName).delete()
    }
}