package com.projets.applicationgestionclients.Paiment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.projets.applicationgestionclients.Activities.MainActivity
import com.projets.applicationgestionclients.Client.ActivityClient
import com.projets.applicationgestionclients.R
import com.projets.applicationgestionclients.databinding.ActivityGestionPaimentBinding

class ActivityPaiment : AppCompatActivity() {
    private lateinit var bindingGestionPaiment: ActivityGestionPaimentBinding
    var fragmentAjouterPaiment: FragmentAjouterPaiment?=null
    var fragmentAfficherPaiments: FragmentAfficherPaiments?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindingGestionPaiment = ActivityGestionPaimentBinding.inflate(layoutInflater)
        setContentView(bindingGestionPaiment.root)
        bindingGestionPaiment.ajouterPaiment.setOnClickListener {
            if (fragmentAjouterPaiment == null){
                fragmentAjouterPaiment = FragmentAjouterPaiment()
                chagerAjouterPaiment(fragmentAjouterPaiment!!)
            }
            else{
                chagerAjouterPaiment(fragmentAjouterPaiment!!)
            }
            bindingGestionPaiment.bottomNavPaiment.visibility= View.GONE
        }
        bindingGestionPaiment.afficherPaiment.setOnClickListener {
            if (fragmentAfficherPaiments == null){
                fragmentAfficherPaiments = FragmentAfficherPaiments()
                chagerAjouterPaiment(fragmentAfficherPaiments!!)
            }
            else{
                chagerAjouterPaiment(fragmentAfficherPaiments!!)
                }
            bindingGestionPaiment.bottomNavPaiment.visibility= View.GONE
            }
    }
    override fun onBackPressed() {
        val i= Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun chagerAjouterPaiment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_paiment,fragment!!)
        transaction.commit()
    }

}
    /*
        bindingGestionPaiment.bottomNavPaiment.setOnNavigationItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.ajouter_paiment -> {
                    if (fragmentAjouterPaiment == null){
                        fragmentAjouterPaiment = FragmentAjouterPaiment()
                        chagerAjouterPaiment(fragmentAjouterPaiment!!)
                    }
                    else{
                        chagerAjouterPaiment(fragmentAjouterPaiment!!)
                    }
                    true

                }
                R.id.afficher_paiment -> {
                    if (fragmentAfficherPaiments == null){
                        fragmentAfficherPaiments = FragmentAfficherPaiments()
                        chagerAjouterPaiment(fragmentAfficherPaiments!!)
                    }
                    else{
                        chagerAjouterPaiment(fragmentAfficherPaiments!!)
                    }
                    true
                }
                R.id.modifier_paiment -> {
                    if (fragmentModifierPaiment == null){
                        fragmentModifierPaiment = FragmentModifierPaiment()
                        chagerModifierPaiment(fragmentModifierPaiment!!)
                    }else{
                        chagerModifierPaiment(fragmentModifierPaiment!!)
                    }
                    true
                }
                else -> false
            }

        }

         */



