package com.daniel.advweek4.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.daniel.advweek4.R
import com.daniel.advweek4.databinding.FragmentStudentDetailBinding
import com.daniel.advweek4.model.Student
import com.daniel.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        detailViewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.getSelectedStudent().value?.let { student ->
            updateUI(student)
        }
        observeViewModel()
        // You might want to handle the case when there's no selected student
    }

    private fun updateUI(student: Student) {
        binding.textInputLayout1.editText?.setText(student.id)
        binding.textInputLayout2.editText?.setText(student.name)
        binding.textInputLayout3.editText?.setText(student.dob)
        binding.textInputLayout4.editText?.setText(student.phone)
    }

    private fun observeViewModel() {
        detailViewModel.getSelectedStudent().observe(viewLifecycleOwner, Observer { student ->
            binding.btnUpdate.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "Five seconds")
                        context?.let {
                            MainActivity.showNotif(
                                student.name.toString(),
                                "A new notification created", R.drawable.notification
                            )
                        }
                    }
            }
        })
    }
}