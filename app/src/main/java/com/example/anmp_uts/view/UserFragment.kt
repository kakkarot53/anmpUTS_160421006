package com.example.anmp_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmp_uts.R
import com.example.anmp_uts.classes.Account
import com.example.anmp_uts.classes.Global
import com.example.anmp_uts.databinding.FragmentHobbyDetailBinding
import com.example.anmp_uts.databinding.FragmentUserBinding
import com.example.anmp_uts.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val account = Global.cachedAcc
        if (account != null) {
            binding.txtChangeFirstName.setText(account.firstName)
            binding.txtChangeLastName.setText(account.lastName)
            binding.txtChangeName.setText(account.username)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        binding.btnLogOut.setOnClickListener{
            Global.cachedAcc = null
            val action = UserFragmentDirections.actionUserLogin()
            Navigation.findNavController(it).navigate(action)
        }
    }

}