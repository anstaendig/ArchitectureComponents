package com.anstaendig.architecturecomponents.datasource

import com.squareup.moshi.Json
import java.util.*

/**
 * Summary data model for characters
 *
 * @param[resourceUri] The path to the individual character resource.
 * @param[name] The full name of the character.
 * @param[role] The role of the creator in the parent entity.
 */
data class CharacterSummaryData(
        @Json(name = "resourceURI") val resourceUri: String?,
        val name: String?,
        val role: String?
)

/**
 * Data model for characters
 *
 * @param[id] The unique ID of the character resource.
 * @param[name] The name of the character.
 * @param[description] A short bio or description of the character.
 * @param[modified] The date the resource was most recently modified.
 * @param[resourceUri] The canonical URL identifier for this resource.
 * @param[urls] A set of public web site URLs for the resource.
 * @param[thumbnail] The representative image for this character.
 * @param[comics] A resource list containing comics which feature this character.
 * @param[stories] A resource list of stories in which this character appears.
 * @param[events] A resource list of events in which this character appears.
 * @param[series] A resource list of series in which this character appears.
 */
data class CharacterData(
        val id: Int?,
        val name: String?,
        val description: String?,
        val modified: Date?,
        @Json(name = "resourceURI") val resourceUri: String,
        val urls: List<MarvelUrl>?,
        val thumbnail: MarvelImage?,
        val comics: MarvelResourceList<String>?,
        val stories: MarvelResourceList<String>?,
        val events: MarvelResourceList<String>?,
        val series: MarvelResourceList<String>?
)

/**
 * Typealias for a list of [CharacterSummaryData]
 */
typealias CharacterListData = MarvelResourceList<CharacterSummaryData>

/**
 * Summary data model for comics
 *
 * @param[resourceUri] The path to the individual comic resource.
 * @param[name] The canonical name of the comic.
 */
data class ComicSummaryData(
        @Json(name = "resourceURI") val resourceUri: String?,
        val name: String?
)

/**
 * Data model for comics
 *
 * @param[id]  The unique ID of the comic resource.
 * @param[digitalId] The ID of the digital comic representation of this comic. Will be 0 if the
 * comic is not available digitally.
 * @param[title] The canonical title of the comic.
 * @param[issueNumber] The number of the issue in the series (will generally be 0 for collection
 * formats).
 * @param[variantDescription] If the issue is a variant (e.g. an alternate cover, second printing,
 * or director’s cut), a text description of the variant.
 * @param[description] The preferred description of the comic.
 * @param[modified] The date the resource was most recently modified.
 * @param[isbn] The ISBN for the comic (generally only populated for collection formats).
 * @param[upc] The UPC barcode number for the comic (generally only populated for periodical
 * formats).
 * @param[diamondCode] The Diamond code for the comic.
 * @param[ean] The EAN barcode for the comic.
 * @param[issn] The ISSN barcode for the comic.
 * @param[format] The publication format of the comic e.g. comic, hardcover, trade paperback.
 * @param[pageCount] The number of story pages in the comic.
 * @param[textObjects] A set of descriptive text blurbs for the comic.
 * @param[resourceUri] The canonical URL identifier for this resource.
 * @param[urls] A set of public web site URLs for the resource.
 * @param[series] A summary representation of the series to which this comic belongs.
 * @param[variants] A list of variant issues for this comic (includes the "original" issue if the
 * current issue is a variant).
 * @param[collections] A list of collections which include this comic (will generally be empty if
 * the comic's format is a collection).
 * @param[collectedIssues] A list of issues collected in this comic (will generally be empty for
 * periodical formats such as "comic" or "magazine").
 * @param[dates] A list of key dates for this comic.
 * @param[prices] A list of prices for this comic.
 * @param[thumbnail] The representative image for this comic.
 * @param[images]  A list of promotional images associated with this comic.
 * @param[creators] A resource list containing the creators associated with this comic.
 * @param[characters] A resource list containing the characters which appear in this comic.
 * @param[stories] A resource list containing the stories which appear in this comic.
 * @param[events]  A resource list containing the events in which this comic appears.
 */
