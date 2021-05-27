package com.jaax.senseme.mvp.listarecordatorios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.jaax.senseme.R
import com.jaax.senseme.utils.Recordatorio

class RecordatorioAdapter( private val context: Context, private val manager: FragmentManager )
    : RecyclerView.Adapter<RecordatorioAdapter.ViewHolder>() {

    private var listRecordatorios = mutableListOf<Recordatorio>()

    inner class ViewHolder( view: View ): RecyclerView.ViewHolder( view ) {
        var txtID: TextView = view.findViewById(R.id.txtID)
        var txtRecordatorio: TextView = view.findViewById(R.id.txtTextoRecordatorio)
        var txtFecha: TextView = view.findViewById(R.id.txtFecha)
        var btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        var btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from( context )
            .inflate( R.layout.adapter_recordatorio, parent, false )

        return ViewHolder( view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recordatorio = listRecordatorios[position]

        holder.txtID.text = recordatorio.id.toString()
        holder.txtRecordatorio.text = recordatorio.text
        holder.txtFecha.text = recordatorio.date

        holder.btnEdit.setOnClickListener {
            DialogUpdateRecordatorio( recordatorio ).show( manager, "update" )
        }

        holder.btnDelete.setOnClickListener {
            DialogDeleteRecordatorio( recordatorio.id ).show( manager, "delete" )
        }
    }

    override fun getItemCount(): Int {
        return listRecordatorios.size
    }

    fun addAllRecordatorios( list: ArrayList<Recordatorio> ){
        listRecordatorios.clear()
        listRecordatorios.addAll( list )
        notifyDataSetChanged()
    }
}