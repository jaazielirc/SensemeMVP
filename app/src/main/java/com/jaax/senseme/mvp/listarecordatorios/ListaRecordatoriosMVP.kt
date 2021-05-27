package com.jaax.senseme.mvp.listarecordatorios

import com.jaax.senseme.api.RecordatorioService
import com.jaax.senseme.utils.Recordatorio

interface ListaRecordatoriosMVP {
    interface Model {
        fun initService(): RecordatorioService
    }

    interface Presenter {
        fun getAllRecordatorios()
        fun updateRecordatorio( recordatorio: Recordatorio)
        fun deleteRecordatorio( id: Int )
        fun getCurrentDate(): String
    }

    interface View {
        fun recordatorioUpdated( updated: Boolean )
        fun recordatorioDeleted( deleted: Boolean )
        fun showAllRecordatorios( list: ArrayList<Recordatorio> )
    }
}