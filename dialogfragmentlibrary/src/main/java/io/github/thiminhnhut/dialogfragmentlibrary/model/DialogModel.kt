package io.github.thiminhnhut.dialogfragmentlibrary.model

data class DialogModel(
    var title: String,
    var message: String,
    var confirm: String,
    var cancel: String?,
    var canceledOnTouchOutside: Boolean
)