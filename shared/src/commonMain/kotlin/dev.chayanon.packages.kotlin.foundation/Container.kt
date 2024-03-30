package dev.chayanon.packages.kotlin.foundation

import dev.chayanon.packages.kotlin.foundation.enum.ResolverError
import dev.chayanon.packages.kotlin.foundation.protocol.ContainerProtocol
import dev.chayanon.packages.kotlin.foundation.protocol.ModuleProtocol
import dev.chayanon.packages.kotlin.foundation.protocol.ResolverProtocol
import kotlin.reflect.KClass

class Container : ContainerProtocol {
    private var modules: MutableMap<String, ModuleProtocol> = mutableMapOf()

    override fun removeAll() {
        modules = mutableMapOf()
    }

    override fun <Module : ModuleProtocol> register(
        moduleType: KClass<Module>,
        name: String?,
        module: (resolver: ResolverProtocol) -> Module,
    ) {
//        val key =
//            ModuleKey(
//                moduleType = moduleType,
//                name = name,
//            ).hashCode()
//        val key = 1
        val key = "$moduleType:$name"
        modules[key] = module(this)
    }

    @Throws(Throwable::class)
    override fun <Module : ModuleProtocol> resolve(
        moduleType: KClass<Module>,
        name: String?,
    ): Module {
//        val key =
//            ModuleKey(
//                moduleType = moduleType,
//                name = name,
//            ).hashCode()
//        val key = 1
        val key = "$moduleType:$name"
        return modules[key] as? Module ?: throw ResolverError.ModuleNotFound
    }
}
