package com.murerwa.picnicinterview.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.murerwa.picnicinterview.R

@Composable
fun EmptyResultComponent(
    message: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_results),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(R.string.tag_empty_result_image))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .padding(10.dp)
                .testTag(stringResource(R.string.tag_empty_result_text)),
            text = message,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
        )
    }
}