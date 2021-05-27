package com.jaax.senseme.mvp.listarecordatorios

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaax.senseme.databinding.ActivityListBinding
import com.jaax.senseme.utils.Recordatorio

class ListarRecordatorios
    : AppCompatActivity(),
    ListaRecordatoriosMVP.View,
    DialogUpdateRecordatorio.UpdateListener,
    DialogDeleteRecordatorio.DeleteListener {

    private lateinit var bind: ActivityListBinding
    private lateinit var presenter: Presenter
    private lateinit var adapter: RecordatorioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityListBinding.inflate( layoutInflater )
        setContentView( bind.root )

        presenter = Presenter( this )
        adapter = RecordatorioAdapter( this, supportFragmentManager )
    }

    override fun onStart() {
        super.onStart()
        val layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,
            false)
        bind.recyclerView.setHasFixedSize( true )
        bind.recyclerView.layoutManager = layoutManager
        bind.recyclerView.adapter = adapter
        presenter.getAllRecordatorios()
    }

    override fun onResume() {
        super.onResume()

        bind.swipeRefresh.setOnRefreshListener {
            presenter.getAllRecordatorios()
            bind.swipeRefresh.isRefreshing = false
        }
    }

    override fun recordatorioUpdated(updated: Boolean){
        if( updated ) Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show()
    }

    override fun recordatorioDeleted(deleted: Boolean) {
        if( deleted ) Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
    }

    override fun showAllRecordatorios(list: ArrayList<Recordatorio>) {
        adapter.addAllRecordatorios( list )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun updateText(newRecordatorio: Recordatorio) {
        presenter.updateRecordatorio( newRecordatorio )
    }

    override fun isDeleting(id: Int) {
        presenter.deleteRecordatorio( id )
    }
}