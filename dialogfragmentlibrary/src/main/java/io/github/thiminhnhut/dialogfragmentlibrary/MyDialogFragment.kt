package io.github.thiminhnhut.dialogfragmentlibrary

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import io.github.thiminhnhut.dialogfragmentlibrary.model.DialogModel

class MyDialogFragment : DialogFragment() {

    interface MyDialogFragmentListener {
        fun onConfirm()
        fun onCancel()
    }

    private lateinit var listener: MyDialogFragmentListener

    companion object {
        private lateinit var dialogModel: DialogModel

        fun newInstance(listener: MyDialogFragmentListener): MyDialogFragment {
            val myDialogFragmentConfirm = MyDialogFragment()
            myDialogFragmentConfirm.listener = listener
            return myDialogFragmentConfirm
        }

        fun newInstance(listener: MyDialogFragmentListener, dialogModel: DialogModel): MyDialogFragment {
            this.dialogModel = dialogModel
            val myDialogFragmentConfirm = MyDialogFragment()
            myDialogFragmentConfirm.listener = listener
            return myDialogFragmentConfirm
        }
    }

    fun setDialogModel(model: DialogModel) {
        dialogModel = model
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            dialog!!.setCanceledOnTouchOutside(false)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle(dialogModel.title)
            .setMessage(dialogModel.title)
            .setPositiveButton(dialogModel.confirm) { _, _ ->
                listener.onConfirm()
            }
        if (dialogModel.cancel != null) {
            builder.setNegativeButton(dialogModel.cancel) { _, _ ->
                listener.onCancel()
            }
        }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(dialogModel.canceledOnTouchOutside)

        return dialog
    }
}