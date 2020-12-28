package com.chunyu.baselearning.android.contentprovider

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.widget.Toast

class MyContentProvider: ContentProvider() {

    companion object {
        private const val CONTACT = 1
        private const val PACKAGE_ID = "com.chunyu.baselearning"
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(PACKAGE_ID,"contact",CONTACT)
            addURI(PACKAGE_ID, "table", TABLE_DIR)
            addURI(PACKAGE_ID, "table/#", TABLE_ITEM)
        }

        private const val TABLE_DIR = 10001
        private const val TABLE_ITEM = 10002
    }

    private var contentResolver: ContentResolver? = null
    // 6
    override fun onCreate(): Boolean {
        contentResolver = context?.contentResolver
        return false
    }
    // 1
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when(uriMatcher.match(uri)) {
            TABLE_DIR -> {

            }
            TABLE_ITEM -> {

            }
            else -> {

            }
        }
        // TODO
        return null
    }
    // 2
    override fun getType(uri: Uri): String? {
        when(uriMatcher.match(uri)) {
            TABLE_DIR -> {
                return "vnd.android.cursor.dir/vnd.com.chunyu.baselearning.android.contentprovider.table"
            }
            TABLE_ITEM -> {

            }
            else -> {

            }
        }
        return null
    }
    // 3
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when(uriMatcher.match(uri)) {
            TABLE_DIR -> {

            }
            TABLE_ITEM -> {

            }
            else -> {

            }
        }
        return null
    }
    // 4
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when(uriMatcher.match(uri)) {
            TABLE_DIR -> {
            }
            TABLE_ITEM -> {

            }
            else -> {

            }
        }
        return -1
    }
    // 5
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        when(uriMatcher.match(uri)) {
            TABLE_DIR -> {
            }
            TABLE_ITEM -> {

            }
            else -> {

            }
        }
        return -1
    }
}