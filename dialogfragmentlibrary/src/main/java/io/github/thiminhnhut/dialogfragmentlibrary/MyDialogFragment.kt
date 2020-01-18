package io.github.thiminhnhut.dialogfragmentlibrary

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import io.github.thiminhnhut.dialogfragmentlibrary.model.DialogModel

class MyDialogFragment : DialogFragment() {

    interface MyDialogFragmentListener {
        fun onConfirmMessage(option: Any? = null) = Unit
        fun onCancelMessage(option: Any? = null) = Unit
    }

    private var listener: MyDialogFragmentListener? = null
    private var mOption: Any? = null

    companion object {
        private lateinit var dialogModel: DialogModel

        fun newInstance(listener: MyDialogFragmentListener? = null): MyDialogFragment {
            val myDialogFragmentConfirm = MyDialogFragment()
            myDialogFragmentConfirm.listener = listener
            return myDialogFragmentConfirm
        }

        fun newInstance(
            listener: MyDialogFragmentListener,
            dialogModel: DialogModel
        ): MyDialogFragment {
            this.dialogModel = dialogModel
            val myDialogFragmentConfirm = MyDialogFragment()
            myDialogFragmentConfirm.listener = listener
            return myDialogFragmentConfirm
        }
    }

    fun setListener(listener: MyDialogFragmentListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)
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
            listener?.onConfirmMessage(mOption)
        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            dialog.dismiss()
            listener?.onCancelMessage(mOption)
        }

        return dialog
    }

    override fun onResume() {
        super.onResume()
        dialog?.setOnKeyListener { dialog, keyCode, event ->
            true
        }
    }

    fun show(fragmentActivity: FragmentActivity, model: DialogModel, option: Any? = null) {
        dialogModel = model
        this.mOption = option
        if (this.isResumed && !this.isDetached) {
            this.dismiss()
        }
        this.show(fragmentActivity.supportFragmentManager, null)
    }
}