package com.jaax.senseme.mvp.recordatorios

import com.jaax.senseme.api.RecordatorioService
import com.jaax.senseme.api.RetrofitClient

class Model: RecordatorioMVP.Model {
    override fun initService(): RecordatorioService {
        return RetrofitClient.getService( RecordatorioService::class.java )
    }
}