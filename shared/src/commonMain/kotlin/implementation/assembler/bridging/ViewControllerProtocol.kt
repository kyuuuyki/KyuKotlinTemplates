package implementation.assembler.bridging

interface ViewControllerProtocol {
    val nativeNavigationController: NavigationControllerProtocol?

    fun nativePresent(
        viewController: ViewControllerProtocol,
        animated: Boolean,
        completion: (() -> Unit)?,
    )

    fun nativeDismiss(
        animated: Boolean,
        completion: (() -> Unit)?,
    )
}
