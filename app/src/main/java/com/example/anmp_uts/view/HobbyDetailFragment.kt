package com.example.anmp_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmp_uts.R
import com.example.anmp_uts.classes.Global
import com.example.anmp_uts.databinding.FragmentHobbyDetailBinding
import com.example.anmp_uts.databinding.FragmentHobbyListBinding
import com.example.anmp_uts.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HobbyDetailFragment : Fragment() {
    private lateinit var binding: FragmentHobbyDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    var detailIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHobbyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = HobbyDetailFragmentArgs.fromBundle(requireArguments()).HobbyID

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(id)

        detailViewModel.hobbyLD.observe(viewLifecycleOwner) { hobby ->
            binding.apply {
                txtDetailTitle.text = hobby.title
                txtDetailWriter.text = hobby.writer
                txtDetailPara.text = hobby.details?.getOrNull(detailIndex)
                btnParBack.isEnabled = false

                val picasso = Picasso.Builder(imgDetailThumb.context)
                picasso.build().load(hobby.images).into(imgDetailThumb)
            }
        }
        observeViewModel()
    }

    fun observeViewModel() {
        detailViewModel.hobbyLD.observe(viewLifecycleOwner, Observer {
            var hobby = it

            binding.btnParNext.setOnClickListener{
                detailIndex+=1
                binding.txtDetailPara.text = hobby.details?.getOrNull(detailIndex)

                if(detailIndex>=2){
                    binding.btnParNext.isEnabled = false
                }
                binding.btnParBack.isEnabled = true

            }

            binding.btnParBack.setOnClickListener{
                detailIndex-=1
                binding.txtDetailPara.text = hobby.details?.getOrNull(detailIndex)

                if(detailIndex<=0){
                    binding.btnParBack.isEnabled = false
                }
                binding.btnParNext.isEnabled = true
            }

            binding.txtBack.setOnClickListener{
                val action = HobbyDetailFragmentDirections.actionDetailToList()
                Navigation.findNavController(it).navigate(action)
            }

        })
    }
}