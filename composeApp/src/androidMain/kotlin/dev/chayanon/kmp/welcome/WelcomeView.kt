package dev.chayanon.kmp.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import implementation.scenes.welcome.WelcomeViewController
import implementation.scenes.welcome.WelcomeViewModelProtocol
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun WelcomeView(
    viewController: WelcomeViewController,
    viewModel: WelcomeViewModel,
) {
    viewModel.liveDataUpdater.observeAsState().value ?: return

    MaterialTheme {
        Column(
            Modifier
                .background(Color.White),
        ) {
            Text("viewController: $viewController")
//            Text("viewModel: ${viewModel}")
//            Text("viewModel.liveData: ${viewModel.liveData}")
//            Text("viewModel.liveData.value: ${viewModel.liveData.value}")
            Text(viewModel.generatedString)
            Button(
                onClick = {
                    viewController.didTapOnRandomButton()
                },
                content = {
                    Text("Generate")
                },
            )
            Button(
                onClick = {
                    viewController.didTapOnPresentButton()
                },
                content = {
                    Text("Present")
                },
            )
            Button(
                onClick = {
                    viewController.didTapOnPushButton()
                },
                content = {
                    Text("Push")
                },
            )
            Button(
                onClick = {
                    viewController.didTapOnPresentAndPushButton()
                },
                content = {
                    Text("PresentAndPush")
                },
            )
            Button(
                onClick = {
                    viewController.didTapOnBackButton()
                },
                content = {
                    Text("Back")
                },
            )
        }
    }
}

class LiveDataUpdater : MutableLiveData<Boolean>(true) {
    fun update() {
        value = !value!!
    }
}

class WelcomeViewModel : WelcomeViewModelProtocol {
    override var generatedString: String = ""
        set(value) {
            field = value
            liveDataUpdater.update()
        }

    var liveDataUpdater = LiveDataUpdater()

    companion object {
        var preview = WelcomeViewModel()
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun WelcomeViewPreview() {
    val viewModel = WelcomeViewModel.preview
    val viewController = WelcomeViewController(interactor = null, router = null)
    viewController.viewModel = viewModel

    WelcomeView(
        viewController = viewController,
        viewModel = viewModel,
    )
}
