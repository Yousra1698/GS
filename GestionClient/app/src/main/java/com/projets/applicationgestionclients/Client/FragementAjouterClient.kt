package com.projets.applicationgestionclients.Client

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.projets.applicationgestionclients.Activities.MainActivity
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.databinding.FragmentAjouterClientBinding
class FragementAjouterClient : Fragment() {
    private lateinit var editTextNomComplet:EditText
    private lateinit var editTextEmail:EditText
    private lateinit var editTextAdresse:EditText
    private lateinit var editTextTel:EditText
    private lateinit var editTextPrix:EditText
    private lateinit var bindingFragementAjouterClient: FragmentAjouterClientBinding
    private lateinit var daoClient: DaoClient
    private lateinit var backAjouter:ImageView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFragementAjouterClient = FragmentAjouterClientBinding.inflate(layoutInflater,container,false)
        editTextNomComplet=bindingFragementAjouterClient.editTextNomComplet
        editTextAdresse=bindingFragementAjouterClient.editTextAdresse
        editTextEmail=bindingFragementAjouterClient.editTextEmail
        editTextTel=bindingFragementAjouterClient.editTextTel
        editTextPrix=bindingFragementAjouterClient.editTextPrix
        backAjouter=bindingFragementAjouterClient.back
        daoClient= DaoClient(requireContext())
        bindingFragementAjouterClient.back.setOnClickListener {
          val intent=Intent(requireContext(),ActivityClient::class.java)
            startActivity(intent)
        }
        bindingFragementAjouterClient.buttonAjouterClient.setOnClickListener {
            if( editTextNomComplet.text.isNotBlank() && editTextAdresse.text.isNotBlank() && editTextEmail.text.isNotBlank() && editTextTel.text.isNotBlank() && editTextPrix.text.isNotBlank()){
                var client= Client(editTextNomComplet.text.toString(),editTextAdresse.text.toString(),editTextEmail.text.toString(),editTextTel.text.toString(),editTextPrix.text.toString().toDouble(),0.0)
                val alertBuilder = AlertDialog.Builder(requireContext())
                alertBuilder.setMessage("Voulez-vous ajouter ce client ?")
                    .setPositiveButton("Oui"){
                        _,_ ->  daoClient.ajouterClient(client)
                        Toast.makeText(requireContext(),"Client a ajouté en succès",Toast.LENGTH_SHORT).show()
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
        return bindingFragementAjouterClient.root

    }
    private fun viderChamps() {
        val vide = ""
        bindingFragementAjouterClient.editTextNomComplet.setText(vide)
        bindingFragementAjouterClient.editTextTel.setText(vide)
        bindingFragementAjouterClient.editTextAdresse.setText(vide)
        bindingFragementAjouterClient.editTextEmail.setText(vide)
        bindingFragementAjouterClient.editTextPrix.setText(vide)
        bindingFragementAjouterClient.editTextNomComplet.requestFocus()
    }


}