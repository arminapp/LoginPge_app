package ir.arminapp.myapplication.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ir.arminapp.myapplication.fragment.LoginFragment
import ir.arminapp.myapplication.R
import ir.arminapp.myapplication.fragment.SignUpFragment
import ir.arminapp.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            binding.login.setTextColor(Color.parseColor("#2C00A5"))
            binding.SignUpBtn.setTextColor(Color.parseColor("#FF000000"))
            replaceFragment(LoginFragment())
        }
        binding.SignUpBtn.setOnClickListener {
            binding.SignUpBtn.setTextColor(Color.parseColor("#2C00A5"))
            binding.login.setTextColor(Color.parseColor("#FF000000"))
            replaceFragment(SignUpFragment())
        }
        binding.SignUpBtn.setTextColor(Color.parseColor("#2C00A5"))
        binding.login.setTextColor(Color.parseColor("#FF000000"))
        replaceFragment(SignUpFragment())


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)




    }
    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

}