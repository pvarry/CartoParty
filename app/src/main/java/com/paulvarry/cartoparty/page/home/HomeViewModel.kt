package com.paulvarry.cartoparty.page.home

import androidx.lifecycle.ViewModel
import com.paulvarry.cartoparty.repository.points.PointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryPoints: PointRepository,
) : ViewModel() {

    val slugs by lazy { repositoryPoints.getAllSlugs() }

}
