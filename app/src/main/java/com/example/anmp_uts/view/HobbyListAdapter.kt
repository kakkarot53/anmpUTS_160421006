package com.example.anmp_uts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_uts.databinding.HobbyListItemBinding
import com.example.anmp_uts.model.Hobby
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class HobbyListAdapter(val HobbyList: ArrayList<Hobby>) :
    RecyclerView.Adapter<HobbyListAdapter.HobbyViewHolder>() {
    class HobbyViewHolder(var binding: HobbyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val binding = HobbyListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HobbyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.binding.txtTitle.text = HobbyList[position].title
        holder.binding.txtWriter.text = HobbyList[position].writer
        holder.binding.txtSynopsis.text = HobbyList[position].synopsis

        holder.binding.btnRead.setOnClickListener {
            val action = HobbyListFragmentDirections.actionHobbyDetail(HobbyList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception -> exception.printStackTrace()
        }
        picasso.build().load(HobbyList[position].images).into(holder.binding.imgThumbnail, object:
            Callback {
            override fun onSuccess() {
                holder.binding.progressImg.visibility = View.INVISIBLE
                holder.binding.imgThumbnail.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso_error", e.toString())
            }

        })


    }

    override fun getItemCount(): Int {
        return HobbyList.size
    }

    fun updateStudentList(newHobbyList: ArrayList<Hobby>) {
        HobbyList.clear()
        HobbyList.addAll(newHobbyList)
        notifyDataSetChanged()
    }

}
