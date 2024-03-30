package dev.chayanon.packages.kotlin.foundation.protocol

import kotlin.reflect.KClass

interface ResolverProtocol {
    @Throws(Throwable::class)
    fun <Module : ModuleProtocol> resolve(
        moduleType: KClass<Module>,
        name: String? = null,
    ): Module
}
