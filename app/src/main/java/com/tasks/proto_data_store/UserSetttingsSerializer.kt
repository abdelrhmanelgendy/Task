//package com.tasks.proto_data_store
//
//import androidx.datastore.core.Serializer
//import com.tasks.proto_data_store.model.UserSettings
//import kotlinx.serialization.SerializationException
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//import java.io.InputStream
//import java.io.OutputStream
//
//
//class UserSettingsSerializer : Serializer<UserSettings> {
//
//
//    override val defaultValue: UserSettings
//        get() = UserSettings()
//
//    override suspend fun readFrom(input: InputStream): UserSettings {
//        return try {
//            Json.decodeFromString(UserSettings.serializer(), input.readBytes().decodeToString())
//
//        } catch (e: SerializationException) {
//            e.printStackTrace()
//            defaultValue
//        }
//    }
//
//    override suspend fun writeTo(t: UserSettings, output: OutputStream) {
//        output.write(Json.encodeToString(UserSettings.serializer(),t).encodeToByteArray())
//    }
//}