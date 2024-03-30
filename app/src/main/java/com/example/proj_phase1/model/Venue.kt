package com.example.proj_phase1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.net.Inet4Address

data class Venue(
    @StringRes val name: Int,
    @StringRes val address: Int,
    @StringRes val description: Int,
    @DrawableRes val coverImage: Int
)
