package com.projets.applicationgestionclients.DaoClasses

import android.content.ContentValues
import android.content.Context
import com.projets.applicationgestionclients.DataBase.DaoDataBase
import com.projets.applicationgestionclients.Livraison.Livraison

class DaoLivraison(context: Context) : DaoDataBase(context) {
    companion object{
        const val TABLE_LIVRAISON = "livraison"
        const val COLUMN_ID = "idLivraison"
        const val FOREIGN_KEY_ID_CLIENT = "idClient"
        const val COLUMN_QUANTITE = "quantite"
        const val CREATE_TABLE = "CREATE TABLE " + TABLE_LIVRAISON +" ( "+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                FOREIGN_KEY_ID_CLIENT + " INTEGER REFERENCES "+ DaoClient.TABLE_CLIENT +  " ( "+ DaoClient.COLUMN_ID +
                " ) ," +
                COLUMN_QUANTITE + " REAL " +
                " ) ;"
        const val DROP_TABLE_LIVRAISON = "DROP TABLE $TABLE_LIVRAISON IF EXISTS "
    }
    fun ajouterLivraison(idClient: Int,livraison: Livraison) {
        val contentValues = ContentValues()
        contentValues.put(FOREIGN_KEY_ID_CLIENT,livraison.idClient)
        contentValues.put(COLUMN_QUANTITE,livraison.quantiteLivraison)
        db.insert(TABLE_LIVRAISON,null,contentValues)

    }
}