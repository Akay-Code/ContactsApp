package com.example.contactapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener

class MyAdapter(val context: Activity, val userContactList: MutableList<userContactInfo>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var myListener: MyClickListener

    interface MyClickListener{
        fun onItemClicking(position: Int)
    }

    fun mySetItemClickListener(listener : MyClickListener){
        myListener = listener
    }
    class MyViewHolder(itemView : View , listener: MyClickListener) : RecyclerView.ViewHolder(itemView) {
        val contactname = itemView.findViewById<TextView>(R.id.contactName)
        val contactdp = itemView.findViewById<ImageView>(R.id.contactProfileIcon)
        val contactnumber = itemView.findViewById<TextView>(R.id.contactNumber)
        val contactemail = itemView.findViewById<TextView>(R.id.contactEmail)
        val callButton = itemView.findViewById<ImageButton>(R.id.callButton)

        init {
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_each_row, parent, false)
        return MyViewHolder(view, myListener)
    }

    override fun getItemCount(): Int {
        return userContactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.contactname.text = userContactList[position].name
        holder.contactnumber.text = userContactList[position].phoneNumber
    }

}
