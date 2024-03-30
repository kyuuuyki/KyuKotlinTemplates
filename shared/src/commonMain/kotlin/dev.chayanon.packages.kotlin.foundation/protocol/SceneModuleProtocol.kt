package dev.chayanon.packages.kotlin.foundation.protocol

import implementation.assembler.bridging.ViewControllerProtocol

interface SceneModuleProtocol : ModuleProtocol {
    @Throws(Throwable::class)
    fun build(
        resolver: ResolverProtocol,
        parameters: Map<String, Any> = emptyMap(),
    ): ViewControllerProtocol
}
