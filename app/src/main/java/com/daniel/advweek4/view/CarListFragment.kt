package com.daniel.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniel.advweek4.R
import com.daniel.advweek4.databinding.FragmentCarListBinding
import com.daniel.advweek4.viewmodel.CarViewModel

class CarListFragment : Fragment() {
    private lateinit var binding: FragmentCarListBinding
    private lateinit var viewModel: CarViewModel
    private val carListAdapter = CarListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = carListAdapter

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
        viewModel.carsLD.observe(viewLifecycleOwner, Observer{
            carListAdapter.updateCarList(it)
        })

        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer{
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