package implementation.scenes.welcome

import implementation.expectation.WeakReference
import kotlin.experimental.ExperimentalNativeApi

interface WelcomePresenterProtocol {
    fun presentGetWelcomeDetail(response: WelcomeSceneModel.GetWelcomeDetail.Response)
}

@OptIn(ExperimentalNativeApi::class)
class WelcomePresenter(
    var viewController: WeakReference<WelcomeViewControllerProtocol>? = null,
) : WelcomePresenterProtocol {
    override fun presentGetWelcomeDetail(response: WelcomeSceneModel.GetWelcomeDetail.Response) {
        val viewModel =
            WelcomeSceneModel.GetWelcomeDetail.ViewModel(
                generatedString = "Generated Number: ${response.randomNumber}",
            )
        viewController?.get()?.displayGetWelcomeDetail(viewModel)
    }
}
