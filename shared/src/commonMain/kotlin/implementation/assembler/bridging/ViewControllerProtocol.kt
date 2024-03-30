package implementation.assembler.bridging

interface ViewControllerProtocol {
    val nativePresentingViewController: ViewControllerProtocol?
    val nativeNavigationController: NavigationControllerProtocol?

    fun nativePresent(
        viewController: ViewControllerProtocol,
        animated: Boolean,
        completion: (() -> Unit)?,
    )
}
