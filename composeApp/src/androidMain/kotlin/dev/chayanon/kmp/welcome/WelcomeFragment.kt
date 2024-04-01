package dev.chayanon.kmp.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import dev.chayanon.kmp.bridging.AppFragment
import implementation.scenes.welcome.WelcomeViewController

class WelcomeFragment(
    val viewController: WelcomeViewController,
) : AppFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val viewModel =
            WelcomeViewModel()
        viewController.viewModel = viewModel

        return ComposeView(requireContext()).apply {
            setContent {
                WelcomeView(
                    viewController = viewController,
                    viewModel = viewModel,
                )
            }
        }
    }
}
