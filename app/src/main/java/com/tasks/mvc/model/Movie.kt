package com.tasks.mvc.model

data class Movie(val name:String,val releaseDate:Int){


    companion object{
        fun getMovies()= listOf(
            Movie(name="Last Stander",2005),
            Movie(name="Last Stander01",2006),
            Movie(name="Last Stander02",2007),
            Movie(name="Last Stander03",2008),
        )
    }
}

