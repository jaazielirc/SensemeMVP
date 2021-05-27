package com.jaax.senseme.mvp.listarecordatorios

import android.util.Log
import com.jaax.senseme.utils.Constantes
import com.jaax.senseme.utils.Recordatorio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class Presenter( private val view: ListaRecordatoriosMVP.View ): ListaRecordatoriosMVP.Presenter {

    private val model: ListaRecordatoriosMVP.Model = Model()
    private val recordatorioService = model.initService()

    override fun getAllRecordatorios() {
        val call = recordatorioService.getAllRecordatorios()

        call.enqueue( object : Callback<List<Recordatorio>> {
            override fun onResponse(
                call: Call<List<Recordatorio>>,
                response: Response<List<Recordatorio>>
            ) {
                if( response.isSuccessful ){
                    val recordatorioResponse = response.body()
                    val listRecordatorio = recordatorioResponse as ArrayList
                    view.showAllRecordatorios(listRecordatorio)
                } else {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "No Success" )
                }
            }

            override fun onFailure(call: Call<List<Recordatorio>>, t: Throwable) {
                Log.e( Constantes.TAG_LOG_RECORDATORIOS, t.message!! )
            }
        })
    }

    override fun updateRecordatorio( recordatorio: Recordatorio ) {
        val call = recordatorioService.updateRecordatorio( recordatorio )

        call.enqueue( object : Callback<Recordatorio> {
            override fun onResponse(call: Call<Recordatorio>, response: Response<Recordatorio>) {
                if( response.isSuccessful ) {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "Recordatorio actualizado" )
                    view.recordatorioUpdated( true )
                } else {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "No Success" )
                }
            }

            override fun onFailure(call: Call<Recordatorio>, t: Throwable) {
                Log.e( Constantes.TAG_LOG_RECORDATORIOS, t.message!! )
            }
        })
    }

    override fun deleteRecordatorio( id: Int ) {
        val call = recordatorioService.deleteRecordatorioById( id )

        call.enqueue( object : Callback<Recordatorio> {
            override fun onResponse(call: Call<Recordatorio>, response: Response<Recordatorio>) {
                if( response.isSuccessful ) {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "Recordatorio eliminado" )
                    view.recordatorioDeleted( true )
                } else {
                    Log.i( Constantes.TAG_LOG_RECORDATORIOS, "No Success" )
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