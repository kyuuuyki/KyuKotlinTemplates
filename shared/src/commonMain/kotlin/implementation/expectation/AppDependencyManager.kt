package implementation.expectation

import implementation.assembler.bridging.NativeComponentBridgingProtocol

class AppDependencyManager {
    companion object {
        val shared = AppDependencyManager()
    }

    var bridgingHandler: NativeComponentBridgingProtocol? = null
}
