import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:usb_manager/usb_manager.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _usbManagerPlugin = UsbManager();

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    final List<String>? result = await _usbManagerPlugin.getUsbList();
    result?.forEach((e) async {
      print(
          "===================================start=======================================");
      Directory file = Directory(e);
      bool exists = await file.exists();
      print("check file ,name = $e");
      print("check file exits = ${exists}");
      print(
          "===================================end=======================================");
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
      ),
    );
  }
}
