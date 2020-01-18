# Dialog Fragment Library for Android

- **Thực hiện:** Thi Minh Nhựt - **Email:** <thiminhnhut@gmail.com> - **Github:** <https://github.com/thiminhnhut>

- **Thời gian:** Ngày 16 tháng 05 năm 2019

- **Source:** <https://github.com/thiminhnhut/DialogFragmentLibrary>

## Nguồn tham khảo

- <https://codinginflow.com/tutorials/android/simple-alertdialog>

- <https://codinginflow.com/tutorials/android/alertdialog-interface>

- <https://codinginflow.com/tutorials/android/custom-dialog-interface>

## Nội dung

## Add Library to Android Project

- Trong `settings.gradle`:

  ```gradle
  include ':dialogfragmentlibrary'
  project(':dialogfragmentlibrary').projectDir = new File('DialogFragmentLibrary/dialogfragmentlibrary')
  ```

  - Trong `build.gradle (app)`:

  ```gradle
  implementation project(path: ':dialogfragmentlibrary')
  ```

### Sử dụng thư viện

- Khai báo:

  ```kotlin
  //Default Dialog Fragment
  private lateinit var customDialogFragment: CustomDialogFragment

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

  myDialogFragment.show(supportFragmentManager, null)

  // Custom Dialog Fragment
  private lateinit var customDialogFragment: CustomDialogFragment

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

    customDialogFragment.show(supportFragmentManager, null)
  ```

- Tùy chỉnh ẩn hoặc hiện `BUTTON_NEGATIVE` thông qua biến `cancel` trong `DialogModel`:

  - `cancel = null`: ẩn nút `BUTTON_NEGATIVE`.

  - `cancel != null`: hiện nút `BUTTON_NEGATIVE`.

- Thay đổi một số thuộc tính trong dialog fragment thông qua `DialogModel`:

  ```kotlin
  data class DialogModel(
    var title: String,
    var message: String,
    var confirm: String,
    var cancel: String?,
    var canceledOnTouchOutside: Boolean
  )
  ```

- Tự quản lý dimiss dialog thông qua: `dialog.dismiss()` trong `interface MyDialogFragmentListener` (do đã override dialog button).

- Interface trong `MyDialogFragment`:

  ```kotlin
  interface MyDialogFragmentListener {
        fun onConfirm(dialog: Dialog)
        fun onCancel(dialog: Dialog)
  }
  ```

  - `dismiss()` thông qua biến `dialog`: `dialog.dismiss()`.

- Interface trong `CustomDialogFragment`:

  ```kotlin
  interface MyDialogFragmentListener {
        fun onConfirm(dialog: Dialog, view: View)
        fun onCancel(dialog: Dialog, view: View)
  }
  ```

  - `dismiss()` thông qua biến `dialog`: `dialog.dismiss()`.

  - Lấy dữ liệu và bắt sự kiện trong `custom view` thông qua biến `view` (ví dụ: `val edtName = view.findViewById<EditText>(R.id.edtName)`).

### TODO

- Bổ sung thêm một số tùy chỉnh linh hoạt hơn cho Dialog Fragment.
