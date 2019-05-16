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
        fun onConfirm(view: View)
        fun onCancel(view: View)
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
            .setPositiveButton(dialogModel.confirm) { _, _ ->
                listener.onConfirm(view)
            }
            if (dialogModel.cancel != null) {
                builder.setNegativeButton(dialogModel.cancel) { _, _ ->
                    listener.onCancel(view)
                }
            }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(dialogModel.canceledOnTouchOutside)

        return dialog
    }
}