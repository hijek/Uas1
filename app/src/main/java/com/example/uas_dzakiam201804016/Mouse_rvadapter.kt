package com.example.uas_dzakiam201804016

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Mouse_rvadapter(private val listMouseku: ArrayList<DataModelMouse>): RecyclerView.Adapter<Mouse_rvadapter.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvmkode: TextView = itemV.findViewById(R.id.tv_mkode)
        var tvmbrand: TextView = itemV.findViewById(R.id.tv_mbrand)
        var tvmtype: TextView = itemV.findViewById(R.id.tv_mtype)
        var tvmharga: TextView = itemV.findViewById(R.id.tv_mharga)
        var btndeletem: Button = itemV.findViewById(R.id.btn_deletecard)
        var btnupdatem: Button = itemV.findViewById(R.id.btn_updatecard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_mouse, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val mouseku = listMouseku[position]
        holder.tvmkode.text = mouseku.mkode
        holder.tvmbrand.text = mouseku.mbrand
        holder.tvmtype.text = mouseku.mtype
        holder.tvmharga.text = mouseku.mharga

        holder.btndeletem.setOnClickListener {
            var adapterDBHelper: DBHelper
            adapterDBHelper = DBHelper(holder.itemView.context)
            adapterDBHelper.deleteData(mouseku.mkode)
            listMouseku.removeAt(position)
            notifyDataSetChanged()
        }

        holder.btnupdatem.setOnClickListener {
            val pindahUpdAc = Intent(holder.itemView.context, MouseUpdate::class.java)
            val bundle = Bundle()
            bundle.putString("mkodek", mouseku.mkode)
            bundle.putString("mbrandk", mouseku.mbrand)
            bundle.putString("mtypek", mouseku.mtype)
            bundle.putString("mhargak", mouseku.mharga)
            pindahUpdAc.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUpdAc)
        }


    }

    override fun getItemCount(): Int {
        return listMouseku.size
    }


}