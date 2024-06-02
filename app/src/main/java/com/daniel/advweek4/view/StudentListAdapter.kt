package com.daniel.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.daniel.advweek4.R
import com.daniel.advweek4.databinding.StudentListItemBinding
import com.daniel.advweek4.model.Student

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonClickListener {
    class StudentViewHolder(val binding: StudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val txtID: TextView
        val txtName: TextView
        val btnDetail: Button
        val imgPhoto: ImageView

        init {
            txtID = binding.txtID
            txtName = binding.txtModel
            btnDetail = binding.btnDetail
            imgPhoto = binding.imageView
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this

        /*holder.txtID.text = studentList[position].id
        holder.txtName.text = studentList[position].name
        holder.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener{ picasso, uri, exception->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl).into(holder.imgPhoto)*/
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View) {
        val id = v.tag.toString()
        val action = StudentListFragmentDirections.actionStudentDetail(id)
        Navigation.findNavController(v).navigate(action)
    }

}
