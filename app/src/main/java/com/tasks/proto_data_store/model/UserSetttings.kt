//package com.tasks.proto_data_store.model
//
//
//
//import kotlinx.collections.immutable.PersistentList
//import kotlinx.collections.immutable.persistentListOf
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class UserSettings(
//    var langauge: Langauge = Langauge.ENGLISH,
//    val userLocations: PersistentList<UserAddress> = persistentListOf()
//)
//
//
//enum class Langauge {
//    ENGLISH, GERMAN, FRENCH
//}
//
//@Serializable
//data class UserAddress(val lat: Long, val long: Long)