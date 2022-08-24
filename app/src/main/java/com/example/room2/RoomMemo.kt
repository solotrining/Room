package com.example.room2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")
class RoomMemo(@ColumnInfo var content: String,
               @ColumnInfo(name = "date") var datetime: Long) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var num : Long? = null

}