package com.projets.applicationgestionclients.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.projets.applicationgestionclients.Paiment.Paiment
import com.projets.applicationgestionclients.R
import com.projets.applicationgestionclients.databinding.ItemPaimentBinding

class AdapterPaiments(val listPaiments:List<Paiment>) : RecyclerView.Adapter<AdapterPaiments.PaimentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaimentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paiment,parent,false)
        return PaimentViewHolder(view)
    }

    override fun getItemCount() = listPaiments.size

    override fun onBindViewHolder(holder: PaimentViewHolder, position: Int) {
        val currentPaiment = listPaiments[position]
        holder.bindingAfficherPaiments.apply {
            idClient.text = "Id Client : " + currentPaiment.idClient.toString()
            idPaimentt.text = "Id Paiment : " + currentPaiment.idPaiment.toString()
            datePaiment.text = "Date de paiment : " + currentPaiment.datePaiment
            montantPaiment.text = "Montant de paiment : " + currentPaiment.montantPaiment.toString() + " DH "
        }
    }
    inner class PaimentViewHolder(itemView: View) : ViewHolder(itemView){
        val bindingAfficherPaiments = ItemPaimentBinding.bind(itemView)
    }
}