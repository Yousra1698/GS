package com.projets.applicationgestionclients.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import com.projets.applicationgestionclients.DaoClasses.DaoClient
import com.projets.applicationgestionclients.DaoClasses.DaoLivraison
import com.projets.applicationgestionclients.DaoClasses.DaoPaiment

class DbDatabase(context: Context, name:String?, cursorFactory: CursorFactory?, version:Int):SQLiteOpenHelper(context,name,cursorFactory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DaoClient.CREATE_TABLE)
        db?.execSQL(DaoPaiment.CREATE_TABLE_PAIMENT)
        db?.execSQL(DaoLivraison.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DaoClient.DROP_TABLE)
        db?.execSQL(DaoPaiment.DROP_TABLE_PAIMENT)
        db?.execSQL(DaoLivraison.DROP_TABLE_LIVRAISON)
    }

}