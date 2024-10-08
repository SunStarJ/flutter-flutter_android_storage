import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'usb_manager_platform_interface.dart';

/// An implementation of [UsbManagerPlatform] that uses method channels.
class MethodChannelUsbManager extends UsbManagerPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('usb_manager');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<List<String>> getUsbList() async {
    final List<dynamic>? list =
        await methodChannel.invokeMethod<List<dynamic>>('getUsbList');
    return list?.map((e) => e.toString()).toList() ?? <String>[];
  }
}
