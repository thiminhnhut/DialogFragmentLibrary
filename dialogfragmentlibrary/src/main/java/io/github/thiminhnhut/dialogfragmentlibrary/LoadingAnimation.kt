package io.github.thiminhnhut.dialogfragmentlibrary

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import io.github.thiminhnhut.dialogfragmentlibrary.model.LoadingAnimationModel
import kotlinx.android.synthetic.main.loading_animation.*

class LoadingAnimation : DialogFragment() {

    companion object {
        private var loadingAnimationModel: LoadingAnimationModel? = null

        fun newInstance(loadingAnimationModel: LoadingAnimationModel? = null): LoadingAnimation {
            this.loadingAnimationModel = loadingAnimationModel
            return LoadingAnimation()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (loadingAnimationModel == null) {
            loadingAnimationModel = LoadingAnimationModel(R.color.colorLoadingAnimation)
        }

        return inflater.inflate(R.layout.loading_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingAnimationModel?.also {
            spin_kit.setColor(ContextCompat.getColor(context!!, it.cRes))
        }
    }

    fun open(fragmentActivity: FragmentActivity) {
        close()
        this.show(fragmentActivity.supportFragmentManager, null)
    }

    fun close() {
        if (this.isResumed && !this.isDetached) {
            this.dismiss()
        }
    }
}