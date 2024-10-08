package top.shi_k.usb_manager


import android.content.Context
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import top.shi_k.usb_manager.usb.UsbManagerHelper
import java.io.File

/** UsbManagerPlugin */
class UsbManagerPlugin : FlutterPlugin, MethodCallHandler {
    val usbManagerHelper = UsbManagerHelper.instance

    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "usb_manager")
        channel.setMethodCallHandler(this)
        usbManagerHelper.registerUsbManager(flutterPluginBinding.applicationContext)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "getUsbList" -> {
                val list: List<String> = usbManagerHelper.getUsbList().map { it }.toList<String>()
                list.forEach {
                    val file = File(it)
                    if (file.exists()) {
                        println("file: ${file.name}")
                        println("file_exists: ${file.exists()}")
                    }
                }
                result.success(list)
            }

            "isUsbPermission" -> {
//        val permission = call.argument<String>("permission")
//        result.success(usbManagerHelper.isUsbPermission(permission!!))
            }

            else -> result.notImplemented()
        }
    }


    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        usbManagerHelper.onDestroy()
    }
}
