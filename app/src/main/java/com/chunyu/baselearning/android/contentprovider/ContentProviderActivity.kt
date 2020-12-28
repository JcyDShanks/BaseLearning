package com.chunyu.baselearning.android.contentprovider

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chunyu.baselearning.R
import java.net.URI
import java.security.Permission

class ContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        initData()
    }

    private val dataSource = ArrayList<String>()

    @SuppressLint("Recycle")
    fun initData() {

        val contentResolver = contentResolver

        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            val size = cursor.columnCount
            var i = 0
            while (i < size) {
                dataSource.add(cursor.getString(i))
                i++
            }
        }
        cursor?.close()
    }

    val READ_CONSACT_CODE = 1001

    fun checkPermissionsResult() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.READ_CONTACTS), READ_CONSACT_CODE)
        } else {
            initData()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            READ_CONSACT_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData()
                } else {
                    Toast.makeText(this, "未授权无法使用", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}