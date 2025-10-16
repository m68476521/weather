import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.ComposeUIViewController
import com.m68476521.weather.App
import com.m68476521.weather.di.initKoin

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun mainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        },
    ) {
        val calculateScreenSize = calculateWindowSizeClass()
        App(calculateScreenSize.widthSizeClass)
    }
