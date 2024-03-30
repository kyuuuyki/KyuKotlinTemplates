package implementation.assembler.transitionCoordinator

import dev.chayanon.packages.kotlin.foundation.protocol.DefaultTransitionCoordinator
import dev.chayanon.packages.kotlin.foundation.protocol.ResolverProtocol
import implementation.assembler.Assembler
import implementation.expectation.AppDependencyManager

class TransitionCoordinator : DefaultTransitionCoordinator() {
    companion object {
        val shared = TransitionCoordinator()
    }

    override val moduleName: String get() = "TransitionCoordinator"
    override val resolver: ResolverProtocol get() {
        return Assembler.shared.resolver
    }

    override fun performNavigation(
        type: Any,
        animated: Boolean,
        completion: (() -> Unit)?,
    ) {
        val navigationType = type as? NavigationType ?: return
        continueNavigation(
            type = navigationType,
            animated = animated,
            completion = completion,
        )
    }

    private fun continueNavigation(
        type: NavigationType,
        animated: Boolean,
        completion: (() -> Unit)?,
    ) {
        val presentingWindow = Assembler.shared.windowManager.presentingWindow
        val rootViewController = presentingWindow?.nativeRootViewController
        val presentingViewController = rootViewController?.nativePresentingViewController

        when (type) {
            is NavigationType.Present -> {
                val viewController =
                    try {
                        resolveScene(
                            sceneName = type.sceneName,
                            parameters = type.parameters,
                        )
                    } catch (e: Exception) {
                        return
                    }
                if (presentingViewController != null) {
                    presentingViewController.nativePresent(
                        viewController = viewController,
                        animated = animated,
                        completion = completion,
                    )
                } else {
                    presentingWindow?.nativeRootViewController = viewController
                }
            }
            is NavigationType.Push -> {
                val viewController =
                    try {
                        resolveScene(
                            sceneName = type.sceneName,
                            parameters = type.parameters,
                        )
                    } catch (e: Exception) {
                        return
                    }
                if (presentingViewController != null) {
                    presentingViewController.nativeNavigationController?.nativePushViewController(
                        viewController = viewController,
                        animated = animated,
                    )
                } else {
                    presentingWindow?.nativeRootViewController = viewController
                }
            }
            is NavigationType.PresentAndPush -> {
                val viewController =
                    try {
                        resolveScene(
                            sceneName = type.sceneName,
                            parameters = type.parameters,
                        )
                    } catch (e: Exception) {
                        return
                    }
                val navigationController =
                    AppDependencyManager.shared.componentBridging?.initializeNavigationController(
                        rootViewController = viewController,
                    ) ?: return
                if (presentingViewController != null) {
                    presentingViewController.nativePresent(
                        viewController = navigationController,
                        animated = animated,
                        completion = completion,
                    )
                } else {
                    presentingWindow?.nativeRootViewController = navigationController
                }
            }
            NavigationType.Back -> TODO()
            NavigationType.Reset -> TODO()
            is NavigationType.OpenUrl -> TODO()
            is NavigationType.OpenSystemBrowser -> TODO()
        }
    }
}
