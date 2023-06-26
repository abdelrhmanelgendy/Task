package com.tasks.gym_dashboard.domain

import com.tasks.gym_dashboard.data.Repository
import javax.inject.Inject

class ToggleFavoriteStateUseCase @Inject constructor(
    private val repository: Repository,
    private val getAllSortedGymsUseCase: GetAllSortedGymsUseCase
) {
    suspend operator fun invoke(gymId: Int, isFavorite: Boolean): List<GymItem> {
        repository.toggleSavedGym(gymId, isFavorite)
        return getAllSortedGymsUseCase()
    }
}