data class ComicData(
        val id: Int?,
        val digitalId: Int?,
        val title: String?,
        val issueNumber: Int?,
        val variantDescription: String?,
        val description: String?,
        val modified: Date?,
        val isbn: String?,
        val upc: String?,
        val diamondCode: String?,
        val ean: String?,
        val issn: String?,
        val format: String?,
        val pageCount: Int?,
        val textObjects: List<MarvelTextObject>?,
        @Json(name = "resourceURI") val resourceUri: String?,
        val urls: List<MarvelUrl>?,
        val series: SeriesSummaryData?,
        val variants: List<ComicSummaryData>?,
        val collections: List<ComicSummaryData>?,
        val collectedIssues: List<ComicSummaryData>?,
        val dates: List<ComicDateData>?,
        val prices: List<ComicPriceData>?,
        val thumbnail: MarvelImage?,
        val images: List<MarvelImage>?,
        val creators: CreatorListData?,
        val characters: CharacterListData?,
        val stories: StoryListData?,
        val events: EventListData?
)

/**
 * Typealias for a list of [ComicSummaryData]
 */
typealias ComicListData = MarvelResourceList<ComicSummaryData>

/**
 * Data model for date of comics.
 *
 * @param[type] A description of the date (e.g. onsale date, FOC date).
 * @param[date] The date.
 */
data class ComicDateData(
        val type: String?,
        val date: Date?
)

/**
 * Data model for price of comics.
 *
 * @param[type] A description of the price (e.g. print price, digital price).
 * @param[price] The price (all prices in USD).
 */
data class ComicPriceData(
        val type: String?,
        val price: Float?
)

/**
 * Summary data model for creators
 *
 * @param[resourceUri] The path to the individual creator resource.
 * @param[name] The full name of the creator.
 * @param[role] The role of the creator in the parent entity.
 */
data class CreatorSummaryData(
        @Json(name = "resourceURI") val resourceUri: String?,
        val name: String?,
        val role: String?
)

/**
 * Data model for creators
 *
 * @param[id] The unique ID of the creator resource.
 * @param[firstName] The first name of the creator.
 * @param[middleName] The middle name of the creator.
 * @param[lastName] The last name of the creator.
 * @param[suffix] The suffix or honorific for the creator.
 * @param[fullName] The full name of the creator (a space-separated concatenation of the above four
 * fields).
 * @param[modified] The date the resource was most recently modified.
 * @param[resourceUri] The canonical URL identifier for this resource.
 * @param[urls] A set of public web site URLs for the resource.
 * @param[thumbnail] The representative image for this creator.
 * @param[series] A resource list containing the series which feature work by this creator.
 * @param[stories] A resource list containing the stories which feature work by this creator.
 * @param[comics] A resource list containing the comics which feature work by this creator.
 * @param[events] A resource list containing the events which feature work by this creator.
 */
data class CreatorData(
        val id: Int?,
        val firstName: String?,
        val middleName: String?,
        val lastName: String?,
        val suffix: String?,
        val fullName: String?,
        val modified: Date?,
        @Json(name = "resourceURI") val resourceUri: String?,
        val urls: List<MarvelUrl>?,
        val thumbnail: MarvelImage?,
        val series: SeriesListData?,
        val stories: StoryListData?,
        val comics: ComicListData?,
        val events: EventListData?
)

/**
 * Typealias for a list of [CreatorSummaryData]
 */
typealias CreatorListData = MarvelResourceList<CreatorSummaryData>

/**
 * Summary data model for events
 *
 * @param[resourceUri] The path to the individual event resource.
 * @param[name] The name of the event.
 */
data class EventSummaryData(
        @Json(name = "resourceURI") val resourceUri: String?,
        val name: String?
)

/**
 * Data model for events
 *
 * @param[id] The unique ID of the event resource.
 * @param[title] The title of the event.
 * @param[description] A description of the event.
 * @param[resourceUri] The canonical URL identifier for this resource.
 * @param[urls] A set of public web site URLs for the event.
 * @param[modified] The date the resource was most recently modified.
 * @param[start] The date of publication of the first issue in this event.
 * @param[end] The date of publication of the last issue in this event.
 * @param[thumbnail] The representative image for this event.
 * @param[comics] A resource list containing the comics in this event.
 * @param[stories] A resource list containing the stories in this event.
 * @param[series] A resource list containing the series in this event.
 * @param[characters] A resource list containing the characters which appear in this event.
 * @param[creators] A resource list containing creators whose work appears in this event.
 * @param[next] A summary representation of the event which follows this event.
 * @param[previous] A summary representation of the event which preceded this event.
 */
