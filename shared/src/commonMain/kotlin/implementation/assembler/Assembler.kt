package implementation.assembler

import dev.chayanon.packages.kotlin.foundation.Container
import dev.chayanon.packages.kotlin.foundation.protocol.ContainerProtocol
import dev.chayanon.packages.kotlin.foundation.protocol.DefaultAssembler
import dev.chayanon.packages.kotlin.foundation.protocol.SceneModuleProtocol
import dev.chayanon.packages.kotlin.foundation.protocol.TransitionCoordinatorProtocol
import implementation.assembler.bridging.WindowProtocol
import implementation.assembler.manager.AppWindowManager
import implementation.assembler.transitionCoordinator.TransitionCoordinator
import implementation.scenes.welcome.WelcomeSceneModule

class Assembler : DefaultAssembler() {
    companion object {
        val shared = Assembler()
    }

    // Variable
    val windowManager = AppWindowManager()
    override var container: ContainerProtocol = Container()

    // Configure
    override fun configure(window: WindowProtocol?) {
        container.register(
            moduleType = TransitionCoordinatorProtocol::class,
            name = TransitionCoordinator.shared.moduleName,
            module = {
                TransitionCoordinator.shared
            },
        )

        val welcomeSceneModule = WelcomeSceneModule()
        container.register(
            moduleType = SceneModuleProtocol::class,
            name = welcomeSceneModule.moduleName,
            module = {
                welcomeSceneModule
            },
        )

        // RootViewController
        window?.let {
            windowManager.configureRootWindow(it)
        }

        try {
            val rootSceneModule =
                resolver.resolve(
                    moduleType = SceneModuleProtocol::class,
                    name = welcomeSceneModule.moduleName,
                )
            val viewController =
                rootSceneModule.build(
                    resolver = resolver,
                )
            window?.nativeRootViewController = viewController
        } catch (e: Exception) {
            print(e.message)
        }
    }
}
