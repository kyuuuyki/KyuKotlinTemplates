//
//  ContentView.swift
//  iosApp
//
//  swiftlint:disable one_declaration_per_file

import Shared
import SwiftUI

private let kSpacing: CGFloat = 16
private let kFontSize: CGFloat = 200

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
					showContent.toggle()
                }
            }

            if showContent {
                VStack(spacing: kSpacing) {
                    Image(systemName: "swift")
                        .font(.system(size: kFontSize))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
