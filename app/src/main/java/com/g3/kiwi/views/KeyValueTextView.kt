package com.g3.kiwi.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.g3.kiwi.databinding.KeyValueTextViewBinding

class KeyValueTextView : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding = KeyValueTextViewBinding.inflate(LayoutInflater.from(context), this, true)

    var configuration: KeyValueTextViewConfiguration? = null
        set(value) {
            field = value
            value?.let { setupView(it) }
        }

    @SuppressLint("SetTextI18n")
    private fun setupView(configuration: KeyValueTextViewConfiguration) {
        binding.keyTV.setText(configuration.key)
        binding.valueTV.text = configuration.value
    }

    data class KeyValueTextViewConfiguration(
        @StringRes val key: Int,
        val value: String?
    )
}