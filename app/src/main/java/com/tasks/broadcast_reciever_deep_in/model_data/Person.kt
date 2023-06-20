package com.tasks.broadcast_reciever_deep_in.model_data

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

data  class Person(  val name:String,private val age:Int,private val address:LatLng):java.io.Serializable