package com.example.proj_phase1.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.proj_phase1.R
import com.example.proj_phase1.data.sampledata
import com.example.proj_phase1.model.BaseEventDisplay
import com.example.proj_phase1.model.Event
import com.example.proj_phase1.model.Venue
import com.example.proj_phase1.model.VenueAsString

@Composable
fun SearchScreen(
    search: String,
    onSearchChange: (String) -> Unit,
    venueList: List<Venue>,
    searchList: List<VenueAsString>,
    onSideBarClick: () -> Unit,
    onVenueClick: (Venue) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchedList: MutableList<Venue> = SetEquivalentVenues(
        originList = searchList,
        checkList = venueList
    )

    Scaffold(
        topBar = { SearchTopBar(
            onSideBarClick = onSideBarClick
        ) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.Padding_Medium))
            ) {
                SearchBar(
                    search = search,
                    onSearchChange = onSearchChange,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.Padding_Medium)))

                if (search == "") {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.Padding_Medium))
                    ) {
                        items(venueList) {
                            var venue = it
                            VenueDisplay(
                                venue = it,
                                onVenueClick = { onVenueClick(venue) }
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.Padding_Medium))
                    ) {
                        items(searchedList) {
                            var venue = it
                            VenueDisplay(
                                venue = it,
                                onVenueClick = { onVenueClick(venue) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    search: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = search,
        onValueChange = onSearchChange,
        placeholder = {
            Text(
                text = stringResource(R.string.Search_Input_Label),
                style = MaterialTheme.typography.labelMedium
            )
        },
        label = {
            Text(
                text = stringResource(R.string.Search_Input_Label),
                style = MaterialTheme.typography.labelMedium
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        modifier = Modifier
            .background(color = Color.Transparent)
            .padding(dimensionResource(id = R.dimen.Padding_Small))
    )
}

@Composable
fun SetEquivalentVenues(originList: List<VenueAsString>, checkList: List<Venue>): MutableList<Venue> {
    var outList: MutableList<Venue> = mutableListOf()
    var originIterator = originList.iterator()
    var checkIterator = checkList.iterator()
    var check: Boolean
    var currentVenueAsString = VenueAsString("", "", "")
    var currentVenue = Venue(0, 0, 0, 0)

    while (originIterator.hasNext()) {
        check = true
        currentVenueAsString = originIterator.next()
        while (check && checkIterator.hasNext()) {
            currentVenue = checkIterator.next()
            if (stringResource(id = currentVenue.name) == currentVenueAsString.name) {
                outList.add(currentVenue)
                check = false
            }
        }
    }

    return outList
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    onSideBarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.Search_Title),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = onSideBarClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.Expand_Menu_Desc)
                )
            }
        }
    )
}