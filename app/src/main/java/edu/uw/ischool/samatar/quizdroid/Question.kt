package edu.uw.ischool.samatar.quizdroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
) : Parcelable

