package com.tasks.data_stores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.tasks.navigationcomponent.databinding.LayoutDataStoreBinding
import kotlinx.coroutines.flow.first

class DataStoreScreen : AppCompatActivity() {
    lateinit var  binding: LayoutDataStoreBinding
    lateinit var  dataStore:DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=LayoutDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = createDataStore(name = "Profile")



        binding.btnSave.setOnClickListener {
            val key =binding.txtKey.text.toString()
            val value =binding.txtValue.text.toString()
            lifecycleScope.launchWhenStarted {
                writePref(key,value)
            }
        }

        binding.btnGet.setOnClickListener {
            val key =binding.txtKey.text.toString()

            lifecycleScope.launchWhenStarted {
                val value = readPref(key)
                binding.txtData.setText(value)
            }
        }

    }


    private suspend fun writePref(key:String,value:String){
        val preferencesKey = preferencesKey<String>(key)

        dataStore.edit { profile->
            profile[preferencesKey]=value
        }
    }

    private suspend fun readPref(key:String):String{
        val preferencesKey = preferencesKey<String>(key)
        return  dataStore.data.first()[preferencesKey]?:""
    }
}
