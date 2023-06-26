package com.tasks.gym_dashboard.domain

import com.tasks.gym_dashboard.data.Repository
import javax.inject.Inject

class GetAllGymsUseCase @Inject constructor(private val repository : Repository) {

      operator suspend fun invoke(): List<GymItem> {
          return repository.getList().map {
              GymItem(it.gym_name,it.gym_location,it.id,it.is_open,it.is_Favorite)
          }
      }


}