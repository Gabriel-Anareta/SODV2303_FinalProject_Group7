package com.example.proj_phase1.model

import androidx.annotation.StringRes

data class Guest(
    @StringRes val name: Int,
    val accepted: Boolean
)
