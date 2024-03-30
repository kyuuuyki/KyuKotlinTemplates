package dev.chayanon.packages.kotlin.foundation.enum

sealed class ResolverError : Throwable() {
    data object ModuleNotFound : ResolverError()

    data object RenderingError : ResolverError()
}
