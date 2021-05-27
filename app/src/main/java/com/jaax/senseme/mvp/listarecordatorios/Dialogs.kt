package com.jaax.senseme.mvp.listarecordatorios

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.jaax.senseme.R
import com.jaax.senseme.databinding.FragmentUpdateBinding
import com.jaax.senseme.utils.Recordatorio
import java.lang.ClassCastException

class DialogUpdateRecordatorio( private val recordatorio: Recordatorio ): DialogFragment() {

    private var _bind: FragmentUpdateBinding? = null
    private val bind get() = _bind!!
    private var callback: UpdateListener? = null

    interface UpdateListener {
        fun updateText( newRecordatorio: Recordatorio )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as? UpdateListener
        } catch( cce: ClassCastException ) {
            cce.printStackTrace()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate( R.layout.fragment_update, null )
        val builder = AlertDialog.Builder( activity )

        builder.setView( view )
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentUpdateBinding.inflate( layoutInflater )
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        bind.btnUpdate.setOnClickListener {
            Toast.makeText(activity!!.applicationContext, "funciona", Toast.LENGTH_SHORT).show()
        }
    }
}

class DialogDeleteRecordatorio( private val idToDelete: Int ): DialogFragment() {

    private var callback: DeleteListener? = null

    interface DeleteListener {
        fun isDeleting( id: Int )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as? DeleteListener
        } catch(cce: ClassCastException) {
            cce.printStackTrace()
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder( activity )

        builder
            .setTitle( "Borrar?" )
            .setPositiveButton( "Sí" ) { _, _ ->
                callback?.isDeleting( idToDelete )
                this.dismiss()
            }.show()
        return super.onCreateDialog(savedInstanceState)
    }
}