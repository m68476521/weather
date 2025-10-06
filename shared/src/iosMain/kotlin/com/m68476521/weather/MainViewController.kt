
import androidx.compose.ui.window.ComposeUIViewController
import com.m68476521.weather.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
//    MyApplicationTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            GreetingView(Greeting().greet())
//        }
//    }
}
