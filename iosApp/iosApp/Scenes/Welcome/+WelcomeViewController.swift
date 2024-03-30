//  swiftlint:disable:this file_name
//
//  +WelcomeViewController.swift
//  iosApp
//

import Foundation
import Shared
import SwiftUI

extension WelcomeViewController: NativeViewControllerRenderable {
	func getViewController() -> ViewControllerProtocol {
		let viewModel = WelcomeView.ViewModel(generatedString: "")
		self.viewModel = viewModel
		
		let view = WelcomeView(viewController: self, viewModel: viewModel)
		let hostingViewController = UIHostingController(rootView: view)
		hostingViewController.navigationItem.hidesBackButton = true
		return hostingViewController
	}
}
