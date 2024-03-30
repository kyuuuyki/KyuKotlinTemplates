package implementation.expectation

import implementation.assembler.bridging.NativeComponentBridgingProtocol

class AppDependencyManager {
    companion object {
        val shared = AppDependencyManager()
    }

    var componentBridging: NativeComponentBridgingProtocol? = null
}
