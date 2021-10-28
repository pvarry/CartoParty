package com.paulvarry.cartoparty.repository.points.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PointDao {
    @Query("SELECT * FROM points")
    suspend fun getAll(): List<PointEntity>

    @Query("SELECT * FROM points")
    fun getAllLiveData(): LiveData<List<PointEntity>>

    @Query("SELECT * FROM points WHERE slug=:slug")
    fun getAllBySlugLiveData(slug: String): LiveData<List<PointEntity>>

    @Insert
    fun insertAll(vararg responses: PointEntity)

    @Delete
    fun delete(response: PointEntity)
}
