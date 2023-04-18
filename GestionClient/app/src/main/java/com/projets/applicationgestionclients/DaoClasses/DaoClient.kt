package com.projets.applicationgestionclients.DaoClasses

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.projets.applicationgestionclients.DataBase.DaoDataBase
import com.projets.applicationgestionclients.Client.Client
class DaoClient(context: Context): DaoDataBase(context){
    companion object{
        const val TABLE_CLIENT="Client"
        const val COLUMN_ID="_id"
        const val COLUMN_NOM_COMPLET="nomComplet"
        const val COLUMN_ADRESSE="adresseClient"
        const val COLUMN_EMAIL="emailClient"
        const val COLUMN_TELEPHONE="telephoneClient"
        const val COLUMN_PRIX="prixClient"
        const val COLUMN_MONTANT="montantClient"
        const val CREATE_TABLE="CREATE TABLE " + TABLE_CLIENT +" ( "+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                COLUMN_NOM_COMPLET + " TEXT ,"+
                COLUMN_ADRESSE + " TEXT ," +
                COLUMN_EMAIL + " TEXT ," +
                COLUMN_PRIX + " REAL ," +
                COLUMN_TELEPHONE + " TEXT ," +
                COLUMN_MONTANT + " REAL DEFAULT 0.0 );"
        const val DROP_TABLE= "DROP TABLE $TABLE_CLIENT IF EXISTS"
        const val SELECT_CLIENTS = "SELECT * FROM $TABLE_CLIENT"
        const val SELECT_ID = "SELECT $COLUMN_ID FROM $TABLE_CLIENT"
        const val WHERE_CLAUSE= "$COLUMN_ID=?"
        const val SELECT_CLIENT = "SELECT * FROM $TABLE_CLIENT WHERE $WHERE_CLAUSE"
        const val HISTORIQUE_CLIENTS = "SELECT $COLUMN_ID, $COLUMN_NOM_COMPLET , " +
                "$COLUMN_MONTANT , ${DaoPaiment.COLUMN_DATE_PAIMENT} , " +
                "${DaoPaiment.COLUMN_MONTANT_PAIMENT} FROM $TABLE_CLIENT INNER JOIN " +
                "${DaoPaiment.TABLE_PAIMENT} ON $COLUMN_ID = ${DaoPaiment.COLUMN_ID}"
    }
    fun ajouterClient(client: Client){
        val contentValues=ContentValues()
        //contentValues.put(COLUMN_ID,client.idClient)
        contentValues.put(COLUMN_NOM_COMPLET,client.nomComplet)
        contentValues.put(COLUMN_ADRESSE,client.adresseClient)
        contentValues.put(COLUMN_EMAIL,client.emailClient)
        contentValues.put(COLUMN_PRIX,client.prix)
        contentValues.put(COLUMN_TELEPHONE,client.telephoneClient)
        contentValues.put(COLUMN_MONTANT,client.montant)
        this.db.insert(TABLE_CLIENT,null,contentValues)
    }
    fun modifierClient(id:Int,client: Client){
        val contentValues=ContentValues()
        //contentValues.put(COLUMN_ID,client.idClient)
        contentValues.put(COLUMN_NOM_COMPLET,client.nomComplet)
        contentValues.put(COLUMN_ADRESSE,client.adresseClient)
        contentValues.put(COLUMN_EMAIL,client.emailClient)
        contentValues.put(COLUMN_PRIX,client.prix)
        contentValues.put(COLUMN_TELEPHONE,client.telephoneClient)
        contentValues.put(COLUMN_MONTANT,client.montant)
        this.db.update(TABLE_CLIENT,contentValues,"$COLUMN_ID=?", arrayOf(id.toString()))
    }
    fun modifierMontantClient(idClient: Int,montant : Double){
        val contentValues = ContentValues()
        contentValues.put(COLUMN_MONTANT,montant)
        this.db.update(TABLE_CLIENT,contentValues, WHERE_CLAUSE, arrayOf(idClient.toString()))
    }
    fun getClientById(id:Int):Cursor
    {
       val cursor = this.db.query(TABLE_CLIENT,null,"$COLUMN_ID=?",arrayOf(id.toString()), null,null,null)
        Log.e("tag","${cursor.getColumnIndex(COLUMN_NOM_COMPLET)} and ${cursor.getColumnIndex(
            COLUMN_MONTANT)}")
        return cursor
    }
    fun historique() : Cursor{
        val cursor = db.rawQuery(HISTORIQUE_CLIENTS,null)
        return cursor
    }
    fun selectClients() : List<Client>{
        val listClients = mutableListOf<Client>()
        val cursor = this.db.rawQuery(SELECT_CLIENTS,null)
        while (cursor.moveToNext()){
            var idClient = cursor.getInt(0)
            var nomComplet = cursor.getString(1)
            var adresseClient = cursor.getString(2)
            var emailClient = cursor.getString(3)
            var prixClient = cursor.getDouble(4)
            var telephoneClient = cursor.getString(5)
            var montantClient = cursor.getDouble(6)
            val client = Client(idClient,nomComplet,adresseClient,emailClient, telephoneClient,prixClient.toDouble(),montantClient)
            listClients.add(client)
        }
        return listClients
        db.close()
    }
    fun supprimerClient(idClient: Int) : Boolean {
       var result= db.delete(TABLE_CLIENT, WHERE_CLAUSE, arrayOf(idClient.toString()))
        return result > 0
        db.close()
    }
    fun selectIdClients() : List<Int>{
        val listId = mutableListOf<Int>()
        val cursor = db.rawQuery(SELECT_ID,null)
        while (cursor.moveToNext()){
            var idClient = cursor.getInt(0)
            listId.add(idClient)
        }
        return listId
    }
    fun retournerClient(idClient : Int) : Client {
        var cursor = db.rawQuery(SELECT_CLIENT,  arrayOf(idClient.toString()))
        var client : Client?=null
        while (cursor.moveToNext()){
            var idClient = cursor.getInt(0)
            var nomComplet = cursor.getString(1)
            var adresseClient = cursor.getString(2)
            var emailClient = cursor.getString(3)
            var prixClient = cursor.getDouble(4)
            var telephoneClient = cursor.getString(5)
            var montantClient = cursor.getDouble(6)
            client = Client(idClient,nomComplet,adresseClient,emailClient,telephoneClient,prixClient,montantClient)
        }
        return client!!
    }
/*
    fun afficherHistry() : List<History>{
        val cursor = db.rawQuery(HISTORIQUE_CLIENTS,null)
        val listHistoriques = mutableListOf<History>()
        while (cursor.moveToNext()){
            var idClient = cursor.getInt(0)
            var nomComplet = cursor.getString(1)
            var dateMontant = cursor.getString(cursor.getColumnIndex(DaoPaiment.COLUMN_DATE_PAIMENT).toString().toInt())
            var credit = cursor.getDouble(cursor.getColumnIndex(COLUMN_MONTANT).toString().toInt())
            var avance = cursor.getDouble(cursor.getColumnIndex(DaoPaiment.COLUMN_MONTANT_PAIMENT).toString().toInt())
            listHistoriques.add(History(idClient,nomComplet,dateMontant,credit,avance))
        }
        return listHistoriques
    }

 */
}



