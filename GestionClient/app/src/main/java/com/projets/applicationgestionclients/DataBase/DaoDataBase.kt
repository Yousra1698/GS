package com.projets.applicationgestionclients.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase

abstract class DaoDataBase(context: Context) {
    protected lateinit var dataBase: DbDatabase
    protected lateinit var db:SQLiteDatabase
    companion object{
        protected const val DATABASE_NAME="GestionClient.db"
        protected const val VERSION_DATABASE=1
    }
   init {
        this.dataBase= DbDatabase(context, DATABASE_NAME,null, VERSION_DATABASE)
        this.db=this.dataBase.writableDatabase
   }


}