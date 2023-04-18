package com.projets.applicationgestionclients.Client

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.databinding.FragmentModifierClientBinding

class FragmentModifierClient : Fragment() {
    private lateinit var bindingFragmentModifierClient: FragmentModifierClientBinding
    private lateinit var editTextNovNomComplet : EditText
    private lateinit var editTextNovAdresse : EditText
    private lateinit var editTextNovEmail : EditText
    private lateinit var editTextNovPrix : EditText
    private lateinit var editTextNovTelephone : EditText
    private lateinit var adapter: ArrayAdapter<Int>
    private lateinit var daoClient: DaoClient
    private lateinit var back_Modiffier:ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bindingFragmentModifierClient = FragmentModifierClientBinding.inflate(layoutInflater,container,false)
        back_Modiffier=bindingFragmentModifierClient.backModifier
        bindingFragmentModifierClient.backModifier.setOnClickListener {
            val intent= Intent(requireContext(),ActivityClient::class.java)
            startActivity(intent)
        }
        editTextNovNomComplet = bindingFragmentModifierClient.editTextNovNomComplet
        editTextNovAdresse = bindingFragmentModifierClient.editTextAdresse
        editTextNovTelephone = bindingFragmentModifierClient.editTextNovTel
        editTextNovPrix = bindingFragmentModifierClient.editTextNovPrix
        editTextNovEmail = bindingFragmentModifierClient.editTextNovEmail
        daoClient = DaoClient(requireContext())
        adapter = ArrayAdapter<Int>(requireContext(),android.R.layout.simple_list_item_1,daoClient.selectIdClients())
        bindingFragmentModifierClient.spinnerClient.adapter = adapter
        bindingFragmentModifierClient.spinnerClient.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var client= daoClient.selectClients()[position]
                positionClient(client)
                bindingFragmentModifierClient.buttonModifierClient.setOnClickListener {
                    if(editTextNovNomComplet.text.isNotBlank() && editTextNovAdresse.text.isNotBlank() && editTextNovEmail.text.isNotBlank() && editTextNovPrix.text.isNotBlank() && editTextNovTelephone.text.isNotBlank()){
                       var clients = Client(editTextNovNomComplet.text.toString(),editTextNovAdresse.text.toString(),editTextNovEmail.text.toString(),editTextNovTelephone.text.toString(),editTextNovPrix.text.toString().toDouble(), client!!.montant)
                        messageModificationClient(client.id!!,clients)
                        viderChamps()
                    }
                    else{
                        Toast.makeText(requireContext(),"Erreur!!", Toast.LENGTH_SHORT).show()
                    }

                }

                //daoClient.modifierClient(client)
            }

            private fun viderChamps() {
                val vide = ""
                bindingFragmentModifierClient.editTextNovNomComplet.setText(vide)
                bindingFragmentModifierClient.editTextAdresse.setText(vide)
                bindingFragmentModifierClient.editTextNovEmail.setText(vide)
                bindingFragmentModifierClient.editTextNovPrix.setText(vide)
                bindingFragmentModifierClient.editTextNovTel.setText(vide)
                bindingFragmentModifierClient.editTextNovNomComplet.requestFocus()
            }

            private fun messageModificationClient(idClient: Int,client: Client) {
                 val alertBuilder = AlertDialog.Builder(requireContext())
                alertBuilder.setMessage("Voulez-vous modifier ce client ?")
                    .setPositiveButton("Oui"){
                        _,_ ->daoClient.modifierClient(idClient,client)
                    }
                    .setNegativeButton("No"){
                        _,_ ->alertBuilder.create().dismiss()
                    }
                    .setNeutralButton("Quitter"){
                        _,_ ->alertBuilder.create().dismiss()
                    }
                    .show()
            }

            private fun positionClient(client: Client) {
                bindingFragmentModifierClient.editTextNovNomComplet.setText(client.nomComplet)
                bindingFragmentModifierClient.editTextNovEmail.setText(client.emailClient)
                bindingFragmentModifierClient.editTextAdresse.setText(client.adresseClient)
                bindingFragmentModifierClient.editTextNovPrix.setText(client.prix.toString())
                bindingFragmentModifierClient.editTextNovTel.setText(client.telephoneClient)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        return bindingFragmentModifierClient.root
    }

}