package implementation.scenes.welcome

interface WelcomeInteractorProtocol {
    fun getWelcomeDetail(request: WelcomeSceneModel.GetWelcomeDetail.Request)
}

class WelcomeInteractor(
    var presenter: WelcomePresenterProtocol? = null,
    var worker: WelcomeWorkerProtocol? = null,
) : WelcomeInteractorProtocol {
    override fun getWelcomeDetail(request: WelcomeSceneModel.GetWelcomeDetail.Request) {
        val response =
            WelcomeSceneModel.GetWelcomeDetail.Response(
                randomNumber = (1..100).random(),
            )
        presenter?.presentGetWelcomeDetail(response)
    }
}
