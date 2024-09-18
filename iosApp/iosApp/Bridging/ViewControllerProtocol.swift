//  swiftlint:disable:this file_name
//
//  ViewControllerProtocol.swift
//  iosApp
//

import Foundation
import Shared
import UIKit

extension UIViewController: ViewControllerProtocol {
	public func nativePresent(
		viewController: ViewControllerProtocol,
		animated: Bool,
		completion: (() -> Void)? = nil
	) {
		guard let viewController = viewController as? UIViewController else { return }
		present(viewController, animated: animated)
		completion?()
	}
	
	public var nativeNavigationController: NavigationControllerProtocol? {
		navigationController
	}
	
	public func nativeDismiss(animated: Bool, completion: (() -> Void)? = nil) {
		dismiss(animated: animated, completion: completion)
	}
}
