package com.tasks.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tasks.mvc.model.Movie
import com.tasks.navigationcomponent.databinding.ActivityMvcBinding

class MVC_Activity : AppCompatActivity() {
    lateinit var binding:ActivityMvcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.mvcBtnGetData.setOnClickListener {
//            val first = Movie.getMovies().first()
//        }

     }
}