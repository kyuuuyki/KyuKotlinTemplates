package dev.chayanon.kmp.bridging

import androidx.fragment.app.FragmentManager
import dev.chayanon.kmp.MainActivity
import dev.chayanon.kmp.welcome.WelcomeFragment
import implementation.assembler.bridging.NativeComponentBridgingProtocol
import implementation.assembler.bridging.NavigationControllerProtocol
import implementation.assembler.bridging.ViewControllerProtocol
import implementation.assembler.bridging.ViewControllerRenderable
import implementation.assembler.bridging.WindowProtocol
import implementation.expectation.AppDependencyManager
import implementation.scenes.welcome.WelcomeViewController

class AppNativeComponentBridgingHandler : NativeComponentBridgingProtocol {
    lateinit var mainActivity: MainActivity
    lateinit var supportFragmentManager: FragmentManager
    var containerViewId: Int = 0

    override fun initializeNavigationController(rootViewController: ViewControllerProtocol): NavigationControllerProtocol? {
        val navigationController = AppNavigationFragment()
        navigationController.nativeRootViewController = rootViewController
        return navigationController
    }

    override fun <ViewModel> initializeViewController(viewController: ViewControllerRenderable<ViewModel>): ViewControllerProtocol? {
        when (viewController) {
            is WelcomeViewController -> {
                return WelcomeFragment(viewController)
            }
        }
        return null
    }

    override fun initializeWindow(): WindowProtocol {
        return AppWindowFragment()
    }
}

val AppDependencyManager.appBridgingHandler: AppNativeComponentBridgingHandler
    get() {
        return AppDependencyManager.shared.bridgingHandler as AppNativeComponentBridgingHandler
    }
