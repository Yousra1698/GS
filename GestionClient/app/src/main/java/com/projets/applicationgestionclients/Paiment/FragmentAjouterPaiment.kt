package com.projets.applicationgestionclients.Paiment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.projets.applicationgestionclients.Client.ActivityClient
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.DaoClasses.DaoPaiment
import com.projets.applicationgestionclients.Client.Client
import com.projets.applicationgestionclients.databinding.FragmentAjouterPaimentBinding

class FragmentAjouterPaiment : Fragment() {
    private lateinit var bindingFragmentAjouterPaiment: FragmentAjouterPaimentBinding
    private lateinit var editTextIdClient: EditText
    private lateinit var editTextDatePaiment: EditText
    private lateinit var editTextMontatPaiment: EditText
    private lateinit var backPaimentAjouter:ImageView
    private lateinit var buttonChercherPaiment:Button
    private lateinit var textviewNomPaiment:TextView
    private lateinit var textviewMontantPaiment:TextView
    private lateinit var client: Client
    private lateinit var daoPaiment: DaoPaiment
    private lateinit var daoClient: DaoClient
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFragmentAjouterPaiment = FragmentAjouterPaimentBinding.inflate(layoutInflater,container,false)
        backPaimentAjouter=bindingFragmentAjouterPaiment.backAjouterPaiment
        buttonChercherPaiment=bindingFragmentAjouterPaiment.buttonChercherPaiment
        textviewMontantPaiment=bindingFragmentAjouterPaiment.textviewMontantPaiment
        textviewNomPaiment=bindingFragmentAjouterPaiment.textviewNomPaiment
        bindingFragmentAjouterPaiment.backAjouterPaiment.setOnClickListener {
            val intent= Intent(requireContext(), ActivityPaiment::class.java)
            startActivity(intent)
        }
        editTextIdClient = bindingFragmentAjouterPaiment.editTextIdClient
        editTextDatePaiment = bindingFragmentAjouterPaiment.editTextDatePaiment
        editTextMontatPaiment = bindingFragmentAjouterPaiment.editTextMontantPaiment
        buttonChercherPaiment.setOnClickListener {
            val clientInfo = this.daoClient.getClientById(this.editTextIdClient.text.toString().toInt())
            if(clientInfo.moveToFirst()) {
                viderText()
                textviewNomPaiment.text = "Nom:" + clientInfo.getString(1)
                textviewMontantPaiment.text = "Montant:" + clientInfo.getDouble(6).toString()
            }else{
                viderText()
                Toast.makeText(requireContext(), "Ce client n'existe pas!", Toast.LENGTH_SHORT).show()
            }
        }
        daoPaiment = DaoPaiment(requireContext())
        daoClient = DaoClient(requireContext())
        bindingFragmentAjouterPaiment.buttonAjouterPaiment.setOnClickListener {
            if(editTextIdClient.text.isNotBlank() && editTextDatePaiment.text.isNotBlank() && editTextMontatPaiment.text.isNotBlank()){
                client = daoClient.retournerClient(editTextIdClient.text.toString().toInt())
                var paiment = Paiment(client.id!!,editTextDatePaiment.text.toString(),editTextMontatPaiment.text.toString().toDouble())
                client.montant = client.montant?.minus(editTextMontatPaiment.text.toString().toDouble())
                val alertBuilder = AlertDialog.Builder(requireContext())
                alertBuilder.setMessage("Voulez-vous ajouter ce paiment ?")
                    .setPositiveButton("Oui"){
                            _,_ ->  daoPaiment.ajouterPaiment(client.id!!,paiment)
                        daoClient.modifierMontantClient(client.id!!,client.montant!!)
                        Toast.makeText(requireContext(),"Paiment a ajouté en succès", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(requireContext(),"Erreur est survenue",Toast.LENGTH_SHORT).show()
            }
            }
        return bindingFragmentAjouterPaiment.root
    }

    private fun viderChamps() {
        val vide = ""
        bindingFragmentAjouterPaiment.editTextIdClient.setText(vide)
        bindingFragmentAjouterPaiment.editTextDatePaiment.setText(vide)
        bindingFragmentAjouterPaiment.editTextMontantPaiment.setText(vide)
        bindingFragmentAjouterPaiment.editTextIdClient.requestFocus()

    }
    private fun viderText()
    {
        bindingFragmentAjouterPaiment.textviewNomPaiment.setText("Nom:")
        bindingFragmentAjouterPaiment.textviewMontantPaiment.setText("Montant:")
    }


}