package com.jaax.senseme.mvp.recordatorios

import com.jaax.senseme.api.RecordatorioService
import com.jaax.senseme.utils.Recordatorio

interface RecordatorioMVP {

    interface Model {
        fun initService(): RecordatorioService
    }

    interface Presenter {
        fun saveNewRecordatorio( recordatorio: Recordatorio)
        fun getCurrentDate(): String
    }

    interface View {
        fun recordatorioSaved( saved: Boolean )
    }
}