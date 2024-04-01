//  swiftlint:disable:this file_name
//
//  NavigationControllerProtocol.swift
//  iosApp
//

import Foundation
import Shared
import UIKit

extension UINavigationController: NavigationControllerProtocol {
	public var nativeViewControllers: [ViewControllerProtocol] {
		get {
			viewControllers
		}
		set(nativeViewControllers) {
			if let viewControllers = nativeViewControllers as? [UIViewController] {
				self.viewControllers = viewControllers
			}
		}
	}
	
	public var nativeRootViewController: ViewControllerProtocol? {
		get {
			viewControllers.first
		}
		set(nativeRootViewController) {
			guard let viewController = nativeRootViewController as? UIViewController else { return }
			viewControllers = [viewController]
		}
	}
	
	public func nativePopViewController(animated: Bool) {
		popViewController(animated: animated)
	}
	
	public func nativePushViewController(
		viewController: ViewControllerProtocol,
		animated: Bool
	) {
		guard let viewController = viewController as? UIViewController else { return }
		pushViewController(viewController, animated: animated)
	}
}
