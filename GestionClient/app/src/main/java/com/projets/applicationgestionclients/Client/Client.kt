package com.projets.applicationgestionclients.Client

data class Client(var nomComplet:String,var adresseClient:String,var emailClient:String,var telephoneClient:String,var prix:Double,var montant:Double?=0.0):java.io.Serializable
{
    var id: Int?=null
    constructor(id: Int,nomComplet:String,adresseClient:String,emailClient:String,telephoneClient:String,prix:Double,montant:Double):this(nomComplet,adresseClient,emailClient,telephoneClient,prix,montant)
    {
        this.id=id
    }

}

