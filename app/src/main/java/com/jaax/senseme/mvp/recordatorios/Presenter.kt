package com.jaax.senseme.mvp.recordatorios

import android.util.Log
import com.jaax.senseme.utils.Constantes
import com.jaax.senseme.utils.Recordatorio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Presenter( private val view: RecordatorioMVP.View ): RecordatorioMVP.Presenter {
    private val model: RecordatorioMVP.Model = Model()
    private val recordatorioService = model.initService()

    override fun saveNewRecordatorio( recordatorio: Recordatorio) {
        val call = recordatorioService.saveRecordatorio( recordatorio )

        call.enqueue( object : Callback<Recordatorio> {
            override fun onResponse(call: Call<Recordatorio>, response: Response<Recordatorio>) {
                if( response.isSuccessful ) {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "Recordatorio guardado" )
                    view.recordatorioSaved( true )
                } else {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "No Success ${response.body()}" )
                }
            }

            override fun onFailure(call: Call<Recordatorio>, t: Throwable) {
                Log.e( Constantes.TAG_LOG_RECORDATORIOS, t.message!! )
            }
        })
    }

    override fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get( Calendar.YEAR )
        val month = calendar.get( Calendar.MONTH )
        val day = calendar.get( Calendar.DAY_OF_MONTH )
        val hour = calendar.get( Calendar.HOUR )
        val minutes = calendar.get( Calendar.MINUTE )
        val date = Date( year, month, day, hour, minutes )

        return "${date.day}/${date.month}/${date.year} a las ${date.hours}:${date.minutes}"
    }
}