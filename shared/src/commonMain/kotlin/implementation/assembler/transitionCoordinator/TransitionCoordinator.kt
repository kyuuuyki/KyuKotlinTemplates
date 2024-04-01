package implementation.assembler.transitionCoordinator

import dev.chayanon.packages.kotlin.foundation.protocol.DefaultTransitionCoordinator
import dev.chayanon.packages.kotlin.foundation.protocol.ResolverProtocol
import implementation.assembler.Assembler
import implementation.assembler.bridging.NavigationControllerProtocol
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
        val presentingViewController = Assembler.shared.windowManager.presentingWindow?.nativePresentingViewController ?: return

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
                presentingViewController.nativePresent(
                    viewController = viewController,
                    animated = animated,
                    completion = completion,
                )
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
                presentingViewController.nativeNavigationController?.nativePushViewController(
                    viewController = viewController,
                    animated = animated,
                )
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
                    AppDependencyManager.shared.bridgingHandler?.initializeNavigationController(
                        rootViewController = viewController,
                    ) ?: return
                presentingViewController.nativePresent(
                    viewController = navigationController,
                    animated = animated,
                    completion = completion,
                )
            }
            NavigationType.Back -> {
                val dismissalCompletion: () -> Unit = {
                    if (Assembler.shared.windowManager.presentingWindow?.nativePresentingViewController is NavigationControllerProtocol) {
                        continueNavigation(
                            type = NavigationType.Back,
                            animated = false,
                            completion = completion,
                        )
                    } else {
                        completion?.invoke()
                    }
                }

//                if (presentingViewController == Assembler.shared.windowManager.presentingWindow?.nativeRootViewController) {
//                    Assembler.shared.windowManager.popWindow()
//                    completion?.invoke()
//                    return
//                }

                val navigationController = presentingViewController.nativeNavigationController
                val nativeViewControllers = navigationController?.nativeViewControllers
                if (nativeViewControllers != null) {
                    if (nativeViewControllers.count() > 1) {
                        navigationController.nativePopViewController(animated = animated)
                        dismissalCompletion()
                    } else {
                        navigationController.nativeDismiss(
                            animated = true,
                            completion = dismissalCompletion,
                        )
                    }
                } else {
                    presentingViewController.nativeDismiss(
                        animated = animated,
                        completion = dismissalCompletion,
                    )
                }
            }
            NavigationType.Reset -> TODO()
            is NavigationType.OpenUrl -> TODO()
            is NavigationType.OpenSystemBrowser -> TODO()
        }
    }
}
