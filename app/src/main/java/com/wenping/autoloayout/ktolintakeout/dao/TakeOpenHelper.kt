package com.wenping.autoloayout.ktolintakeout.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.wenping.autoloayout.ktolintakeout.model.User

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:
 */
class TakeOpenHelper(context: Context?) :
        OrmLiteSqliteOpenHelper(context, "takeout_kotlin,db", null, 1) {
    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        //创建User表
        TableUtils.createTable(connectionSource,User::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
    }

}