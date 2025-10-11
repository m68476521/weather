
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hoods.forecastly.utils.K

@Serializable
data class GeoLocationDto(
    @SerialName("generationtime_ms")
    val generationtimeMs: Double = 0.0,
    @SerialName("results")
    val results: List<Result> = listOf(),
)

fun GeoLocationDto.toDomain(): List<GeoLocation> =
    results.map {
        GeoLocation(
            id = it.id,
            name = it.name,
            countryCode = it.countryCode,
            flagUrl = K.flagUrl(it.countryCode),
            countryId = it.countryId,
            latitude = it.latitude,
            longitude = it.longitude,
            timeZone = it.timezone,
            elevation = it.elevation.toDouble(),
            countryName = it.country,
        )
    }
