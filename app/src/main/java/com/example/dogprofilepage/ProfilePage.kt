package com.example.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePage() {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        ConstraintLayout {
            val (image, nameText, descText,rowStats,followButton,dmButton) = createRefs()

            val guideLine=createGuidelineFromTop(0.1f)
            Image(
                painter = painterResource(id = R.drawable.timmy),
                contentDescription = "Timmy",
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                    .border(width = 2.dp, color = Color.Magenta, shape = CircleShape)
                    .constrainAs(image) {
                        top.linkTo(guideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )
            Text(text = "Timmy", modifier = Modifier.constrainAs(nameText) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Text(text = "Cute Doggo", modifier = Modifier.constrainAs(descText) {
                top.linkTo(nameText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(rowStats){
                        top.linkTo(descText.bottom)
                    }
            ) {
                ProfileStats(count = "150", title = "Followers")
                ProfileStats(count = "100", title = "Following")
                ProfileStats(count = "30", title = "Posts")
            }

                Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(followButton){
                    top.linkTo(rowStats.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(dmButton.start)
                    width= Dimension.wrapContent
                }) {
                    Text(text = "Follow Doggo" )
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(dmButton){
                    top.linkTo(rowStats.bottom, margin = 16.dp)
                    end.linkTo(parent.end)
                    start.linkTo(followButton.end)
                    width= Dimension.wrapContent
                }) {
                    Text(text = "Direct Message")
            }
        }
    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}