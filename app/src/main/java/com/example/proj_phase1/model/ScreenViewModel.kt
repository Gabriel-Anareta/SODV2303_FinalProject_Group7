package com.example.proj_phase1.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.proj_phase1.data.sampledata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    // lists to use in functions that require no composable elements
    private var loginList: MutableList<LoginAsString> = mutableListOf()
    private var eventList: MutableList<EventAsString> = mutableListOf()
    private var venueList: MutableList<VenueAsString> = mutableListOf()

    @Composable
    fun ConvertLoginList(origin: List<Login>) {
        for (login in origin) {
            loginList.add(
                LoginAsString(
                    username = stringResource(id = login.username),
                    password = stringResource(id = login.password),
                    name = stringResource(id = login.name),
                    email = stringResource(id = login.email)
                )
            )
        }
    }

    @Composable
    fun ConvertEventList(origin: List<Event>) {
        for (event in origin) {
            eventList.add(
                EventAsString(
                    name = stringResource(id = event.name),
                    description = stringResource(id = event.description),
                    date = stringResource(id = event.date),
                    startTime = stringResource(id = event.startTime),
                    endTime = stringResource(id = event.endTime),
                    type = stringResource(id = event.type),
                    venue = SetEquivalentVenue(origin = event.venue),
                    planner = ConvertActivityList(origin = event.planner),
                    guestList = ConvertGuestList(origin = event.guestList)
                )
            )
        }
    }

    @Composable
    fun ConvertVenueList(origin: List<Venue>) {
        for (venue in origin) {
            venueList.add(
                VenueAsString(
                    name = stringResource(id = venue.name),
                    address = stringResource(id = venue.address),
                    description = stringResource(id = venue.description)
                )
            )
        }
    }

    @Composable
    fun SetEquivalentVenue(origin: Venue): VenueAsString {
        var venueIterator = venueList.iterator()
        var check = true
        var current: VenueAsString = VenueAsString("", "", "")

        while (check && venueIterator.hasNext()) {
            current = venueIterator.next()
            if (stringResource(id = origin.name) == current.name) {
                check = false
            }
        }

        return current
    }

    @Composable
    fun ConvertGuestList(origin: List<Guest>): MutableList<GuestAsString> {
        var listToString: MutableList<GuestAsString> = mutableListOf()

        for (guest in origin) {
            listToString.add(
                GuestAsString(
                    name = stringResource(id = guest.name),
                    accepted = guest.accepted
                )
            )
        }

        return listToString
    }

    @Composable
    fun ConvertActivityList(origin: List<Activity>): MutableList<ActivityAsString> {
        var listToString: MutableList<ActivityAsString> = mutableListOf()

        for (activity in origin) {
            listToString.add(
                ActivityAsString(
                    name = stringResource(id = activity.name),
                    description = stringResource(id = activity.description),
                    startTime = stringResource(id = activity.startTime)
                )
            )
        }

        return listToString
    }

    var usernameEntry by mutableStateOf("")
        private set

    var passwordEntry by mutableStateOf("")
        private set

    fun updateUsername(username: String) {
        usernameEntry = username
    }

    fun updatePassword(password: String) {
        passwordEntry = password
    }

    fun checkLogin() {
        val loginIterator = loginList.listIterator()
        var check = true
        var current: LoginAsString

        while (check && loginIterator.hasNext()) {
            current = loginIterator.next()
            if (usernameEntry == current.username && passwordEntry == current.password) {
                check = false
            }
        }

        _uiState.update { currentState ->
            currentState.copy(loginCheck = !check)
        }
    }

    fun logout() {
        updateUsername("")
        updatePassword("")
        _uiState.update { currentState ->
            currentState.copy(loginCheck = false)
        }
    }


    fun ShowSideBar() {
        _uiState.update { currentState ->
            currentState.copy(showingSideBar = true)
        }
    }

    fun HideSideBar() {
        _uiState.update { currentState ->
            currentState.copy(showingSideBar = false)
        }
    }


    fun updateToEvent(event: Event?) {
        _uiState.update { currentState ->
            currentState.copy(toEvent = event)
        }
    }


    var eventNameEntry by mutableStateOf("")
        private set

    var eventDateEntry by mutableStateOf("")
        private set

    var eventTimeEntry by mutableStateOf("")
        private set

    var eventTypeEntry by mutableStateOf("")
        private set

    fun updateEventName(name: String) {
        eventNameEntry = name
    }

    fun updateEventDate(date: String) {
        eventDateEntry = date
    }

    fun updateEventTime(time: String) {
        eventTimeEntry = time
    }

    fun updateEventType(type: String) {
        eventTypeEntry = type
    }

    fun updateToVenue(venue: Venue?) {
        _uiState.update { currentState ->
            currentState.copy(toVenue = venue)
        }
    }


    var searchEntry by mutableStateOf("")
        private set

    var searchLength by mutableIntStateOf(0)
        private set

    var searchList: MutableList<VenueAsString> = mutableListOf()
        private set

    fun updateSearch(search: String) {
        searchEntry = search
        searchLength = searchEntry.length

        searchList.clear()

        for (venue in venueList) {
            if (venue.name.take(searchLength).lowercase() == searchEntry.lowercase()) {
                searchList.add(venue)
            }
        }
    }
}


// data classes to use in functions that require no composable elements

data class LoginAsString(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
)

data class EventAsString(
    val name: String,
    val description: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val type: String,
    val venue: VenueAsString,
    val planner: List<ActivityAsString>,
    val guestList: List<GuestAsString>
)

data class VenueAsString(
    val name: String,
    val address: String,
    val description: String
)

data class GuestAsString(
    val name: String,
    val accepted: Boolean
)

data class ActivityAsString(
    val name: String,
    val description: String,
    val startTime: String
)