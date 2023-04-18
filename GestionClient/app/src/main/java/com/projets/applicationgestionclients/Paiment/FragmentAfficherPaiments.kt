package com.projets.applicationgestionclients.Paiment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.projets.applicationgestionclients.Adapters.AdapterPaiments
import com.projets.applicationgestionclients.DaoClasses.DaoPaiment
import com.projets.applicationgestionclients.databinding.FragmentAfficherPaimentsBinding

class FragmentAfficherPaiments : Fragment() {
   private lateinit var bindingFragmentAfficherPaiments: FragmentAfficherPaimentsBinding
   private lateinit var adapterPaiments : AdapterPaiments
   private lateinit var daoPaiment: DaoPaiment
   private lateinit var backPaimentAfficher:ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFragmentAfficherPaiments = FragmentAfficherPaimentsBinding.inflate(layoutInflater,container,false)
        backPaimentAfficher=bindingFragmentAfficherPaiments.backAffichPaiment
        bindingFragmentAfficherPaiments.backAffichPaiment.setOnClickListener {
            val intent= Intent(requireContext(), ActivityPaiment::class.java)
            startActivity(intent)
        }
        daoPaiment = DaoPaiment(requireContext())
        adapterPaiments = AdapterPaiments(daoPaiment.selectPaiments())
        bindingFragmentAfficherPaiments.recyclerViewPaiments.adapter = adapterPaiments
        return bindingFragmentAfficherPaiments.root
    }


}