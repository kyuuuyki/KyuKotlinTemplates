package implementation.assembler.bridging

interface NavigationControllerProtocol : ViewControllerProtocol {
    var nativeRootViewController: ViewControllerProtocol?

    fun nativePushViewController(
        viewController: ViewControllerProtocol,
        animated: Boolean,
    )
}
