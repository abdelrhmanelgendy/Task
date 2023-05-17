package com.tasks.navigationcomponent.data

data  class GymDataModel(val id:Int,val name:String,val location:String,var isFavorite:Boolean=false)




fun listOfGyms():List<GymDataModel>{
    return listOf(
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),
        GymDataModel(1,name="Gym One ",location="this is dummy location",),


    )
}