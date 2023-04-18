package com.projets.applicationgestionclients.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.projets.applicationgestionclients.Client.ActivityClient
import com.projets.applicationgestionclients.Client.FragementAjouterClient
import com.projets.applicationgestionclients.Livraison.ActivityLivraison
import com.projets.applicationgestionclients.Paiment.ActivityPaiment
import com.projets.applicationgestionclients.R
import com.projets.applicationgestionclients.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.iconAnimation.alpha=0f
        binding.iconAnimation.scaleX=0f
        binding.iconAnimation.scaleY=0f
        binding.iconAnimation.animate().apply {
            duration=3000
            alpha(1f)
            scaleX(1f)
            scaleY(1f)
            withEndAction(Runnable {
                Toast.makeText(applicationContext,"Bienvenue",Toast.LENGTH_SHORT).show()
            })
        }

        binding.bottomNav.setOnItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.clients_home -> {
                    GestionClient()
                    true
                }
                R.id.paiments_home -> {
                    GestionPaiment()
                    true
                }
                R.id.livrisons_home -> {
                    GestionLivraison()
                    true
                }
                else -> false
            }

        }

    }

    private fun GestionClient() {
        val intentGestionClient = Intent(this@MainActivity, ActivityClient::class.java)
        this.startActivity(intentGestionClient)
    }
    private fun GestionPaiment() {
        val intentGestionPaiment = Intent(this@MainActivity, ActivityPaiment::class.java)
        this.startActivity(intentGestionPaiment)
    }
    private fun GestionLivraison() {
        val intentGestionLivraison = Intent(this@MainActivity, ActivityLivraison::class.java)
        this.startActivity(intentGestionLivraison)
    }
    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("êtes-vous sûr ?")
        alertDialog.setMessage("Exit")
        alertDialog.setPositiveButton("Oui"){ _ , _ -> this@MainActivity.finish() }
        alertDialog.setNegativeButton("Non"){_ , _ -> alertDialog.setOnDismissListener { it.dismiss() } }
        alertDialog.show()
    }

}