package com.projets.applicationgestionclients.Client

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.projets.applicationgestionclients.Activities.ActivityHistorique
import com.projets.applicationgestionclients.Activities.InteractionClient
import com.projets.applicationgestionclients.Adapters.AdapterClients
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.databinding.FragmentAfficherClientsBinding

class FragmentAfficherClients : Fragment(),InteractionClient {
  private lateinit var bindingFragmentAfficherClients: FragmentAfficherClientsBinding
  private lateinit var adapterClients: AdapterClients
  private lateinit var daoClient: DaoClient
  private lateinit var back_Afficher:ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bindingFragmentAfficherClients = FragmentAfficherClientsBinding.inflate(layoutInflater,container,false)
        back_Afficher=bindingFragmentAfficherClients.backAfficher
        bindingFragmentAfficherClients.backAfficher.setOnClickListener {
            val intent= Intent(requireContext(),ActivityClient::class.java)
            startActivity(intent)
        }
        daoClient = DaoClient(requireContext())
        adapterClients = AdapterClients(context,daoClient.selectClients(),this)
        bindingFragmentAfficherClients.recyclerViewClient.adapter = adapterClients

        return bindingFragmentAfficherClients.root
    }

    override fun onClickItemClient(client: Client) {

    }

    override fun onClickIconHistorique(client: Client) {
        val intentee=Intent(requireContext(),ActivityHistorique::class.java)
        intentee.putExtra("client",client)
        this.startActivity(intentee)
    }


}