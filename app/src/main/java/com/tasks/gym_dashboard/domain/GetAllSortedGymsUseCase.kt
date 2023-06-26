package com.tasks.gym_dashboard.domain

 import com.tasks.gym_dashboard.data.Repository
 import javax.inject.Inject

class GetAllSortedGymsUseCase @Inject constructor(private val repository : Repository) {


    suspend operator fun invoke(): List<GymItem> {
        return repository.getList().sortedBy { it.gym_name }.map {
            GymItem(it.gym_name,it.gym_location,it.id,it.is_open,it .is_Favorite)
        }
    }
}