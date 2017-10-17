package com.anstaendig.architecturecomponents.datasource

import com.squareup.moshi.Json

// Result structures
/**
 * Wrapper object for a successful call for [T]
 *
 * @param[code] The HTTP status code of the returned result.
 * @param[status] A String description of the call status.
 * @param[copyright] The copyright notice for the returned result.
 * @param[attributionText] The attribution notice for this result. Please display either this notice
 * or the contents of the attributionHTML field on all screens which contain data from the Marvel
 * Comics API.
 * @param[attributionHtml] An HTML representation of the attribution notice for this result. Please
 * display either this notice or the contents of the attributionText field on all screens which
 * contain data from the Marvel Comics API.
 * @param[data] The results returned by the call.
 * @param[etag] A digest value of the content returned by the call.
 */
data class MarvelResultWrapper<out T>(
        val code: Int?,
        val status: String?,
        val copyright: String?,
        val attributionText: String?,
        @Json(name = "attributionHTML") val attributionHtml: String?,
        val data: MarvelResultContainer<T>?,
        val etag: String?
)

/**
 * Container object for List of [T]
 *
 * @param[offset] The requested offset (number of skipped results) of the call.
 * @param[limit] The requested result limit.
 * @param[total] The total number of resources available given the current filter set.
 * @param[count] The total number of results returned by this call.
 * @param[results] The list of [T] returned by the call.
 */
data class MarvelResultContainer<out T>(
        val offset: Int?,
        val limit: Int?,
        val total: Int?,
        val count: Int?,
        val results: List<T>
)

/**
 * If a call to an API endpoint fails, the API will return an error object.
 *
 * @param[code] The http status code of the error.
 * @param[status] A description of the error.
 */
data class MarvelError(
        val code: Int,
        val status: String
)

// Common structures
/**
 * URLs are references to web pages or deep links into applications. (When present in a resultset,
 * it is preferred that you use these to link back to Marvel.) Many resources will have more than
 * one representation on the web so URLs are generally presented as an array of URL resources.
 *
 * @param[type] A text identifier for the URL.
 * @param[url] A full URL (including scheme, domain, and path).
 */
data class MarvelUrl(
        val type: String?,
        val url: String?
)

/**
 * Text objects are bits of descriptive text which are attached to an entity.
 *
 * @param[type] The canonical type of the text object (e.g. solicit text, preview text, etc.).
 * @param[language] The IETF language tag denoting the language the text object is written in.
 * @param[text] The text.
 */
data class MarvelTextObject(
        val type: String,
        val language: String,
        val text: String
)

/**
 * Resource lists are collections of summary views within the context of another entity type. For
 * example, the list of creators attached to a comic would be presented as resource list inside the
 * full representation of that comic.
 *
 * @param[available] The number of total available [T] in this list. Will always be greater
 * than or equal to the [returned] value.
 * @param[returned] The number of [T] returned in this collection (up to 20).
 * @param[collectionUri] The path to the full list of [T] in this collection.
 * @param[items] The list of returned [T] in this collection.
 */
data class MarvelResourceList<out T>(
        val available: Int?,
        val returned: Int?,
        @Json(name = "collectionURI") val collectionUri: String?,
        val items: List<T>?
)

/**
 * Images are represented as a partial path and an extension. See the guide to images for
 * information about how to construct full paths to image files:
 * https://developer.marvel.com/documentation/images
 *
 * @param[path] The directory path of to the image.
 * @param[extension] The file extension for the image.
 */
data class MarvelImage(
        val path: String?,
        val extension: String?
)
