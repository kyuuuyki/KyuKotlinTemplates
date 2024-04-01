package implementation.assembler.bridging

interface WindowProtocol {
    val nativePresentingViewController: ViewControllerProtocol?
    var nativeRootViewController: ViewControllerProtocol?

    fun nativeMakeKeyAndVisible()

    fun nativeResignKey()
}
