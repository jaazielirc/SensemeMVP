package com.jaax.senseme.mvp.recordatorios

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaax.senseme.databinding.ActivityMainBinding
import com.jaax.senseme.mvp.listarecordatorios.ListarRecordatorios
import com.jaax.senseme.utils.Recordatorio

class MainActivity: AppCompatActivity(), RecordatorioMVP.View {
    private lateinit var bind: ActivityMainBinding
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate( layoutInflater )
        setContentView( bind.root )

        presenter = Presenter( this )
    }

    override fun onResume() {
        super.onResume()

        bind.btnSave.setOnClickListener {
            val cuerpoRecordatorio = bind.edTxtTexto.text
            val fecha = presenter.getCurrentDate()
            val recordatorio = Recordatorio( 0, cuerpoRecordatorio.toString(), fecha )
            bind.edTxtTexto.setText( "" )
            presenter.saveNewRecordatorio( recordatorio )
        }

        bind.btnSeeMemories.setOnClickListener {
            val intent = Intent( this, ListarRecordatorios::class.java )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity( intent )
        }
    }

    override fun recordatorioSaved( saved: Boolean ) {
        if( saved ) Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
    }
}