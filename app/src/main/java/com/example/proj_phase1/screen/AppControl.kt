package com.example.proj_phase1.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proj_phase1.data.sampledata
import com.example.proj_phase1.model.ScreenViewModel

enum class AppControlScreen {
    Login,
    Home,
    AllEvents,
    CreateEvent,
    Event,
    Venue,
    Invitations,
    Search,
    Notifications,
    Account
}

@Composable
fun AppControl() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppControlScreen.valueOf(
        backStackEntry?.destination?.route ?: AppControlScreen.Login.name
    )

    val viewModel: ScreenViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    viewModel.ConvertLoginList(origin = sampledata.Logins)
    viewModel.ConvertEventList(origin = sampledata.Events)
    viewModel.ConvertVenueList(origin = sampledata.Venues)

    NavHost(
        navController = navController,
        startDestination = AppControlScreen.Login.name
    ) {
        composable(route = AppControlScreen.Login.name) {
            LoginScreen(
                username = viewModel.usernameEntry,
                password = viewModel.passwordEntry,
                onUsernameChange = { viewModel.updateUsername(it) },
                onPasswordChange = { viewModel.updatePassword(it) },
                onClick = { viewModel.checkLogin() }
            )

            if (uiState.loginCheck) {
                navController.navigate(AppControlScreen.Home.name)
            }
        }

        composable(route = AppControlScreen.Home.name) {
            HomeScreen(
                eventList = sampledata.Events,
                onSideBarClick = { viewModel.ShowSideBar() },
                onSearchClick = { navController.navigate(AppControlScreen.Search.name) },
                onNotificationsClick = { navController.navigate(AppControlScreen.Notifications.name) },
                onCreateEventClick = { navController.navigate(AppControlScreen.CreateEvent.name) },
                onEventClick = { viewModel.updateToEvent(it) }
            )

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = { viewModel.HideSideBar() },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }

            if (uiState.toEvent != null) {
                viewModel.HideSideBar()
                navController.navigate(AppControlScreen.Event.name)
            }
        }

        composable(route = AppControlScreen.AllEvents.name) {
            AllEventsScreen(
                eventList = sampledata.Events,
                onSideBarClick = { viewModel.ShowSideBar() },
                onEventClick = { viewModel.updateToEvent(it) }
            )

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = { viewModel.HideSideBar() },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }

            if (uiState.toEvent != null) {
                viewModel.HideSideBar()
                navController.navigate(AppControlScreen.Event.name)
            }
        }

        composable(route = AppControlScreen.CreateEvent.name) {
            CreateEventScreen(
                eventName = viewModel.eventNameEntry,
                eventDate = viewModel.eventDateEntry,
                eventTime = viewModel.eventTimeEntry,
                eventType = viewModel.eventTypeEntry,
                onNameChange = { viewModel.updateEventName(it) },
                onDateChange = { viewModel.updateEventDate(it) },
                onTimeChange = { viewModel.updateEventTime(it) },
                onTypeChange = { viewModel.updateEventType(it) },
                onVenueClick = { viewModel.updateToVenue(it) },
                onSideBarClick = { viewModel.ShowSideBar() }
            )

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = { viewModel.HideSideBar() },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }

            if (uiState.toVenue != null) {
                viewModel.HideSideBar()
                navController.navigate(AppControlScreen.Venue.name)
            }
        }

        composable(route = AppControlScreen.Event.name) {
            uiState.toEvent?.let { event ->
                EventScreen(
                    event = event,
                    onSideBarClick = { viewModel.ShowSideBar() },
                )
            }

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToEvent(null)
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }
        }

        composable(route = AppControlScreen.Venue.name) {
            uiState.toVenue?.let { venue ->
                VenueScreen(
                    venue = venue,
                    onSideBarClick = { viewModel.ShowSideBar() },
                )
            }

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        viewModel.updateToVenue(null)
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }
        }

        composable(route = AppControlScreen.Invitations.name) {
            InvitationsScreen(onSideBarClick = { viewModel.ShowSideBar() })

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = { viewModel.HideSideBar() },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }
        }

        composable(route = AppControlScreen.Search.name) {
            SearchScreen(
                search = viewModel.searchEntry,
                onSearchChange = { viewModel.updateSearch(it) },
                venueList = sampledata.Venues,
                searchList = viewModel.searchList,
                onSideBarClick = { viewModel.ShowSideBar() },
                onVenueClick = { viewModel.updateToVenue(it) }
            )

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = { viewModel.HideSideBar() },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }

            if (uiState.toVenue != null) {
                viewModel.HideSideBar()
                navController.navigate(AppControlScreen.Venue.name)
            }
        }

        composable(route = AppControlScreen.Notifications.name) {
            NotificationScreen(onSideBarClick = { viewModel.ShowSideBar() })

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = { viewModel.HideSideBar() },
                    onAccountClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Account.name) },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }
        }

        composable(route = AppControlScreen.Account.name) {
            AccountScreen(onSideBarClick = { viewModel.ShowSideBar() })

            if (uiState.showingSideBar) {
                NavSideBar(
                    onCloseClick = { viewModel.HideSideBar() },
                    onHomeClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Home.name) },
                    onYourEventsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.AllEvents.name) },
                    onCreateEventClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.CreateEvent.name) },
                    onInvitationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Invitations.name) },
                    onSearchClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Search.name) },
                    onNotificationsClick = {
                        viewModel.HideSideBar()
                        navController.navigate(AppControlScreen.Notifications.name) },
                    onAccountClick = { viewModel.HideSideBar() },
                    onLogoutClick = {
                        viewModel.HideSideBar()
                        viewModel.logout()
                        navController.navigate(AppControlScreen.Login.name) }
                )
            }
        }
    }
}