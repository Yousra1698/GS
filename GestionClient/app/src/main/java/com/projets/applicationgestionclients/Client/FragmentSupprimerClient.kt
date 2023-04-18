package com.projets.applicationgestionclients.Client
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.databinding.FragmentSupprimerClientBinding

class FragmentSupprimerClient : Fragment() {
    private lateinit var bindingFragmentSupprimerClient: FragmentSupprimerClientBinding
    private lateinit var daoClient: DaoClient
    private lateinit var backSupprim:ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFragmentSupprimerClient = FragmentSupprimerClientBinding.inflate(layoutInflater,container,false)
        backSupprim=bindingFragmentSupprimerClient.backSupprim
        bindingFragmentSupprimerClient.backSupprim.setOnClickListener {
            val intent= Intent(requireContext(),ActivityClient::class.java)
            startActivity(intent)
        }
        daoClient = DaoClient(requireContext())
        bindingFragmentSupprimerClient.buttonSupprimerClient.setOnClickListener {
            var idClient = bindingFragmentSupprimerClient.idClientSupprimer.text
            var res = daoClient.supprimerClient(idClient.toString().toInt())
            if (res){
            val alertBuilder = AlertDialog.Builder(requireContext())
            alertBuilder.setTitle("Supprition d'un client")
                .setMessage("Voulez-vous supprimer ce client ?")
                .setPositiveButton("Oui"){
                        _,_ -> Toast.makeText(requireContext(),"Client a supprimé",Toast.LENGTH_SHORT).show()
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
                Toast.makeText(requireContext(),"Ce il n'existe pas",Toast.LENGTH_SHORT).show()
            }
        }

        return bindingFragmentSupprimerClient.root
    }

}