package com.example.sehati

import android.app.Dialog
import android.content.Context
import com.example.sehati.R

class ProgressDialog(context: Context): Dialog(context) {
    init {
        setContentView(R.layout.progress_layout)
    }
}