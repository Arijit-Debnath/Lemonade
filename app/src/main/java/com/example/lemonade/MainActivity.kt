package com.example.lemonade

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp(){
    var currentStep by remember { mutableStateOf(1)}
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(currentStep){
            1 ->{
                LemonAppWithImage(
                    textResourceId = R.string.lemon_tree,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionId =R.string.lemon_tree ,
                    onImageClick = { currentStep++ })
            }
            in 2..4->{
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()) {
                    Text(text = stringResource(id = R.string.squeeze), fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    val context=LocalContext.current
                    Image(painter = painterResource(id = R.drawable.lemon_squeeze),
                        contentDescription =stringResource(id = R.string.squeeze),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable { currentStep++
                        Toast.makeText(context,"CLick ${5-currentStep} times",Toast.LENGTH_SHORT).show()}
                        .border(
                            BorderStroke(2.dp, Color(105, 205, 216)),
                            shape = RoundedCornerShape(4.dp)
                        ))
                }
            }

            5->{
                LemonAppWithImage(
                    textResourceId = R.string.drink,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.drink,
                    onImageClick = { currentStep++ })

            }
            6->{
                LemonAppWithImage(
                    textResourceId = R.string.empty,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.empty,
                    onImageClick = { currentStep=1 })

            }

        }
    }
}

@Composable
fun LemonAppWithImage(textResourceId:Int,drawableResourceId:Int,contentDescriptionId:Int,
            onImageClick:()->Unit,modifier: Modifier=Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        Text(text = stringResource(id = textResourceId), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
//        val context=LocalContext.current
        Image(painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier= Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}