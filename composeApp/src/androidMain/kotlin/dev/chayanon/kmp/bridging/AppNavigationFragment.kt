package dev.chayanon.kmp.bridging

import implementation.assembler.bridging.NavigationControllerProtocol
import implementation.assembler.bridging.ViewControllerProtocol
import implementation.expectation.AppDependencyManager
import implementation.expectation.WeakReference

class AppNavigationFragment : AppFragment(), NavigationControllerProtocol {
    override var nativeViewControllers: List<ViewControllerProtocol> = emptyList()

    override var nativeRootViewController: ViewControllerProtocol?
        get() {
            return nativeViewControllers.firstOrNull()
        }
        set(rootFragment) {
            val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
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
            nativeViewControllers = mutableListOf()

            if (rootFragment != null) {
                nativePushViewController(
                    viewController = rootFragment,
                    animated = false,
                )
            }
        }

    override fun nativePushViewController(
        viewController: ViewControllerProtocol,
        animated: Boolean,
    ) {
        val viewController = viewController as? AppFragment ?: return
        viewController.navigationController = WeakReference(this)

        val nativeViewControllers = this.nativeViewControllers.toMutableList()
        nativeViewControllers.add(viewController)
        this.nativeViewControllers = nativeViewControllers.toList()

        val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(AppDependencyManager.shared.appBridgingHandler.containerViewId, viewController)
        fragmentTransaction.commit()
    }

    override fun nativePopViewController(animated: Boolean) {
        val viewController = nativeViewControllers.lastOrNull() as? AppFragment ?: return

        val nativeViewControllers = this.nativeViewControllers.toMutableList()
        nativeViewControllers.removeLast()
        this.nativeViewControllers = nativeViewControllers.toList()

        val fragmentManager = AppDependencyManager.shared.appBridgingHandler.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(viewController)
        fragmentTransaction.commit()
    }

    override fun nativeDismiss(
        animated: Boolean,
        completion: (() -> Unit)?,
    ) {
        nativeRootViewController = null
        super.nativeDismiss(animated, completion)
    }
}
