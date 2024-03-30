package dev.chayanon.packages.kotlin.foundation.protocol

import kotlin.reflect.KClass

interface ContainerProtocol : ResolverProtocol {
    fun removeAll()

    fun <Module : ModuleProtocol> register(
        moduleType: KClass<Module>,
        name: String? = null,
        module: (resolver: ResolverProtocol) -> Module,
    )
}
