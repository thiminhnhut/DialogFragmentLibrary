package io.github.thiminhnhut.dialogfragmentlibrary

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.github.thiminhnhut.dialogfragmentlibrary.model.DialogModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var myDialogFragment: MyDialogFragment
    private lateinit var customDialogFragment: CustomDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDialogFragment =
            MyDialogFragment.newInstance(object : MyDialogFragment.MyDialogFragmentListener {
                override fun onConfirm(dialog: Dialog) {
                    Toast.makeText(applicationContext, "On Click OK", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

                override fun onCancel(dialog: Dialog) {
                    Toast.makeText(applicationContext, "On Click Cancel", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

            }, DialogModel("Dialog Title", "This is a Dialog", "OK", null, false))

        customDialogFragment =
            CustomDialogFragment.newInstance(object : CustomDialogFragment.MyDialogFragmentListener {
                override fun onConfirm(dialog: Dialog, view: View) {
                    val edtName = view.findViewById<EditText>(R.id.edtName)
                    Toast.makeText(applicationContext, "${edtName.text}", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

                override fun onCancel(dialog: Dialog, view: View) {
                    Toast.makeText(applicationContext, "On Click Cancel", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }, R.layout.custom_dialog, DialogModel("Dialog Title", "This is a Dialog", "OK", null, false))

        btnShow.setOnClickListener {
//            myDialogFragment.show(supportFragmentManager, null)
            customDialogFragment.show(supportFragmentManager, null)
        }
    }
}
