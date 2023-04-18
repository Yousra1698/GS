package com.projets.applicationgestionclients.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.projets.applicationgestionclients.Activities.InteractionClient
import com.projets.applicationgestionclients.Client.Client
import com.projets.applicationgestionclients.R
import com.projets.applicationgestionclients.databinding.ItemClientBinding

class AdapterClients(val context: Context?, val listClients: List<Client>, val listenerr: InteractionClient) : RecyclerView.Adapter<AdapterClients.ClientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(view)
    }
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val currentClient = listClients[position]
        holder.bindingItemClient.apply {
            idClient.text = "Id Client : " + currentClient.id.toString()
            nomCompletClient.text ="Nom :"+ currentClient.nomComplet
            numeroTelephoneClient.text ="Téléphone :" + currentClient.telephoneClient
            adresseClient.text ="Adresse : " +currentClient.adresseClient
            prixClient.text ="Prix : "+currentClient.prix.toString()+ " DH "
            montantClient.text ="Montant : "+ currentClient.montant.toString()+ " DH "
            emailClient.text ="Email : "+ currentClient.emailClient
            cardHistory.setOnClickListener {
                listenerr.onClickIconHistorique(currentClient)

            }
        }

    }
    override fun getItemCount() = listClients.size
    inner class ClientViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {
        val bindingItemClient = ItemClientBinding.bind(viewItem)
    }

}