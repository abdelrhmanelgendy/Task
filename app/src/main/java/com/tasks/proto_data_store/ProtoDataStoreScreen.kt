//package com.tasks.proto_data_store
//
//import android.content.Context
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.datastore.dataStore
//import androidx.lifecycle.lifecycleScope
//import com.tasks.navigationcomponent.databinding.LayoutProtoDataStoreBinding
//import com.tasks.proto_data_store.model.Langauge
//import com.tasks.proto_data_store.model.UserAddress
//import kotlinx.coroutines.GlobalScope
//
//
//class ProtoDataStoreScreen : AppCompatActivity() {
//    lateinit var binding: LayoutProtoDataStoreBinding
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = LayoutProtoDataStoreBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        lifecycleScope.launchWhenStarted {
//            dataStore.data.collectLatest { settings ->
//                binding.txtData.append("\n")
//                binding.txtData.append(settings.toString())
//            }
//        }
//
//
//
//
//
//        binding.btnSave.setOnClickListener {
//
//            GlobalScope.launch {
//                dataStore.updateData {
//                    it.copy(Langauge.GERMAN, it.userLocations.mutate {
//                        it.add(UserAddress(Random(545454).nextLong(), Random(545454).nextLong()))
//                    })
//                }
//            }
//
//        }
//
//        binding.btnGet.setOnClickListener {
//            val key = binding.txtKey.text.toString()
//
//            lifecycleScope.launchWhenStarted {
//
//            }
//        }
//
//    }
//
//
//}
//
//val Context.dataStore by dataStore("settings.json", UserSettingsSerializer)
