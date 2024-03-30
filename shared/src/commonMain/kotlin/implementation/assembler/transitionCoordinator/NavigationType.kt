package implementation.assembler.transitionCoordinator

sealed class NavigationType {
    // Base Navigation
    data class Present(
        val sceneName: String,
        val parameters: Map<String, Any>,
    ) : NavigationType()

    data class Push(
        val sceneName: String,
        val parameters: Map<String, Any>,
    ) : NavigationType()

    data class PresentAndPush(
        val sceneName: String,
        val parameters: Map<String, Any>,
    ) : NavigationType()

    data object Back : NavigationType()

    data object Reset : NavigationType()

    data class OpenUrl(
        val url: String,
        val parameters: Map<String, Any>,
    ) : NavigationType()

    data class OpenSystemBrowser(
        val url: String,
    ) : NavigationType()
}
