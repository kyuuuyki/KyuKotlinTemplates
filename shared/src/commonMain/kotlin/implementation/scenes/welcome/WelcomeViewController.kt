package implementation.scenes.welcome

import implementation.assembler.bridging.ViewControllerRenderable

interface WelcomeViewModelProtocol {
    var generatedString: String
}

interface WelcomeViewControllerProtocol {
    fun displayGetWelcomeDetail(viewModel: WelcomeSceneModel.GetWelcomeDetail.ViewModel)
}

class WelcomeViewController(
    var interactor: WelcomeInteractorProtocol? = null,
    var router: WelcomeRouterProtocol? = null,
) : WelcomeViewControllerProtocol, ViewControllerRenderable<WelcomeViewModelProtocol> {
    override var viewModel: WelcomeViewModelProtocol? = null

    fun onAppear() {
        getWelcomeDetail()
    }

    // ACTIONS
    fun didTapOnRandomButton() {
        getWelcomeDetail()
    }

    fun didTapOnPresentButton() {
        router?.navigateToPresent()
    }

    fun didTapOnPushButton() {
        router?.navigateToPush()
    }

    fun didTapOnPresentAndPushButton() {
        router?.navigateToPresentAndPush()
    }

    fun didTapOnBackButton() {
        router?.navigateToBack()
    }

    // INTERACTION: GET WELCOME DETAIL
    private fun getWelcomeDetail() {
        val request = WelcomeSceneModel.GetWelcomeDetail.Request()
        interactor?.getWelcomeDetail(request)
    }

    override fun displayGetWelcomeDetail(viewModel: WelcomeSceneModel.GetWelcomeDetail.ViewModel) {
        this.viewModel?.generatedString = viewModel.generatedString
    }
}
