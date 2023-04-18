package com.projets.applicationgestionclients.Client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.Fragment
import com.projets.applicationgestionclients.Activities.MainActivity
import com.projets.applicationgestionclients.R
import com.projets.applicationgestionclients.databinding.ActivityGestionClientBinding

class ActivityClient : AppCompatActivity(),OnClickListener {
    private lateinit var bindingGestionClient: ActivityGestionClientBinding
    var fragmentAjouterClient: FragementAjouterClient? = null
    var fragmentAfficherClients: FragmentAfficherClients? = null
    var fragmentSupprimerClient: FragmentSupprimerClient? = null
    var fragmentModifierClient: FragmentModifierClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindingGestionClient = ActivityGestionClientBinding.inflate(layoutInflater)
        setContentView(bindingGestionClient.root)
        // onNavigationBottomItemSelected()
        bindingGestionClient.ajouterClient.setOnClickListener(this)
        bindingGestionClient.afficherClient.setOnClickListener(this)
        bindingGestionClient.modifierClient.setOnClickListener(this)
        bindingGestionClient.supprimerClient.setOnClickListener(this)

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    //chargement d'un fragment pour ajouter un client
    private fun chargerFragmentAjouterClient(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment!!)
        transaction.commit()
    }

    //chargement d'un fragment pour afficher des client
    private fun chargerFragmentAffichageClient(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment!!)
        transaction.commit()
    }

    private fun chargerFragmentSupprimerClient(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment!!)
        transaction.commit()
    }

    private fun chargerFragmentModiferClient(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment!!)
        transaction.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ajouter_client -> {
                if (fragmentAjouterClient == null) {
                    fragmentAjouterClient = FragementAjouterClient()
                    chargerFragmentAjouterClient(fragmentAjouterClient!!)
                } else {
                    chargerFragmentAjouterClient(fragmentAjouterClient!!)
                }

            }
            R.id.afficher_client -> {
                if (fragmentAfficherClients == null) {
                    fragmentAfficherClients = FragmentAfficherClients()
                    chargerFragmentAffichageClient(fragmentAfficherClients!!)
                } else {
                    chargerFragmentAffichageClient(fragmentAfficherClients!!)
                }

            }
            R.id.supprimer_client -> {
                if (fragmentSupprimerClient == null) {
                    fragmentSupprimerClient = FragmentSupprimerClient()
                    chargerFragmentSupprimerClient(fragmentSupprimerClient!!)
                } else {
                    chargerFragmentSupprimerClient(fragmentSupprimerClient!!)
                }

            }
            R.id.modifier_client -> {
                if (fragmentModifierClient == null) {
                    fragmentModifierClient = FragmentModifierClient()
                    chargerFragmentModiferClient(fragmentModifierClient!!)
                } else {
                    chargerFragmentModiferClient(fragmentModifierClient!!)
                }

            }


        }
        bindingGestionClient.bottomBottom.visibility = View.GONE

    }
}










