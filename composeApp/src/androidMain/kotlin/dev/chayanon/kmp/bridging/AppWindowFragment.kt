package dev.chayanon.kmp.bridging

import implementation.assembler.bridging.NavigationControllerProtocol
import implementation.assembler.bridging.ViewControllerProtocol
import implementation.assembler.bridging.WindowProtocol
import implementation.expectation.AppDependencyManager

typealias FragmentProtocol = ViewControllerProtocol

class AppWindowFragment() : AppFragment(), WindowProtocol {
    var nativeViewControllers: List<ViewControllerProtocol> = emptyList()

    override val nativePresentingViewController: ViewControllerProtocol?
        get() {
            return nativeViewControllers.lastOrNull().let {
                if (it is NavigationControllerProtocol) {
                    it.nativeViewControllers.lastOrNull()
                } else {
                    it
                }
            }
        }

    override var nativeRootViewController: FragmentProtocol?
        get() {
            return nativeViewControllers.firstOrNull()
        }
        set(rootFragment) {
            val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
            val fragment = rootFragment as? AppFragment ?: return

            nativeViewControllers
                .asReversed()
                .mapNotNull {
                    it as? AppFragment
                }
                .forEach { fragment ->
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.remove(fragment)
                    fragmentTransaction.commit()
                }
            nativeViewControllers = mutableListOf(fragment)

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(AppDependencyManager.shared.appBridgingHandler.containerViewId, fragment)
            fragmentTransaction.commit()
        }

    override fun nativeMakeKeyAndVisible() {
        AppDependencyManager.shared.appBridgingHandler.mainActivity.windows.add(this)

        val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(AppDependencyManager.shared.appBridgingHandler.containerViewId, this)
        fragmentTransaction.commit()
    }

    override fun nativeResignKey() {
        AppDependencyManager.shared.appBridgingHandler.mainActivity.windows.remove(this)

        val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(this)
        fragmentTransaction.commit()
    }
}
