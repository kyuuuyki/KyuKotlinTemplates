import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.chayanon.kmp.bridging.appBridgingHandler
import dev.chayanon.kmp.welcome.WelcomeFragment
import implementation.expectation.AppDependencyManager
import implementation.scenes.welcome.WelcomeViewController
import kyukotlintemplates.composeapp.generated.resources.Res
import kyukotlintemplates.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
            Button(
                onClick = {
                    val fragmentManager = AppDependencyManager.shared.appBridgingHandler!!.supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val viewController = WelcomeViewController(interactor = null, router = null)
                    val fragment = WelcomeFragment(viewController)
                    fragmentTransaction.add(fragment, null)
                    fragmentTransaction.commit()
                },
                content = {
                    Text("Show!")
                },
            )
        }
    }
}
