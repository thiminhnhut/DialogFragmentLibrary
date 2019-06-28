package io.github.thiminhnhut.dialogfragmentlibrary

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import io.github.thiminhnhut.dialogfragmentlibrary.model.DialogModel

class MyDialogFragment : DialogFragment() {

    interface MyDialogFragmentListener {
        fun onConfirm() = Unit
        fun onCancel() = Unit
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle(dialogModel.title)
            .setMessage(dialogModel.message)
            .setPositiveButton(dialogModel.confirm, null)
        if (dialogModel.cancel != null) {
            builder.setNegativeButton(dialogModel.cancel, null)
        }

        val dialog = builder.show()
        dialog.setCanceledOnTouchOutside(dialogModel.canceledOnTouchOutside)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            dialog.dismiss()
            listener.onConfirm()
        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            dialog.dismiss()
            listener.onCancel()
        }

        return dialog
    }
}