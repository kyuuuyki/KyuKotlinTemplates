package dev.chayanon.kmp.bridging

import implementation.assembler.bridging.ViewControllerProtocol

interface NativeViewControllerRenderable {
    fun getViewController(): ViewControllerProtocol
}
