package com.example.proj_phase1.model

import androidx.annotation.StringRes

data class Activity(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val startTime: Int
)
