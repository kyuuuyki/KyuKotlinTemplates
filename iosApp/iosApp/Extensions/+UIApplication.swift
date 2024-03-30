//  swiftlint:disable:this file_name
//
//  +UIApplication.swift
//  iosApp
//

import UIKit

// MARK: - Application's rootViewController
public extension UIApplication {
	/// Get the top-most presenting `UIViewController`.
	///
	/// - Parameters:
	///   - controller: Controller to get it's top-most presenting ViewController.
	///
	/// - returns: A top-most presenting `UIViewController`.
	class func topViewController(
		controller: UIViewController? = UIApplication.shared.window?.rootViewController
	) -> UIViewController? {
		if let navigationController = controller as? UINavigationController {
			return topViewController(controller: navigationController.visibleViewController)
		}
		
		if let tabBarController = controller as? UITabBarController,
		   let selectedViewController = tabBarController.selectedViewController {
			return topViewController(controller: selectedViewController)
		}
		
		if let presentedViewController = controller?.presentedViewController {
			return topViewController(controller: presentedViewController)
		}
		
		return controller
	}
}

// MARK: - Application's window
public extension UIApplication {
	/// The app's currently connected scenes' keyWindow.
	var window: UIWindow? {
		UIApplication
			.shared
			.connectedScenes
			.flatMap { ($0 as? UIWindowScene)?.windows ?? [] }
			.first { $0.isKeyWindow }
	}
}
