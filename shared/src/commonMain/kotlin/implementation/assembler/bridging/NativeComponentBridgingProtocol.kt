package implementation.assembler.bridging

interface NativeComponentBridgingProtocol {
    fun initializeNavigationController(rootViewController: ViewControllerProtocol): NavigationControllerProtocol?

    fun <ViewModel> initializeViewController(viewController: ViewControllerRenderable<ViewModel>): ViewControllerProtocol?

    fun initializeWindow(): WindowProtocol
}
