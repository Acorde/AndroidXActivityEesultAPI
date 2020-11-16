package com.igor.androidxactivityresultapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    companion object {
        const val ID = "post_activity"
        const val TITLE = "title"

        fun getIntent(context: Context, postId: Int): Intent {
            return Intent(context, PostActivity::class.java).apply {
                putExtra(ID, postId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        back_with_data.setOnClickListener {
            val intent = Intent().apply {
                putExtra(TITLE, "hello")
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}