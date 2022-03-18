package com.ccplay.ccchat.ui.Profife.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database (entities = arrayOf(Member::class), version = 1)
abstract class Getmember :RoomDatabase(){
abstract fun memberDao():MemberDao

}