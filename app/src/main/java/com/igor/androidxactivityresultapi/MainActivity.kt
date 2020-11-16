package com.igor.androidxactivityresultapi

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // General activity result contract
    private val openPostActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Do something here
                    toast("Result OK from PostActivity The extra data is ${result.data?.getStringExtra("title")}")
                }
            }

    // Custom activity result contract
    private val openPostActivityCustom =
            registerForActivityResult(ResultContract()) { result ->
                if (result != null) toast("Result : $result")
                else toast("No Result")
            }

    // Request permission contract
    private val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                // Do something if permission granted
                if (isGranted) toast("Permission is granted")
                else toast("Permission is denied")
            }

    // Request multiple permissions contract
    private val requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                // Do something if some permissions granted or denied
                permissions.entries.forEach {
                    it.key
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_activity_general.setOnClickListener {
            openPostActivity.launch(
                    PostActivity.getIntent(this, 1)
            )
        }

        start_activity_contract.setOnClickListener {
            openPostActivityCustom.launch(1)
        }

        request_permission.setOnClickListener {
            requestPermission.launch(Manifest.permission.BLUETOOTH)
        }

        request_multiple_permission.setOnClickListener {
            requestMultiplePermissions.launch(
                    arrayOf(
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.NFC,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    )
            )
        }

    }


    private fun Context?.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}