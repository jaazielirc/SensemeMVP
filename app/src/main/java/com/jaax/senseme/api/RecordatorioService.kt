package com.jaax.senseme.api

import com.jaax.senseme.utils.Constantes
import com.jaax.senseme.utils.Recordatorio
import com.jaax.senseme.utils.RecordatorioResponse
import retrofit2.Call
import retrofit2.http.*

interface RecordatorioService {

    @GET( Constantes.ENDPOINT_RECORDATORIOS )
    fun getAllRecordatorios(): Call<List<Recordatorio>>

    /*@GET( "/{id}" )
    fun getRecordatorioById( @Path("id") id: Int ): Call<Recordatorio>*/

    @POST( Constantes.ENDPOINT_RECORDATORIOS )
    fun saveRecordatorio( @Body recordatorio: Recordatorio ): Call<Recordatorio>

    @PUT( Constantes.ENDPOINT_RECORDATORIOS )
    fun updateRecordatorio( @Body recordatorio: Recordatorio ): Call<Recordatorio>

    @DELETE( "${Constantes.ENDPOINT_RECORDATORIOS}/{id}" )
    fun deleteRecordatorioById( @Path("id") id: Int ): Call<Recordatorio>
}