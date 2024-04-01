package dev.chayanon.kmp.bridging

import androidx.fragment.app.Fragment
import implementation.assembler.bridging.NavigationControllerProtocol
import implementation.assembler.bridging.ViewControllerProtocol
import implementation.expectation.AppDependencyManager
import implementation.expectation.WeakReference

open class AppFragment : Fragment(), ViewControllerProtocol {
    var navigationController: WeakReference<AppNavigationFragment>? = null

    override val nativeNavigationController: NavigationControllerProtocol?
        get() {
            return navigationController?.get()
        }

    override fun nativePresent(
        viewController: ViewControllerProtocol,
        animated: Boolean,
        completion: (() -> Unit)?,
    ) {
        val fragment = viewController as? AppFragment ?: return

        val window = AppDependencyManager.shared.appBridgingHandler.mainActivity.windows.lastOrNull()
        val nativeViewControllers = window?.nativeViewControllers?.toMutableList() ?: mutableListOf()
        nativeViewControllers.add(fragment)
        window?.nativeViewControllers = nativeViewControllers.toList()

        val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(AppDependencyManager.shared.appBridgingHandler.containerViewId, fragment)
        fragmentTransaction.commit()
    }

    override fun nativeDismiss(
        animated: Boolean,
        completion: (() -> Unit)?,
    ) {
        val window = AppDependencyManager.shared.appBridgingHandler.mainActivity.windows.lastOrNull()
        val nativeViewControllers = window?.nativeViewControllers?.toMutableList() ?: mutableListOf()
        nativeViewControllers.removeLast()
        window?.nativeViewControllers = nativeViewControllers.toList()

        val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(this)
        fragmentTransaction.commit()
    }
}
