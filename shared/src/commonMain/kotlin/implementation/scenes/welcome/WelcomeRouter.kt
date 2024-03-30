package implementation.scenes.welcome

import dev.chayanon.packages.kotlin.foundation.protocol.TransitionCoordinatorProtocol
import implementation.assembler.transitionCoordinator.NavigationType

interface WelcomeRouterProtocol {
    fun navigateToPresent()

    fun navigateToPush()

    fun navigateToPresentAndPush()
}

class WelcomeRouter(
    var transitionCoordinator: TransitionCoordinatorProtocol,
) : WelcomeRouterProtocol {
    override fun navigateToPresent() {
        transitionCoordinator.performNavigation(
            type =
                NavigationType.Present(
                    sceneName = "WelcomeScene",
                    parameters = emptyMap(),
                ),
            animated = true,
            completion = null,
        )
    }

    override fun navigateToPush() {
        transitionCoordinator.performNavigation(
            type =
                NavigationType.Push(
                    sceneName = "WelcomeScene",
                    parameters = emptyMap(),
                ),
            animated = true,
            completion = null,
        )
    }

    override fun navigateToPresentAndPush() {
        transitionCoordinator.performNavigation(
            type =
                NavigationType.PresentAndPush(
                    sceneName = "WelcomeScene",
                    parameters = emptyMap(),
                ),
            animated = true,
            completion = null,
        )
    }
}
