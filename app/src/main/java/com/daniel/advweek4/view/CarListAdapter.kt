package com.daniel.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniel.advweek4.R
import com.daniel.advweek4.model.Car

class CarListAdapter(private val carList:ArrayList<Car>):RecyclerView.Adapter<CarListAdapter.CarViewHolder>()
{
    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtID: TextView
        val txtModel:TextView
        val txtMake: TextView
        val txtColor: TextView
        val txtFeature: TextView

        init {
            txtID = view.findViewById(R.id.txtID)
            txtModel = view.findViewById(R.id.txtModel)
            txtMake = view.findViewById(R.id.txtMake)
            txtColor = view.findViewById(R.id.txtColor)
            txtFeature = view.findViewById(R.id.txtFeature)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.car_list_item, parent, false)

        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.txtID.text = carList[position].id.toString()
        holder.txtModel.text = carList[position].model.toString()
        holder.txtMake.text = carList[position].make.toString()
        holder.txtColor.text = carList[position].colors?.joinToString(", ")
        holder.txtFeature.text = carList[position].features.toString()
    }

    fun updateCarList(newCarList: ArrayList<Car>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }
}
