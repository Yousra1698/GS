package com.projets.applicationgestionclients.Livraison

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.projets.applicationgestionclients.R
import com.projets.applicationgestionclients.databinding.ActivityGestionLivraisonBinding

class ActivityLivraison : AppCompatActivity() {
    private lateinit var bindingGestionLivraison: ActivityGestionLivraisonBinding
    var fragmentAjouterLivraison: FragmentAjouterLivraison?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGestionLivraison = ActivityGestionLivraisonBinding.inflate(layoutInflater)
        setContentView(bindingGestionLivraison.root)

        onNavigationItemSelected()
    }

    private fun onNavigationItemSelected() {
        bindingGestionLivraison.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.ajouter_livraison -> {
                    if(fragmentAjouterLivraison == null){
                        fragmentAjouterLivraison = FragmentAjouterLivraison()
                        chargerFramentAjouterLivraison(fragmentAjouterLivraison!!)
                    }
                    else{
                        chargerFramentAjouterLivraison(fragmentAjouterLivraison!!)
                    }
                    true
                }
                else -> false
            }
        }
    }
    private fun chargerFramentAjouterLivraison(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_livraision,fragment!!)
            transaction.commit()
    }


}