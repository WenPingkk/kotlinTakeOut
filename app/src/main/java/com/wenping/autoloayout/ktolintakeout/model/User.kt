package com.wenping.autoloayout.ktolintakeout.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:
 */
@DatabaseTable(tableName = "t_user")
class User {
    //id=true表示使用指定id
    @DatabaseField(id = true)
    var id: Int = 0
    @DatabaseField(columnName = "name")
    var name: String? = null
    @DatabaseField(columnName = "balance")
    var balance: Float = 0f
    @DatabaseField(columnName = "discount")
    var discount: Int = 0
    @DatabaseField(columnName = "integral")
    var integral: Int = 0
    @DatabaseField(columnName = "phone")
    var phone: String? = null
}
