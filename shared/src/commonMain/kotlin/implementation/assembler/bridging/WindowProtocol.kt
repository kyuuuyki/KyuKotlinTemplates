package implementation.assembler.bridging

interface WindowProtocol {
    var nativeRootViewController: ViewControllerProtocol?

    fun nativeMakeKeyAndVisible()

    fun nativeResignKey()
}
