package com.ccplay.ccchat.ui.Profife.data

import androidx.room.*


@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//若重複就覆蓋
    fun insert(member: Member)//新增會員
    @Query("select*From member")
    fun getmember():List<Member>//查詢全部會員



}