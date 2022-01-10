package com.example.orange_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import androidx.lifecycle.lifecycleScope
import com.example.orange_task.databinding.ActivityMostafaBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class MostafaActivity : AppCompatActivity() {

    val dataStore: DataStore<Preferences> by lazy {  createDataStore(name = "first_task_with_data_store") }

    lateinit var binding: ActivityMostafaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostafaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnSet.setOnClickListener {
                et.text.toString().let { str ->
                    lifecycleScope.launch {
                        dataStore.setValue(USERNAME, str)
                    }
                }
            }

            btnGet.setOnClickListener {
                lifecycleScope.launch {
                    dataStore.getValueFlow(USERNAME,"default").collect {
                        et.setText(it)
                    }
                }
            }

        }



    }


    companion object{
        const val TYPE ="type"
        const val LINK ="link"
        const val WEB = "web"
        const val DEEP = "deep"
        private val USERNAME = preferencesKey<String>("username")
    }


    fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

    suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }
}