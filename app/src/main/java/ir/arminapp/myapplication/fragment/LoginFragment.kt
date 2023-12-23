package ir.arminapp.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import ir.arminapp.myapplication.activity.SecendActivity
import ir.arminapp.myapplication.databinding.FragmentLoginBinding
import ir.arminapp.myapplication.database.room.DBHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment() : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)

        binding.btnSave.setOnClickListener {
            var inputData = binding.inputText.text.toString().trim()
            var pass = binding.passwprdText.text.toString().trim()
            if (isValid(inputData, pass)) {
                login(inputData)

            }
        }




        return binding.root
    }

    fun isValid(inputData: String, pass: String): Boolean {

        var emailValid = if (inputData.isEmpty()) {
            binding.inputLayout.error = "required field"
            false
        } else
            true
        var passValid = if (pass.isEmpty()) {
            binding.passwordLayout.error = "required field"
            false
        } else
            true
        return emailValid && passValid
    }

    fun login(inputData: String) {


        var intent = Intent(requireContext(), SecendActivity::class.java)
        if (inputData.endsWith("@gmail.com")) {
            loginWithEmail(inputData)
        }else
            loginWithUsername(inputData)


    }


    fun loginWithEmail(email: String) {

        var db = DBHandler.getDataBase(requireContext())
        lifecycleScope.launch {
            var intent = Intent(requireContext(), SecendActivity::class.java)
            withContext(Dispatchers.IO) {
                var data =
                    db.NotesDAO().getDataWithEmail(email)
                withContext(Dispatchers.Main) {
                    data.collect {
                        it.forEach {
                            if (binding.passwprdText.text.toString().trim() == it.password) {
                                intent.putExtra("username", it.username)
                                startActivity(intent)
                            } else {
                                binding.passwordLayout.error = "wrong password"
                                binding.inputLayout.error = "wrong username or email"
                            }
                        }

                    }

                }

            }
        }


    }

    fun loginWithUsername(userName: String) {


        var db = DBHandler.getDataBase(requireContext())
        lifecycleScope.launch {
            var intent = Intent(requireContext(), SecendActivity::class.java)
            withContext(Dispatchers.IO) {
                var data = db.NotesDAO().getDataWithUsername(userName)
                withContext(Dispatchers.Main) {
                    data.collect { data ->
                        data.forEach {
                            if (binding.passwprdText.text.toString().trim() == it.password) {
                                intent.putExtra("username", it.username)
                                startActivity(intent)
                            } else {
                                binding.passwordLayout.error = "wrong password"
                                binding.inputLayout.error = "wrong username or email"
                            }

                        }
                    }
                }
            }
        }


    }


}