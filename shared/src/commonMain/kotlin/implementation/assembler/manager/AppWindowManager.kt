package implementation.assembler.manager

import implementation.assembler.bridging.WindowProtocol
import implementation.expectation.AppDependencyManager

class AppWindowManager {
    private var windows: MutableList<WindowProtocol> = mutableListOf()
    val presentingWindow: WindowProtocol? get() = windows.lastOrNull()

    fun configureRootWindow(window: WindowProtocol) {
        window.nativeMakeKeyAndVisible()
        windows = mutableListOf(window)
    }

    fun pushWindow() {
        val window = AppDependencyManager.shared.bridgingHandler?.initializeWindow() ?: return
        window.nativeMakeKeyAndVisible()
        windows.add(window)
    }

    fun popWindow() {
        windows.lastOrNull()?.nativeResignKey()
        if (windows.count() > 1) {
            windows.removeLast()
        }
    }
}
