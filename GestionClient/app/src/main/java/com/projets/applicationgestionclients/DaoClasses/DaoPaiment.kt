package com.projets.applicationgestionclients.DaoClasses

import android.content.ContentValues
import android.content.Context
import com.projets.applicationgestionclients.Client.Client
import com.projets.applicationgestionclients.DataBase.DaoDataBase
import com.projets.applicationgestionclients.Paiment.Paiment

class DaoPaiment(context: Context) : DaoDataBase(context) {
    companion object{
           const val TABLE_PAIMENT = "Paiment"
           const val COLUMN_ID = "idPaiment"
           const val FOREIGN_KEY_ID_CLIENT = "idClient"
           const val COLUMN_DATE_PAIMENT = "datePaiment"
           const val COLUMN_MONTANT_PAIMENT = "montantPaiment"
           const val CREATE_TABLE_PAIMENT = "CREATE TABLE " + TABLE_PAIMENT +" ( "+
                   COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                   FOREIGN_KEY_ID_CLIENT + " INTEGER REFERENCES " +
                   DaoClient.TABLE_CLIENT +" ( "+
                   DaoClient.COLUMN_ID+
                   " ) ,"+
                   COLUMN_DATE_PAIMENT + " TEXT ,"+
                   COLUMN_MONTANT_PAIMENT + " REAL DEFAULT 0.0 "+
                   " ) ;"
           const val DROP_TABLE_PAIMENT = "DROP $TABLE_PAIMENT IF EXISTS "
           const val SELECT_PAIMENT = "SELECT * FROM $TABLE_PAIMENT"
           const val SELECT_ID = "SELECT $COLUMN_ID FROM $TABLE_PAIMENT"

    }
    fun ajouterPaiment(idClient: Int ,paiment: Paiment){
        val contentValues  = ContentValues()
        contentValues.put(DaoPaiment.FOREIGN_KEY_ID_CLIENT,paiment.idClient)
        contentValues.put(DaoPaiment.COLUMN_DATE_PAIMENT,paiment.datePaiment)
        contentValues.put(DaoPaiment.COLUMN_MONTANT_PAIMENT,paiment.montantPaiment)
        db.insert(TABLE_PAIMENT,null,contentValues)
        db.close()
    }
    fun selectClient():List<Client>{
        val listClient = mutableListOf<Client>()
        val cursorClient = db.rawQuery("SELECT ${DaoClient.TABLE_CLIENT}.${DaoClient.COLUMN_NOM_COMPLET} FROM ${DaoClient.TABLE_CLIENT} INNER JOIN $TABLE_PAIMENT ON ${DaoClient.TABLE_CLIENT}.${DaoClient.COLUMN_ID}=$TABLE_PAIMENT.$FOREIGN_KEY_ID_CLIENT",null)
        while (cursorClient.moveToNext()){
            var nom = cursorClient.getString(0)
            var adresse = cursorClient.getString(1)
            var email = cursorClient.getString(2)
            var tele = cursorClient.getString(3)
            var pri = cursorClient.getDouble(4)
            listClient.add(Client(nom,adresse,email,tele,pri))
        }
        return listClient
        db.close()
    }
    fun selectPaiments():List<Paiment>{
        val listPaiments = mutableListOf<Paiment>()
        val cursor = db.rawQuery(SELECT_PAIMENT,null)
        while (cursor.moveToNext()){
            var idPaiment = cursor.getInt(0)
            var idClient = cursor.getInt(1)
            var nomClient = cursor
            var datePaiment = cursor.getString(2)
            var montantPaiment = cursor.getDouble(3)
            val paiment = Paiment(idClient,idPaiment,datePaiment,montantPaiment)
            listPaiments.add(paiment)
        }
        return listPaiments
        db.close()
    }
    fun selectIdPaiments():List<Int>{
        val cursor = db.rawQuery(SELECT_ID,null)
        val listId = mutableListOf<Int>()
        while (cursor.moveToNext()){
            var idPaiment = cursor.getInt(0)
            listId.add(idPaiment)
        }
        return listId
        db.close()
    }

    fun modifierPaiment(idClient: Int, paiment: Paiment){
        val contentValues  = ContentValues()
        //contentValues.put(DaoPaiment.FOREIGN_KEY_ID_CLIENT,paiment.idClient)
        contentValues.put(DaoPaiment.COLUMN_DATE_PAIMENT,paiment.datePaiment)
        contentValues.put(DaoPaiment.COLUMN_MONTANT_PAIMENT,paiment.montantPaiment)
        db.update(TABLE_PAIMENT,contentValues,"$COLUMN_ID=?", arrayOf(idClient.toString()))
    }

}