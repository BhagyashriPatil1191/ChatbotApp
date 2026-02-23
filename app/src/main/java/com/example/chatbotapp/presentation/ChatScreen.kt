package com.example.chatbotapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.chatbotapp.domain.model.Message
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatbotapp.R
import com.example.chatbotapp.common.Constant.EMPTY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf(EMPTY) }
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.messages.lastIndex)
        }
    }

    Scaffold(
        topBar = { AppBar() },
        bottomBar = { UserChatBox(text, viewModel) }
    ) { paddingValues ->
        LazyColumn(
            userScrollEnabled = true,
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(R.dimen.dimen_8)),
            reverseLayout = false,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dimen_4))
        ) {
            items(uiState.messages) { message ->
                AutoResponseView(message)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.LightGray
        )
    )
}

@Composable
private fun UserChatBox(
    text: String,
    viewModel: ChatViewModel
) {
    var text by remember { mutableStateOf(text) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.dimen_8),
                end = dimensionResource(R.dimen.dimen_8),
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
                top = dimensionResource(R.dimen.dimen_16)
            )
    ) {
        TextField(
            placeholder = {
                Text(stringResource(R.string.tf_hint_label))
            },
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .imePadding(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (text.isNotBlank()) {
                        viewModel.sendMessage(text)
                        text = EMPTY
                        keyboardController?.hide()
                    }
                }
            )
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.dimen_8)))
        Button(
            onClick = {
                viewModel.sendMessage(text)
                text = EMPTY
            }) {
            Text(stringResource(R.string.btn_label_send))
        }
    }
}

@Composable
fun AutoResponseView(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = if (message.isUser)
            Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.dimen_8))
                .background(
                    color = if (message.isUser)
                        Color.LightGray else Color.Gray,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.dimen_12))
                )
                .padding(dimensionResource(R.dimen.dimen_12))
        ) {
            Text(
                text = message.text,
                color = if (message.isUser) {
                    Color.Black
                } else {
                    Color.White
                }
            )
        }
    }
}