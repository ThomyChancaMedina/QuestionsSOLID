package com.architectcoders.grupo2verano2020.data.server

import android.os.Parcelable
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Questions(
    val id: String,
    val question: String,
    val answers: List<Answer>
): Parcelable

@Parcelize
data class Answer(
    val answer: String,
    val isCorrect: Boolean
) : Parcelable









