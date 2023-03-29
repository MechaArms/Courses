package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopicGrid()
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),                  // define a grid of two cells
        verticalArrangement = Arrangement.spacedBy(8.dp),    // define the space between the objects vertically
        horizontalArrangement = Arrangement.spacedBy(8.dp),  // define the space between the objects horizontally
        modifier = modifier.padding(8.dp)                    // define the space of the lazy column
    ){
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(elevation = 4.dp) {    // shadow of the card is 4.dp
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,        // the image hasn't description
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp) // the image size
                        .aspectRatio(1f),        // Attempts to size the content to match a specified aspect ratio
                    contentScale = ContentScale.Crop  // Cut the image to match in the space
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.body2, // predefined font text
                    modifier = Modifier.padding(            // define the free space of text name title position
                        start = 16.dp,  // left
                        top = 16.dp,    // up
                        end = 16.dp,    // right
                        bottom = 8.dp   // down
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {  // define the row objects alignment
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,  // the icon hasn't description
                        modifier = Modifier
                            .padding(start = 16.dp) // define the free space of icon position
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.caption, // predefined font text
                        modifier = Modifier.padding(start = 8.dp) // define the free space of text position after the icon image
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    CoursesTheme {
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}