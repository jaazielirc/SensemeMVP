package com.jaax.senseme.mvp.listarecordatorios

import com.jaax.senseme.api.RecordatorioService
import com.jaax.senseme.api.RetrofitClient

class Model: ListaRecordatoriosMVP.Model {
    override fun initService(): RecordatorioService {
        return RetrofitClient.getService( RecordatorioService::class.java )
    }
}