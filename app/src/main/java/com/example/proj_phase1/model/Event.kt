package com.example.proj_phase1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Event(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val date: Int,
    @StringRes val startTime: Int,
    @StringRes val endTime: Int,
    @StringRes val type: Int,
    @DrawableRes val coverImage: Int,
    val venue: Venue,
    val planner: List<Activity>,
    val guestList: List<Guest>
)