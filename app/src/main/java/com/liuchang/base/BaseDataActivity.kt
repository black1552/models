package com.liuchang.base

import android.content.res.Resources
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.FileUtils

abstract class BaseDataActivity:FragmentActivity() {
    abstract var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.transparentStatusBar(this)
        binding.root.fitsSystemWindows = true
        initView()
    }

    abstract fun initView()

    fun saveFile(content: String, pathName: String) {
        FileUtils.createFileByDeleteOldFile(pathName)
        FileIOUtils.writeFileFromString(pathName, content)
    }

    fun addContentFile(content: String,pathName: String,isAdd:Boolean){
        FileIOUtils.writeFileFromString(pathName,content,isAdd)
    }

    fun getFileContext(pathName: String): List<String>? {
        return if (FileUtils.isFileExists(pathName)) {
            val context = FileIOUtils.readFile2List(pathName)
            if (!context.isNullOrEmpty()) {
                context
            } else {
                null
            }
        } else {
            null
        }
    }

    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080)
    }
}