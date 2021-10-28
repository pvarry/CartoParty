package com.paulvarry.cartoparty.repository.points.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "points")
data class PointEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "lng") val lng: Float,
    @ColumnInfo(name = "lat") val lat: Float,
    @ColumnInfo(name = "createdAt") val createdAt: Date,
)
