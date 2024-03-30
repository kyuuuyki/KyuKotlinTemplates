package dev.chayanon.packages.kotlin.foundation.protocol

import implementation.assembler.bridging.ViewControllerProtocol

interface TransitionCoordinatorProtocol : ModuleProtocol {
    val resolver: ResolverProtocol

    fun performNavigation(
        type: Any,
        animated: Boolean,
        completion: (() -> Unit)?,
    )

    @Throws(Throwable::class)
    fun resolveScene(
        sceneName: String,
        parameters: Map<String, Any>,
    ): ViewControllerProtocol
}

abstract class DefaultTransitionCoordinator : TransitionCoordinatorProtocol {
    override fun resolveScene(
        sceneName: String,
        parameters: Map<String, Any>,
    ): ViewControllerProtocol {
        val sceneModule =
            resolver.resolve(
                moduleType = SceneModuleProtocol::class,
                name = sceneName,
            )
        return sceneModule.build(
            resolver = resolver,
            parameters = parameters,
        )
    }
}
