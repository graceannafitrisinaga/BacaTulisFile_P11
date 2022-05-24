package id.graceannafitrisinaga.bacatulis_p11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.graceannafitrisinaga.bacatulis_p11.databinding.ActivityMainBinding
import id.graceannafitrisinaga.bacatulis_p11.model.InternalFileRepository
import id.graceannafitrisinaga.bacatulis_p11.model.Note
import id.graceannafitrisinaga.bacatulis_p11.model.NoteRepository

class MainActivity : AppCompatActivity() {
    // menginisialisasi variabel repo yang menggunakan akses private
    // sebagai implementasi menulis ke penyimpanan internal dengan mewakili objek kelas yang mengimplementasikan NoteRepository
    private val repo: NoteRepository by lazy { InternalFileRepository(this) }
    // menginisialisasi variable binding pada ActivityProfileBinding dengan akses private
    private lateinit var binding: ActivityMainBinding

    // class MainActivity menyiapkan instance class binding dalam metode onCreate() agar dapat digunakan dengan suatu aktivitas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mendefinisikan konsep/metode binding untuk menampilkan layout file activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // class binding dengan fungsi setOnClickListener ini dijalankan ketika button Write dengan id btnWrite di klik untuk menulis file baru
        binding.btnWrite.setOnClickListener {
            // dilakukan pengkondisian menggunakan seleksi if untuk men-cek ulang apakah editText dengan id editFileName tidak kosong
            if (binding.editFileName.text.isNotEmpty()) {
                // blok try ini dijalankan untuk menangani munculnya kesalahan inputan teks dalam editText dengan id editFileName yang ingin di proses
                try {
                    // repo akan menjalankan fungsi addNote() yang berisi class Note
                    repo.addNote(
                        // class Note menjalankan binding untuk menyimpan teks yang diinput user pada editText dengan id editFileName dan id editFileName sebagai string
                        Note(
                            binding.editFileName.text.toString(),
                            binding.editTeksCatatan.text.toString()
                        )
                    )
                }
                // sedangkan blok catch digunakan untuk menangani jenis exception, kesalahan yang muncul akan dianggap sebagai objek.
                catch (e: Exception) {
                    // Toast akan menampilkan pesan singkat bahwa file gagal ditulis yang ditampilkan dalam pop up kecil.
                    Toast.makeText(this, "File Write Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
                //class binding akan membersihkan teks yang ada dalam id editFileName dan editTeksCatatan
                binding.editFileName.text.clear()
                binding.editTeksCatatan.text.clear()
            }
            // fungsi else dijalankan ketika editText dengan id editFileName kosong (kondisi seleksi if salah)
            else {
                // menampilkan pesan singkat bahwa nama file harus ditulis yang ditampilkan dalam pop up kecil.
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }

        // class binding dengan fungsi setOnClickListener ini dijalankan ketika button Read dengan id btnRead di klik untuk membaca isi file menggunakan id EditFileName yang di input user
        binding.btnRead.setOnClickListener {
            // dilakukan pengkondisian menggunakan seleksi if untuk men-cek ulang apakah editText dengan id editFileName tidak kosong
            if (binding.editFileName.text.isNotEmpty()) {
                // blok try ini dijalankan untuk menangani munculnya kesalahan inputan teks dalam editFileName yang ingin di baca
                try {
                    // variabel note akan menjalankan fungsi getNote() pada repo supaya class binding dapat menyimpan teks yang diinput user dalam editFileName sebagai string
                    val note = repo.getNote(binding.editFileName.text.toString())
                    //class binding akan menampilkan teks dalam editTeksCatatan sesuai dengan nama file yang diinput pada id editFileName
                    binding.editTeksCatatan.setText(note.noteText)
                }
                // blok catch dijalankan untuk menangani kesalahan yang muncul dan akan dianggap sebagai objek.
                catch (e: Exception) {
                    // menampilkan pesan singkat bahwa file gagal dibaca yang ditampilkan dalam pop up kecil.
                    Toast.makeText(this, "File Read Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
            // fungsi else dijalankan ketika editText dengan id editFileName sebagai nama file yang akan dibaca kosong(kondisi seleksi if salah)
            else {
                // menampilkan pesan singkat bahwa nama file harus ditulis yang ditampilkan dalam pop up kecil.
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }

        // class binding dengan fungsi setOnClickListener ini dijalankan ketika button Delete dengan id btnDelete di klik untuk menghapus file menggunakan id EditFileName yang di input user
        binding.btnDelete.setOnClickListener {
            // dilakukan pengkondisian menggunakan seleksi if untuk men-cek ulang apakah editText dengan id editFileName tidak kosong sebagai nama file yang akan dihapus
            if (binding.editFileName.text.isNotEmpty()) {
                // blok try ini dijalankan untuk menangani munculnya kesalahan inputan teks dalam editFileName yang ingin di hapus
                try {
                    //seleksi if ini dijalankan ketika class binding yang menyimpan teks sebagai string yang diinput user dalam editFileName oleh repo dijalankan fungsi deleteNote untuk menghapus file
                    if (repo.deleteNote(binding.editFileName.text.toString())) {
                        // kemudian ditampilkan pesan singkat bahwa file berhasil dihapus yang ditampilkan dalam pop up kecil
                        Toast.makeText(this, "File Deleted", Toast.LENGTH_LONG).show()
                    }
                    // seleksi else ini dijalankan ketika kondisi pada seleksi if untuk menghapus file benilai salah
                    else {
                        // kemudian ditampilkan pesan singkat bahwa file tidak dapat dihapus yang ditampilkan dalam pop up kecil
                        Toast.makeText(this, "File Could Not Be Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                // blok catch dijalankan untuk menangani kesalahan yang muncul pada inputan teks di editFileName
                catch (e: Exception) {
                    // menampilkan pesan singkat bahwa file gagal dihapus yang ditampilkan dalam pop up kecil.
                    Toast.makeText(this, "File Delete Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
                //class binding akan membersihkan teks yang ada dalam id editFileName dan editTeksCatatan
                binding.editFileName.text.clear()
                binding.editTeksCatatan.text.clear()
            }
            // fungsi else dijalankan ketika editText dengan id editFileName sebagai nama file yang akan dihapus kosong(kondisi seleksi if salah)
            else {
                // menampilkan pesan singkat bahwa nama file harus ditulis yang ditampilkan dalam pop up kecil.
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }
    }
}