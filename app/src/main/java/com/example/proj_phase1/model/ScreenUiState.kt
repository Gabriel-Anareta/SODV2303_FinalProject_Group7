package com.example.proj_phase1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ScreenUiState(
    val currentScreen: String? = null,
    val username: String? = null,
    val password: String? = null,
    val loginCheck: Boolean = false, // false indicates an invalid login
    val showingSideBar: Boolean = false,
    val toEvent: Event? = null,
    val eventName: String? = null,
    val eventDate: String? = null,
    val eventTime: String? = null,
    val eventType: String? = null,
    val toVenue: Venue? = null,
    val search: String? = null
)