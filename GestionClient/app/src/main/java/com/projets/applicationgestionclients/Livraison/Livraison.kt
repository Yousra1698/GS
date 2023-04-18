package com.projets.applicationgestionclients.Livraison

data class Livraison(var idClient: Int,var quantiteLivraison: Int){
    var idLivraison:Int?=null
    constructor(idClient: Int,idLivraison:Int,quantiteLivraison: Int):this(idClient,quantiteLivraison){
        this.idLivraison=idLivraison
    }
}
