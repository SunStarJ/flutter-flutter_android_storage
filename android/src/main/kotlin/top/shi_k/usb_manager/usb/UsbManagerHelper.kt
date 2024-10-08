package top.shi_k.usb_manager.usb

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import java.io.File


/**
 * 类名：UsbManagerHelper
 * 描述：
 * - 功能简述：USB管理工具
 * - 创建时间：2024-09-30 16:21
 * - 作者：孙浩
 *
 */

class UsbManagerHelper private constructor() {
    var storageManager: StorageManager? = null

    companion object {
        val instance by lazy { UsbManagerHelper() }
    }

    fun registerUsbManager(context: Context) {
        storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
    }


    fun getUsbList(): List<String> = storageManager?.storageVolumes?.map {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            it.directory?.path ?: ""
        } else {
            getPathBySystemMethod(it).path ?: ""
        }
    }?.toList() ?: emptyList<String>()

    fun onDestroy() {
        storageManager = null
    }

    private fun getPathBySystemMethod(storageVolume: StorageVolume): File {
        val storageClazz = Class.forName("android.os.storage.StorageVolume")
        val getPathMethod = storageClazz.getMethod("getPath")
        val path = getPathMethod.invoke(storageVolume) as String
        return File(path)
    }
}