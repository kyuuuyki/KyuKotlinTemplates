package implementation.scenes.welcome

import dev.chayanon.packages.kotlin.foundation.enum.ResolverError
import dev.chayanon.packages.kotlin.foundation.protocol.ResolverProtocol
import dev.chayanon.packages.kotlin.foundation.protocol.SceneModuleProtocol
import dev.chayanon.packages.kotlin.foundation.protocol.TransitionCoordinatorProtocol
import implementation.assembler.bridging.ViewControllerProtocol
import implementation.expectation.AppDependencyManager
import implementation.expectation.WeakReference
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
class WelcomeSceneModule : SceneModuleProtocol {
    override val moduleName: String get() = "WelcomeScene"

    @Throws(Throwable::class)
    override fun build(
        resolver: ResolverProtocol,
        parameters: Map<String, Any>,
    ): ViewControllerProtocol {
        // Services
        val transitionCoordinator =
            resolver.resolve(
                moduleType = TransitionCoordinatorProtocol::class,
                name = "TransitionCoordinator",
            )

        // ViewController
        val viewController = WelcomeViewController()

        val worker = WelcomeWorker()
        val presenter =
            WelcomePresenter(
                viewController = WeakReference(viewController),
            )
        val interactor =
            WelcomeInteractor(
                presenter = presenter,
                worker = worker,
            )
        val router =
            WelcomeRouter(
                transitionCoordinator = transitionCoordinator,
            )
        viewController.interactor = interactor
        viewController.router = router

        return AppDependencyManager.shared.bridgingHandler?.initializeViewController(
            viewController = viewController,
        ) ?: throw ResolverError.RenderingError
    }
}
