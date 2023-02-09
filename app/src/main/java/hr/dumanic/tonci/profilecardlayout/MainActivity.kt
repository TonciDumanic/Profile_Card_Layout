package hr.dumanic.tonci.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import hr.dumanic.tonci.profilecardlayout.ui.theme.ProfileCardLayoutTheme
import hr.dumanic.tonci.profilecardlayout.ui.theme.lightGreen
@coil.annotation.ExperimentalCoilApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {

                MainScreen()
            }

        }
    }
}
@coil.annotation.ExperimentalCoilApi

@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {

    Scaffold(topBar = { AppBar() }) {  padding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            LazyColumn() {

                items(userProfiles) { userProfiles ->
                    ProfileCard(userProfile = userProfiles)
                }

            }

        }
    }

}
@Composable
fun AppBar() {
    TopAppBar (navigationIcon = { Icon(Icons.Default.Home, "content Description", Modifier.padding(12.dp)) },
        title = { Text("Messiging Application users")}    )
}
@coil.annotation.ExperimentalCoilApi

@Composable
fun ProfileCard(userProfile:UserProfile) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White) {

       Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Start ) {
           ProfilePicture(userProfile.pictureUrl,userProfile.status)
           ProfileContent(userProfile.name,userProfile.status)
       }
    }
}
@Composable
@coil.annotation.ExperimentalCoilApi
fun ProfilePicture(pictureUrl :String,onlineStatus : Boolean) {
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp,
            color = if(onlineStatus)
                        MaterialTheme.colors.lightGreen
                else
                    Color.Red ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {

        Image(

            painter =rememberImagePainter(pictureUrl),
            contentDescription = "Content description",
            Modifier.size(72.dp),
            contentScale = ContentScale.Crop)

    }}

@Composable
fun ProfileContent(userName: String,onlineStatus : Boolean) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()) {
        CompositionLocalProvider( LocalContentAlpha provides if(onlineStatus) ContentAlpha.medium else ContentAlpha.disabled

         )
        {

            Text(
                text = userName,
                style = MaterialTheme.typography.h5
            )
        }
        CompositionLocalProvider( LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if(onlineStatus)
                            "Active now"
                        else
                            "Offline",
                style = MaterialTheme.typography.body2

            )
        }


    }

}
@coil.annotation.ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileCardLayoutTheme {
        MainScreen()
    }
}