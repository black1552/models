package com.liuchang.base

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.*


abstract class BaseActivity: FragmentActivity() {

    abstract var binding:ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.transparentStatusBar(this)
        binding.root.fitsSystemWindows = true
        testNetWork()
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

    fun testNetWork(){
        if (!NetworkUtils.isAvailable()) {
            AlertDialog.Builder(this).setTitle("警告").setMessage("当前网络异常，请稍后再试！").setNegativeButton("确定",null).show()
            onBackPressedDispatcher
            return
        }
    }

    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080)
    }
}
