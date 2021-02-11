package com.example.uas_dzakiam201804016

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBInfo.UserTable.TABLE_NAME + "("+DBInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT, " + DBInfo.UserTable.COL_JENKAL + " VARCHAR(200), " + DBInfo.UserTable.COL_ALAMAT + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAME
        private val SQL_CREATE_MOUSE = "CREATE MOUSE " + DBInfo.MouseTable.TABLE_NAME + "("+DBInfo.MouseTable.COL_MKODE+" VARCHAR(200) PRIMARY KEY, " + DBInfo.MouseTable.COL_MBRAND + "VARCHAR(200), " + DBInfo.MouseTable.COL_MTYPE + " VARCHAR(200), " + DBInfo.MouseTable.COL_MHARGA + " VARCHAR(200))"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_MOUSE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailin: String, passin: String, fullnamein: String, jenkalin: String, alamatin: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        val jenkalt = DBInfo.UserTable.COL_JENKAL
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        var sql = "INSERT INTO " + namatable + " (" + emailt + ", " + passt + ", " + fullnamet + ", " + jenkalt + ", " + alamatt + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "', '" + jenkalin + "', '" + alamatin + "')"
        db.execSQL(sql)
    }
    fun InsertDataMouse(mkodein: String, mbrandin: String, mtypein: String, mhargain: String) {
        val db = writableDatabase
        val namatablem = DBInfo.MouseTable.TABLE_NAME
        val mkodet = DBInfo.MouseTable.COL_MKODE
        val mbrandt = DBInfo.MouseTable.COL_MBRAND
        val mtypet = DBInfo.MouseTable.COL_MTYPE
        val mhargat = DBInfo.MouseTable.COL_MHARGA
        var sql = "INSERT INTO " + namatablem + " (" + mkodet + ", " + mbrandt + ", " + mtypet + ", " + mhargat + ") VALUES('" + mkodein + "', '" + mbrandin + "', '" + mtypein + "', '" + mhargain + "')"
        db.execSQL(sql)
    }
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            //db.execSQL(SQL_CREATE_MOUSE)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun fullData():ArrayList<DataModelMouse> {
//        val users = ArrayList<DBModel>()
        val users = arrayListOf<DataModelMouse>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.UserTable.TABLE_NAME, null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_MOUSE)
            return ArrayList()
        }

        var mkode: String
        var mbrand: String
        var mtype: String
        var mharga: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                mkode = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_MKODE))
                mbrand = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_MBRAND))
                mtype = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_MTYPE))
                mharga = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_MHARGA))

                users.add(DataModelMouse(mkode, mbrand, mtype, mharga))
                cursor.moveToNext()
            }
        }
        return  users
    }
    fun deleteData(mkodein: String){
        val db = writableDatabase
        val namatablem = DBInfo.MouseTable.TABLE_NAME
        val mkode = DBInfo.MouseTable.COL_MKODE
        val sql = "DELETE FROM " +namatablem+ " WHERE "+mkode+"='"+mkodein+"'"
        db.execSQL(sql)
    }
    fun updateData(mkodein: String, mbrandin: String, mtypein: String, mhargain: String){
        val db = writableDatabase
        val namatablem = DBInfo.MouseTable.TABLE_NAME
        val tkodem = DBInfo.MouseTable.COL_MKODE
        val tbrandm = DBInfo.MouseTable.COL_MBRAND
        val ttypem = DBInfo.MouseTable.COL_MTYPE
        val thargam = DBInfo.MouseTable.COL_MHARGA
        var sql = "UPDATE "+ namatablem + " SET "+
                tbrandm+"='"+mbrandin+"', "+ttypem+"='"+mtypein+"', "+thargam+"='"+mhargain+"' "+
                "WHERE "+tkodem+"='"+mkodein+"'"
        db.execSQL(sql)
    }
}