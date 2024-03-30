//
//  WelcomeView.swift
//  iosApp
//
//  swiftlint:disable one_declaration_per_file

import Shared
import SwiftUI

private let kSpacing: CGFloat = 12

struct WelcomeView: View {
	var viewController: WelcomeViewController
	@ObservedObject var viewModel: ViewModel
	
	var body: some View {
		if let viewModel = viewController.viewModel {
			VStack(spacing: kSpacing) {
				Text("WelcomeView")
				Text(viewModel.generatedString)
				
				Button { [weak viewController] in
					viewController?.didTapOnRandomButton()
				} label: {
					Text("Generate")
				}
				.buttonStyle(BorderedProminentButtonStyle())
				
				VStack(spacing: kSpacing) {
					Button { [weak viewController] in
						viewController?.didTapOnPresentButton()
					} label: {
						Text("Present")
					}
					.buttonStyle(BorderedProminentButtonStyle())
					Button { [weak viewController] in
						viewController?.didTapOnPushButton()
					} label: {
						Text("Push")
					}
					.buttonStyle(BorderedProminentButtonStyle())
					Button { [weak viewController] in
						viewController?.didTapOnPresentAndPushButton()
					} label: {
						Text("PresentAndPush")
					}
					.buttonStyle(BorderedProminentButtonStyle())
				}
			}
			.navigationTitle("Navigation Stack")
			.navigationBarTitleDisplayMode(.inline)
		} else {
			Text("Missing ViewModel")
		}
	}
	
	// MARK: VIEWMODEL
	class ViewModel: ObservableObject {
		@Published var generatedString: String
		
		init(generatedString: String) {
			self.generatedString = generatedString
		}
	}
}

// MARK: - VIEWMODEL
extension WelcomeView.ViewModel: WelcomeViewModelProtocol {
	static var preview: WelcomeView.ViewModel {
		.init(
			generatedString: "Generated Number: -"
		)
	}
}

// MARK: - PREVIEW
struct WelcomeView_Previews: PreviewProvider {
	static var previews: some View {
		let viewController = WelcomeViewController(interactor: nil, router: nil)
		viewController.viewModel = WelcomeView.ViewModel.preview
		return NavigationView {
			WelcomeView(
				viewController: viewController,
				viewModel: .preview
			)
		}
	}
}
