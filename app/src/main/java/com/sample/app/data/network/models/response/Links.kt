package com.sample.app.data.network.models.response

import com.google.gson.annotations.SerializedName
import java.net.URL

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */
class Links(
    val patch: Patch,
    val flickr: Flickr,
    val webcast: URL,
    @SerializedName("youtube_id")
    val youtubeID: String,
    val article: URL?,
    val wikipedia: URL?
) {
    data class Patch(
        val small: URL?,
        val large: URL?
    )

    data class Flickr (
        val original: ArrayList<URL>
    )
}