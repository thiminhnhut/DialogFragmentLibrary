package io.github.thiminhnhut.dialogfragmentlibrary

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import io.github.thiminhnhut.dialogfragmentlibrary.model.DialogModel

class CustomDialogFragment : DialogFragment() {

    interface MyDialogFragmentListener {
        fun onConfirm(dialog: Dialog, view: View)
        fun onCancel(dialog: Dialog, view: View)
    }

    private lateinit var listener: MyDialogFragmentListener

    companion object {
        private lateinit var dialogModel: DialogModel
        private var resId = -1

        fun newInstance(listener: MyDialogFragmentListener, resId: Int): CustomDialogFragment {
            this.resId = resId
            val myDialogFragmentConfirm = CustomDialogFragment()
            myDialogFragmentConfirm.listener = listener
            return myDialogFragmentConfirm
        }

        fun newInstance(listener: MyDialogFragmentListener, resId: Int, dialogModel: DialogModel): CustomDialogFragment {
            this.dialogModel = dialogModel
            this.resId = resId
            val myDialogFragmentConfirm = CustomDialogFragment()
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
        val view = activity!!.layoutInflater.inflate(resId, null)
        val builder = AlertDialog.Builder(activity!!)
        builder.setView(view)
            .setTitle(dialogModel.title)
            .setPositiveButton(dialogModel.confirm, null)
            if (dialogModel.cancel != null) {
                builder.setNegativeButton(dialogModel.cancel, null)
            }

        val dialog = builder.show()
        dialog.setCanceledOnTouchOutside(dialogModel.canceledOnTouchOutside)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            listener.onConfirm(dialog, view)
        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            listener.onCancel(dialog, view)
        }

        return dialog
    }
}