package implementation.scenes.welcome

class WelcomeSceneModel {
    class GetWelcomeDetail {
        class Request

        class Response(
            val randomNumber: Int,
        )

        class ViewModel(
            val generatedString: String,
        )
    }
}
