package dev.chayanon.packages.kotlin.foundation.protocol

import implementation.assembler.bridging.WindowProtocol

interface AssemblerProtocol {
    val container: ContainerProtocol
    val resolver: ResolverProtocol

    fun configure(window: WindowProtocol?)

    fun reconfigure()
}

abstract class DefaultAssembler : AssemblerProtocol {
    private var startingWindow: WindowProtocol? = null

    override val resolver: ResolverProtocol get() = container

    override fun configure(window: WindowProtocol?) {
        startingWindow = window
    }

    override fun reconfigure() {
        container.removeAll()
        configure(
            window = startingWindow,
        )
    }
}
