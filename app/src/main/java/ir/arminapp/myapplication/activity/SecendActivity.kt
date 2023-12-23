package ir.arminapp.myapplication.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import ir.arminapp.myapplication.databinding.ActivitySecendBinding
import ir.arminapp.myapplication.database.room.DBHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecendActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = intent.getStringExtra("username")



    }
}