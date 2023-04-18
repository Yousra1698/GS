package com.projets.applicationgestionclients.Paiment

data class Paiment(var idClient: Int,var datePaiment: String,var montantPaiment : Double)
{
    var idPaiment:Int?=null
    constructor(idClient:Int,idPaiment:Int,datePaiment: String,montantPaiment : Double):this(idClient,datePaiment,montantPaiment){
        this.idPaiment=idPaiment
    }




}