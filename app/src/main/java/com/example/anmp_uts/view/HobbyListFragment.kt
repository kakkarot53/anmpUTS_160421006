package com.example.anmp_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_uts.R
import com.example.anmp_uts.databinding.FragmentHobbyListBinding
import com.example.anmp_uts.viewmodel.ListViewModel

class HobbyListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentHobbyListBinding
    private var hobbyListAdapter  = HobbyListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHobbyListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).binding.NavBar.visibility = View.VISIBLE

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = hobbyListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel(){
        viewModel.hobbiesLD.observe(viewLifecycleOwner, Observer {
            hobbyListAdapter.updateStudentList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if (it == true){
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.hobbiesLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if (it == true){
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })
    }
}