data class EventData(
        val id: Int?,
        val title: String?,
        val description: String?,
        @Json(name = "resourceURI") val resourceUri: String?,
        val urls: List<MarvelUrl>?,
        val modified: Date?,
        val start: Date?,
        val end: Date?,
        val thumbnail: MarvelImage?,
        val comics: ComicListData?,
        val stories: StoryListData?,
        val series: SeriesListData?,
        val characters: CharacterListData?,
        val creators: CreatorListData?,
        val next: EventSummaryData?,
        val previous: EventSummaryData?
)

/**
 * Typealias for a list of [EventSummaryData]
 */
typealias EventListData = MarvelResourceList<EventSummaryData>

/**
 * Summary data model for series
 *
 * @param[resourceUri] The path to the individual series resource.
 * @param[name] The canonical name of the series.
 */
data class SeriesSummaryData(
        @Json(name = "resourceURI") val resourceUri: String?,
        val name: String?
)

/**
 * Data model for series
 *
 * @param[id] The unique ID of the series resource.
 * @param[title] The canonical title of the series.
 * @param[description] A description of the series.
 * @param[resourceUri] The canonical URL identifier for this resource.
 * @param[urls] A set of public web site URLs for the resource.
 * @param[startYear] The first year of publication for the series.
 * @param[endYear] The last year of publication for the series (conventionally, 2099 for ongoing
 * series).
 * @param[rating] The age-appropriateness rating for the series.
 * @param[modified] The date the resource was most recently modified.
 * @param[thumbnail] The representative image for this series.
 * @param[comics] A resource list containing comics in this series.
 * @param[stories] A resource list containing stories which occur in comics in this series.
 * @param[events] A resource list containing events which take place in comics in this series.
 * @param[characters] A resource list containing characters which appear in comics in this series.
 * @param[creators] A resource list of creators whose work appears in comics in this series.
 * @param[next] A summary representation of the series which follows this series.
 * @param[previous] A summary representation of the series which preceded this series.
 */
data class SeriesData(
        val id: Int?,
        val title: String?,
        val description: String?,
        @Json(name = "resourceURI") val resourceUri: String?,
        val urls: List<MarvelUrl>?,
        val startYear: Int?,
        val endYear: Int?,
        val rating: String?,
        val modified: Date?,
        val thumbnail: MarvelImage?,
        val comics: ComicListData?,
        val stories: StoryListData?,
        val events: EventListData?,
        val characters: CharacterListData?,
        val creators: CreatorListData?,
        val next: SeriesSummaryData,
        val previous: SeriesSummaryData
)

/**
 * Typealias for a list of [SeriesSummaryData]
 */
typealias SeriesListData = MarvelResourceList<SeriesSummaryData>

/**
 * Summary data model for stories
 *
 * @param[resourceUri] The path to the individual story resource.
 * @param[name] The canonical name of the story.
 * @param[type] The type of the story (interior or cover).
 */
data class StorySummaryData(
        @Json(name = "resourceURI") val resourceUri: String?,
        val name: String?,
        val type: String?
)

/**
 * Data model for stories
 *
 * @param[id] The unique ID of the story resource.
 * @param[title] The story title.
 * @param[description] A short description of the story.
 * @param[resourceUri] The canonical URL identifier for this resource.
 * @param[type] The story type e.g. interior story, cover, text story.
 * @param[modified] The date the resource was most recently modified.
 * @param[thumbnail] The representative image for this story.
 * @param[comics] A resource list containing comics in which this story takes place.
 * @param[series] A resource list containing series in which this story appears.
 * @param[events] A resource list of the events in which this story appears.
 * @param[characters] A resource list of characters which appear in this story.
 * @param[creators] A resource list of creators who worked on this story.
 * @param[originalIssue] A summary representation of the issue in which this story was originally
 * published.
 */
data class StoryData(
        val id: Int?,
        val title: String?,
        val description: String?,
        @Json(name = "resourceURI") val resourceUri: String?,
        val type: String?,
        val modified: Date?,
        val thumbnail: MarvelImage?,
        val comics: ComicListData?,
        val series: SeriesListData?,
        val events: EventListData?,
        val characters: CharacterListData?,
        val creators: CreatorListData?,
        val originalIssue: ComicSummaryData?
)

/**
 * Typealias for a list of [StorySummaryData]
 */
typealias StoryListData = MarvelResourceList<StorySummaryData>
