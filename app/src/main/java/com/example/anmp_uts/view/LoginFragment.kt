package com.example.anmp_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.anmp_uts.classes.Account
import com.example.anmp_uts.classes.Global
import com.example.anmp_uts.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    fun checkAcc(username:String, pass:String):Boolean{
        for (acc in Global.accountArr) if(username==acc.username && pass==acc.password) return true
        return false
    }

    fun getAcc(username: String, pass: String): Account? {
        for (acc in Global.accountArr) {
            if (username == acc.username && pass == acc.password) {
                return acc
            }
        }
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).binding.NavBar.visibility = View.INVISIBLE

        val signUpName = LoginFragmentArgs.fromBundle(requireArguments()).Username
        if(signUpName!=null){
            binding.txtLoginName.setText(signUpName.toString())
        }

        if( signUpName=="null"){
            binding.txtLoginName.setText("")
        }

        binding.txtCreateAcc.setOnClickListener{
            val action = LoginFragmentDirections.actionSignUp()
            Navigation.findNavController(it).navigate(action)
        }

        binding.btnLogin.setOnClickListener{
            var uName = binding.txtLoginName.text.toString()
            var pass = binding.txtLoginPassword.text.toString()
            if(uName!="" && pass!=""){
                if(checkAcc(uName, pass)){
                    val account = getAcc(uName, pass)
                    if (account != null) {
                        Global.saveAccount(account)
                    }

                    val action = LoginFragmentDirections.actionLoginToHobbyList()
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

}