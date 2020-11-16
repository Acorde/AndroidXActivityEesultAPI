package com.igor.androidxactivityresultapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ResultContract : ActivityResultContract<Int, String>() {
    /** Create an intent that can be used for [Activity.startActivityForResult]  */
    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, PostActivity::class.java).apply {
            this.putExtra(PostActivity.ID, input)
        }
    }

    /** Convert result obtained from [Activity.onActivityResult] to O  */
    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(PostActivity.ID)
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else null
    }
}