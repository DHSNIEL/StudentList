package com.daniel.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.advweek4.model.Student

class DetailViewModel : ViewModel() {

    val studentLD = MutableLiveData<Student>()
//
   fun fetch(){
       val student1 = Student("16055","Nonie","1998/03/28",
           "5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
       studentLD.value = student1
   }
   
    //private var selectedStudent: Student? = null

    fun setSelectedStudent(student: Student) {
        studentLD.value = student
    }

    fun getSelectedStudent(): MutableLiveData<Student> {
        return studentLD
    }
}

//class DetailViewModel (application: Application): AndroidViewModel(application) {
//    val studentLD = MutableLiveData<Student>()
//
//    fun fetch(){
//        val student1 = Student("16055","Nonie","1998/03/28",
//            "5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
//    }
//}