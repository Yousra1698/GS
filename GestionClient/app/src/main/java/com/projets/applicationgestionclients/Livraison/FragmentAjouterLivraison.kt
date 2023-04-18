package com.projets.applicationgestionclients.Livraison
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.DaoClasses.DaoLivraison
import com.projets.applicationgestionclients.Client.Client
import com.projets.applicationgestionclients.databinding.FragmentAjouterLivraisonBinding

class FragmentAjouterLivraison : Fragment() {
    private lateinit var bindingFragmentAjouterLivraison: FragmentAjouterLivraisonBinding
    private lateinit var editTextIdClient : EditText
    private lateinit var editTextQuantiteLivraison :EditText
    private lateinit var daoLivraison: DaoLivraison
    private lateinit var daoClient : DaoClient
    private lateinit var client: Client
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFragmentAjouterLivraison = FragmentAjouterLivraisonBinding.inflate(layoutInflater,container,false)
        editTextIdClient = bindingFragmentAjouterLivraison.editTextIdClient
        editTextQuantiteLivraison = bindingFragmentAjouterLivraison.editTextQuantiteLivraison
        daoClient = DaoClient(requireContext())
        daoLivraison = DaoLivraison(requireContext())
        bindingFragmentAjouterLivraison.buttonAjouterPaiment.setOnClickListener {
            if(editTextIdClient.text.isNotBlank() && editTextQuantiteLivraison.text.isNotBlank()){
                client = daoClient.retournerClient(editTextIdClient.text.toString().toInt())
                var livraison = Livraison(client.id!!,editTextQuantiteLivraison.text.toString().toInt())
                var montant = client.montant?.plus(client.prix * editTextQuantiteLivraison.text.toString().toInt())
                val alertBuilder = AlertDialog.Builder(requireContext())
                alertBuilder.setMessage("Voulez-vous ajouter cette livraison ?")
                    .setPositiveButton("Oui"){
                            _,_ ->  daoLivraison.ajouterLivraison(client.id!!,livraison)
                                      daoClient.modifierMontantClient(client.id!!,montant!!.toDouble())

                        Toast.makeText(requireContext(),"Livraison a ajouté en succès", Toast.LENGTH_SHORT).show()
                        viderChamps()
                    }
                    .setNegativeButton("No"){
                            _,_ -> alertBuilder.create().dismiss()
                    }
                    .setNeutralButton("Quitter"){
                            _,_ -> alertBuilder.create().dismiss()
                    }
                val alertDialog = alertBuilder.create()
                alertDialog.show()
            }
            else{
                Toast.makeText(requireContext(),"Erreur est survenue", Toast.LENGTH_SHORT).show()
            }
            }
        return bindingFragmentAjouterLivraison.root
    }
    private fun viderChamps() {
        val vide = ""
        bindingFragmentAjouterLivraison.editTextIdClient.requestFocus()
        bindingFragmentAjouterLivraison.editTextIdClient.setText(vide)
        bindingFragmentAjouterLivraison.editTextQuantiteLivraison.setText(vide)

    }


}