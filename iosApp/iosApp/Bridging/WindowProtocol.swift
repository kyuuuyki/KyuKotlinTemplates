//  swiftlint:disable:this file_name
//
//  WindowProtocol.swift
//  iosApp
//

import Foundation
import Shared
import UIKit

extension UIWindow: WindowProtocol {
	public func nativeMakeKeyAndVisible() {
		makeKeyAndVisible()
	}
	
	public func nativeResignKey() {
		resignKey()
	}
	
	public var nativeRootViewController: ViewControllerProtocol? {
		get {
			rootViewController
		}
		set(nativeRootViewController) {
			guard let viewController = nativeRootViewController as? UIViewController else { return }
			rootViewController = viewController
		}
	}
}
