package com.example.proj_phase1.data

import com.example.proj_phase1.R
import com.example.proj_phase1.model.Activity
import com.example.proj_phase1.model.Event
import com.example.proj_phase1.model.Guest
import com.example.proj_phase1.model.Login
import com.example.proj_phase1.model.Venue

object sampledata {
    val Events: List<Event> = listOf<Event> (
        Event(
            name = R.string.Event_Name_1,
            description = R.string.Event_Desc_1,
            date = R.string.Event_Date_1,
            startTime = R.string.Event_StartTime_1,
            endTime = R.string.Event_EndTime_1,
            type = R.string.Event_Type_1,
            coverImage = R.drawable.event_coverimage_1,
            venue = Venue(
                name = R.string.Venue_Name_1,
                address = R.string.Venue_Address_1,
                description = R.string.Venue_Desc_1,
                coverImage = R.drawable.venue_coverimage_1
            ),
            planner = listOf(
                Activity(
                    name = R.string.Event1_Activity1_Name,
                    description = R.string.Event1_Activity1_Desc,
                    startTime = R.string.Event1_Activity1_StartTime
                ),
                Activity(
                    name = R.string.Event1_Activity2_Name,
                    description = R.string.Event1_Activity2_Desc,
                    startTime = R.string.Event1_Activity2_StartTime
                ),
                Activity(
                    name = R.string.Event1_Activity3_Name,
                    description = R.string.Event1_Activity3_Desc,
                    startTime = R.string.Event1_Activity3_StartTime
                )
            ),
            guestList = listOf(
                Guest(
                    name = R.string.Name1,
                    accepted = true
                ),
                Guest(
                    name = R.string.Name2,
                    accepted = true
                ),
                Guest(
                    name = R.string.Name3,
                    accepted = false
                ),
            )
        )
    )

    val Venues: List<Venue> = listOf<Venue> (
        Venue(
            name = R.string.Venue_Name_1,
            address = R.string.Venue_Address_1,
            description = R.string.Venue_Desc_1,
            coverImage = R.drawable.venue_coverimage_1
        )
    )

    val Logins: List<Login> = listOf<Login> (
        Login(
            username = R.string.username1,
            password = R.string.password1,
            name = R.string.Name1,
            email = R.string.Email1
        ),
        Login(
            username = R.string.username2,
            password = R.string.password2,
            name = R.string.Name2,
            email = R.string.Email2
        ),
        Login(
            username = R.string.username3,
            password = R.string.password3,
            name = R.string.Name3,
            email = R.string.Email3
        )
    )
}