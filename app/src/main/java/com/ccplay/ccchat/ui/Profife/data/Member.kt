package com.ccplay.ccchat.ui.Profife.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Member(
    @NonNull @ColumnInfo(name = "nick")
    var nickname: String,
    @NonNull @ColumnInfo(name = "name")
    var username: String,
    @NonNull @ColumnInfo(name = "pass")
    var password: String,
    @PrimaryKey(autoGenerate = true)//自動產生值
    var id: Long = 0

)