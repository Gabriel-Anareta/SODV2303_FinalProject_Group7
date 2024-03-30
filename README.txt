About this file
-----------------

There are 4 main files in com.example.proj_phase1

These are the data, model, screen, and ui.theme folders - really nothing different from what weve been doing in classwork

The "data" folder holds the sample data that is used for the project because we haven't gotten to the sql portion of the project yet

The "model" folder hold all of the base classes, Screen UI, Screen View Model, and some extra composables that are used frequently in the project

Please note that all of the files in the "model" folder are all complete and working at the moment. If you want to edit them to better fit the project, that is alright with me but also note that you will have to find all of the dependencies in all of the other files and change those. Most of all the logic for the app is done so you wont need to worry too much about that.

The "screen" folder is where all of the main composables are for the different pages of the app as well as the "AppControl.kt" file where the navController is.

The "ui.theme" folder just holds all of the material theming stuff that you can use for the styling of your composables. In all honesty, you are free to edit the material theming stuff if you want to - so long as you make sure it looks alright and it doesn't break the app :)



About the logins
-----------------

So when you first open the app, there is a login screen. You can find all the valid logins in the "data" folder or in the strings xml file in the "res" directory. I just kind of went with whatever came to mind so those were the logins I came up with for now lol.



About what still needs to be completed
---------------------------------------

Regarding what still needs to be done, there isn't all too much and hopefully it wont take you too much time since Ive done the layout stuff for you already.

For the pages, its still what we agreed on before and you'll just do 4 pages. Those will be the "Invitations", "Search", "Notifications", and "Account" pages. 

In the "AppControl.kt" file, you'll be able to find their composables in the navController. They are the last 4 composables in the NavHost composable scope. Their composables are all called in the same format of "[Page Name]Screen(onSideBarClick = { viewModel.ShowSideBar() })". So and example would be that the Invitations compossable is called as "InvitationsScreen(onSideBarClick = { viewModel.ShowSideBar() })". You can edit their input values to fit how you need them, like how the other composables have different input values, but I'll have to ask you to not edit the onSideBarClick value because that controls the side bar pop up.

For each of the pages, in their individual .kt files (ex. Invitation screen is in "InvitationsScreen.kt"), I have already put in some simple formating for the topBar so that you wouldn't have to do that for all of the pages.
You can begin editting in the Scaffold composable. Each of the pages that you need to do should have it so that you can start adding code on line 31 just after the ") { innerPadding ->" line.

For the sample data, if you have time, could you just populate it with a little bit more sample data? It doesn't have to be a lot, just enough so the app looks a little more complete



