package com.example.uas_dzakiam201804016

import android.provider.BaseColumns

object DBInfo {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_ALAMAT = "alamat"
            val COL_JENKAL = "jeniskelamin"
            val COL_JUMLAH = "jumlah"

//            val TABLE_NAMEM = "mouse"
//            val COL_MKODE = "mkode"
//            val COL_MBRAND = "mbrand"
//            val COL_MTYPE = "mtype"
//            val COL_MHARGA = "mharga"
        }

    }
    class MouseTable: BaseColumns {
        companion object {
            val TABLE_NAME = "mouse"
            val COL_MKODE = "mkode"
            val COL_MBRAND = "mbrand"
            val COL_MTYPE = "mtype"
            val COL_MHARGA = "mharga"
        }
    }
}