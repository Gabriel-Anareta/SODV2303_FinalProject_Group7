package com.example.proj_phase1.model

import androidx.annotation.StringRes

data class Login(
    @StringRes val username: Int,
    @StringRes val password: Int,
    @StringRes val name: Int,
    @StringRes val email: Int,
)
