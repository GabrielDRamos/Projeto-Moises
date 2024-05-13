package com.example.moisesapi.model

import com.google.gson.annotations.SerializedName


data class SendData(
    @SerializedName ("lyrics")
    var lyrics: String,
    var mood: String,
    var genre: String,
    var n_verses: Int,
    var remove_profanity: Boolean,
    var diversity: String,
    var group: String,
    var beta: String

)
