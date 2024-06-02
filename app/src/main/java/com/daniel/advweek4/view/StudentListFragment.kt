package com.daniel.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniel.advweek4.R
import com.daniel.advweek4.databinding.FragmentStudentListBinding
import com.daniel.advweek4.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private lateinit var viewModel:ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            studentListAdapter.updateStudentList(it)
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtError)

            if(it == true){
                txtError?.visibility = View.VISIBLE
            }
            else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            val recView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            if(it==true){
                    recView?.visibility = View.GONE
                    progressLoad?.visibility = View.VISIBLE
                }else{
                    recView?.visibility = View.VISIBLE
                    progressLoad?.visibility = View.GONE
                }
        })
    }
}