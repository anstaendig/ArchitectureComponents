package anstaendig.com.architecturecomponents.datasource

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.squareup.moshi.Json
import java.util.*

@Entity(tableName = "persons")
@TypeConverters(Converters::class)
data class PersonData(
    @PrimaryKey var id: String?,
    var name: String,
    @Json(name = "birth_year") var birthYear: String?,
    @Json(name = "eye_color") var eyeColor: String?,
    var gender: String?,
    @Json(name = "hair_color") var hairColor: String?,
    var height: String?,
    var mass: String?,
    @Json(name = "skin_color") var skinColor: String?,
    var homeworld: String?,
    var films: List<String>?,
    var species: List<String>?,
    var starships: List<String>?,
    var vehicles: List<String>?,
    var url: String?,
    var created: String?,
    var edited: String?
)

data class FilmData(
    val title: String,
    @Json(name = "episode_id") val episodeId: Int,
    @Json(name = "opening_crawl") val openingCrawl: String,
    val director: String,
    val producer: String,
    @Json(name = "release_date") val releaseDate: Date,
    val species: List<String>,
    val vehicles: List<String>,
    val characters: List<String>,
    val planets: List<String>,
    val url: String,
    val created: String,
    val edited: String
)

data class StarshipData(
    val name: String,
    val model: String,
    @Json(name = "starship_class") val starshipClass: String,
    val manufacturer: String,
    @Json(name = "cost_in_credits") val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    @Json(name = "max_atmosphering_speed") val maxAtmospheringSpeed: String,
    @Json(name = "hyperdrive_rating") val hyperdriveRating: String,
    @Json(name = "MGLT") val mglt: String,
    @Json(name = "cargo_capacity") val cargoCapacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String,
    val created: String,
    val edited: String
)

data class VehicleData(
    val name: String,
    val model: String,
    @Json(name = "vehicle_class") val vehicleClass: String,
    val manufacturer: String,
    val length: String,
    @Json(name = "cost_in_credits") val costInCredits: String,
    val crew: String,
    val passengers: String,
    @Json(name = "max_atmosphering_speed") val maxAtmospheringSpeed: String,
    @Json(name = "cargo_capacity") val cargoCapacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String,
    val created: String,
    val edited: String
)

data class SpeciesData(
    val name: String,
    val classification: String,
    val designation: String,
    @Json(name = "average_height") val averageHeight: String,
    @Json(name = "average_lifespan") val averageLifespan: String,
    @Json(name = "eye_colors") val eyeColors: String,
    @Json(name = "hair_colors") val hairColors: String,
    @Json(name = "skin_colors") val skinColors: String,
    val language: String,
    val homeworld: String,
    val people: List<String>,
    val films: List<String>,
    val url: String,
    val created: String,
    val edited: String
)

data class PlanetData(
    val name: String,
    val diameter: String,
    @Json(name = "rotation_period") val rotationPeriod: String,
    @Json(name = "orbital_period") val orbitalPeriod: String,
    val gravity: String,
    val population: String,
    val climate: String,
    val terrain: String,
    @Json(name = "surface_water") val surfaceWater: String,
    val residents: List<String>,
    val films: List<String>,
    val url: String,
    val created: String,
    val edited: String
)

data class PageData<out T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)
