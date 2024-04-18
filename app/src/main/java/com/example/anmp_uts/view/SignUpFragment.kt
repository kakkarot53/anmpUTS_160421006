package com.example.anmp_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.anmp_uts.R
import com.example.anmp_uts.classes.Account
import com.example.anmp_uts.classes.Global
import com.example.anmp_uts.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener{
            var uname =     binding.txtSignupName.text.toString()
            var firstName = binding.txtFirstName.text.toString()
            var lastName =  binding.txtLastName.text.toString()
            var email =     binding.txtEmail.text.toString()
            var pass =      binding.txtSignUpPassword.text.toString()
            var pass2 =     binding.txtSignUpPasswordConfr.text.toString()
            if(pass == pass2){
                Global.accountArr.add(Account(uname, firstName, lastName, email, pass))

                val action = SignUpFragmentDirections.actionLogin(uname)
                Navigation.findNavController(it).navigate(action)
            }
            else{
                binding.txtPassWarning.visibility = View.VISIBLE
            }
        }

        binding.txtSignUpPassword.setOnClickListener {
            binding.txtPassWarning.visibility = View.INVISIBLE
        }
        binding.txtSignUpPasswordConfr.setOnClickListener {
            binding.txtPassWarning.visibility = View.INVISIBLE
        }


    }
}