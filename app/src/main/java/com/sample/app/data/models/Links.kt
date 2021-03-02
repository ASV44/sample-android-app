package com.sample.app.data.models

import com.google.gson.annotations.SerializedName
import java.net.URL

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */
class Links(
    val webcast: URL,
    @SerializedName("youtube_id")
    val youtubeID: String,
    val article: URL?,
    val wikipedia: URL?
)