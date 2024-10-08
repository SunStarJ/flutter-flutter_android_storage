// class MockUsbManagerPlatform
//     with MockPlatformInterfaceMixin
//     implements UsbManagerPlatform {
//
//   @override
//   Future<String?> getPlatformVersion() => Future.value('42');
// }
//
// void main() {
//   final UsbManagerPlatform initialPlatform = UsbManagerPlatform.instance;
//
//   test('$MethodChannelUsbManager is the default instance', () {
//     expect(initialPlatform, isInstanceOf<MethodChannelUsbManager>());
//   });
//
//   test('getPlatformVersion', () async {
//     UsbManager usbManagerPlugin = UsbManager();
//     MockUsbManagerPlatform fakePlatform = MockUsbManagerPlatform();
//     UsbManagerPlatform.instance = fakePlatform;
//
//     expect(await usbManagerPlugin.getPlatformVersion(), '42');
//   });
// }
