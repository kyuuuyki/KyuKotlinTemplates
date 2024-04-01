package implementation.assembler.bridging

interface NavigationControllerProtocol : ViewControllerProtocol {
    var nativeViewControllers: List<ViewControllerProtocol>
    var nativeRootViewController: ViewControllerProtocol?

    fun nativePushViewController(
        viewController: ViewControllerProtocol,
        animated: Boolean,
    )

    fun nativePopViewController(animated: Boolean)
}
