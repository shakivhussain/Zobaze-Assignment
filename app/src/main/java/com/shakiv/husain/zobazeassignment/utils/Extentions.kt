package com.shakiv.husain.zobazeassignment.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shakiv.husain.zobazeassignment.domain.University
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart


fun Fragment.openUrl(url: String) {

    var completeUrl = url

    if (!url.startsWith("http://") && !url.startsWith("https://"))
        completeUrl = "https://$url";
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(completeUrl))
    startActivity(browserIntent)
}

fun Fragment.getUrl(university: University): String {
    val url = university.webPages?.getOrNull(0)
    return url ?: ""
}


fun Fragment.getStringById(string: Int): String {
    return ContextCompat.getString(context ?: requireContext(), string)
}


@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}