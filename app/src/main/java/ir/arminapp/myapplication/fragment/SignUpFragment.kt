package ir.arminapp.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import ir.arminapp.myapplication.activity.SecendActivity
import ir.arminapp.myapplication.databinding.FragmentSignUpBinding
import ir.arminapp.myapplication.database.room.DBHandler
import ir.arminapp.myapplication.database.room.LoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    //اینجا مشکل داره


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater)


        binding.btnSave.setOnClickListener {
            var username = binding.username.text.toString().trim()
            var email = binding.emailText.text.toString().trim()
            var pass = binding.passwordText.text.toString().trim()

            if (isValid(username, email, pass)) {
                saveData()

            }



        }


        return binding.root
    }

    fun isValid(username: String, email: String, pass: String): Boolean {
        var validUser = if (username.isEmpty()) {
            binding.usernameLayout.error = "required field"
            false
        } else {
            true
        }

        var validPass = if (pass.isEmpty()) {
            binding.passwordLayout.error = "required field"
            false
        } else
            true

        var validEmail = when{
            email.isEmpty() ->{
                binding.emailLayout.error = "required field"
                false
            }
            !email.endsWith("@gmail.com")->{
                binding.emailLayout.error = "not valid email"
                false
            }

            else -> {
                true
            }
        }


        return validEmail && validPass && validUser
    }

    fun saveData() {

        var db = DBHandler.getDataBase(requireContext())
        lifecycleScope.launch {
            var intent = Intent(requireContext(), SecendActivity::class.java)
            withContext(Dispatchers.IO) {

                try {
                    db.NotesDAO()
                        .insert(
                            LoginData(
                                username = binding.username.text.toString().trim(),
                                email = binding.emailText.text.toString().trim(),
                                password = binding.passwordText.text.toString().trim()
                            )
                        )


                } catch (e: Exception) {
                    toast(e.toString())
                }
                val username =
                    db.NotesDAO().getDataWithEmail(binding.emailText.text.toString().trim())
                withContext(Dispatchers.Main) {

                    username.collect {
                        it.forEach {
                            intent.putExtra("username", it.username)
                        }
                        startActivity(intent)
                    }
                }

            }
        }

    }

    fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }


}