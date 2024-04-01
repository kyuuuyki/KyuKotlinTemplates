package dev.chayanon.kmp

import App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentContainerView
import dev.chayanon.kmp.bridging.AppNativeComponentBridgingHandler
import dev.chayanon.kmp.bridging.AppWindowFragment
import implementation.assembler.Assembler
import implementation.expectation.AppDependencyManager

class MainActivity : AppCompatActivity() {
    var windows: MutableList<AppWindowFragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bridgingHandler = AppNativeComponentBridgingHandler()
        bridgingHandler.mainActivity = this
        bridgingHandler.supportFragmentManager = supportFragmentManager
        AppDependencyManager.shared.bridgingHandler = bridgingHandler

        setContent {
            AndroidView(
                factory = cb@{ context ->
                    val view = FragmentContainerView(context)
                    view.id = ViewCompat.generateViewId()
                    bridgingHandler.containerViewId = view.id

                    val rootWindow = AppWindowFragment()
                    Assembler.shared.configure(window = rootWindow)
                    return@cb view
                },
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
