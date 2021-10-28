package com.paulvarry.cartoparty.repository.points

import androidx.lifecycle.LiveData
import com.paulvarry.cartoparty.data_source.db.AppDatabase
import com.paulvarry.cartoparty.repository.points.db.PointEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

class PointRepository @Inject constructor(
    dbSource: AppDatabase,
) {

    private val dataSourceDb = dbSource.pointsDao()

    fun getPointsForSlug(slug: String): LiveData<List<PointEntity>> =
        dataSourceDb.getAllBySlugLiveData(slug)

    fun getAllSlugs(): LiveData<List<PointEntity>> =
        dataSourceDb.getAllLiveData()
}



