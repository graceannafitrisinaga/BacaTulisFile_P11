package id.graceannafitrisinaga.bacatulis_p11.model

//implementasi kelas NoteRepository yang digunakan untuk menggunakan fungsionalitas metode
interface NoteRepository {
    //fungsi yang digunakan untuk menambahkan catatan dengan id note
    fun addNote(note: Note)
    //fungsi yang digunakan untuk mendapatkan catatan dengan id fileName sebagai string
    fun getNote(fileName: String): Note
    //fungsi yang digunakan untuk menghapus catatan dengan id fileName sebagai boolean
    fun deleteNote(fileName: String): Boolean
}
