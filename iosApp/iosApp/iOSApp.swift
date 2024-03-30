//
//  iOSApp.swift
//  iosApp
//
//  swiftlint:disable type_name

import Shared
import SwiftUI

@main
struct iOSApp: App {
	init() {
		AppDependencyManager.companion.shared.componentBridging = AppNativeComponentBridgingHandler()
	}
	
	var body: some Scene {
		WindowGroup {
			ContentView()
				.onAppear {
					let rootWindow = UIApplication.shared.window
					Assembler.companion.shared.configure(window: rootWindow)
				}
		}
	}
}
