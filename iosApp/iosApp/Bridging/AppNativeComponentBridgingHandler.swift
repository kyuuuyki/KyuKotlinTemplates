//
//  AppNativeComponentBridgingHandler.swift
//  iosApp
//

import Foundation
import Shared
import SwiftUI
import UIKit

class AppNativeComponentBridgingHandler: NativeComponentBridgingProtocol {
	class RootViewController: UIViewController {}
	
	func initializeNavigationController(
		rootViewController: ViewControllerProtocol
	) -> NavigationControllerProtocol? {
		guard let rootViewController = rootViewController as? UIViewController else { return nil }
		return UINavigationController(rootViewController: rootViewController)
	}
	
	func initializeViewController(
		viewController: ViewControllerRenderable
	) -> ViewControllerProtocol? {
		if let viewController = viewController as? NativeViewControllerRenderable {
			return viewController.getViewController()
		}
		return nil
	}
	
	func initializeWindow() -> WindowProtocol {
		let window = UIWindow(frame: UIScreen.main.bounds)
		//		window.windowScene = windows.first?.windowScene
		window.rootViewController = RootViewController()
		return window
	}
}
