package com.aunkhtoo.jetpackpaging.feature.popularmovie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aunkhtoo.jetpackpaging.R
import com.aunkhtoo.jetpackpaging.extension.format
import com.aunkhtoo.jetpackpaging.extension.showShortToast
import com.aunkhtoo.jetpackpaging.model.Movie

/**
Created By Aunt Htoo Aung on 09/11/2023.
 **/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMovieScreen(viewModel: PopularMovieViewModel) {

  Scaffold(topBar = {

    TopAppBar(
      title = { Text(text = "Popular Movie List", color = Color.White) },
      colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xff4a4ae8))
    )

  }) { paddingValues ->

    Box(modifier = Modifier.padding(paddingValues)) {

      val moviePagingItems: LazyPagingItems<Movie> =
        viewModel.loadPopularViewModel().collectAsLazyPagingItems()

      LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {

        items(moviePagingItems.itemCount) { index ->
          val item = moviePagingItems[index]!!
          MovieItem(movie = item)
        }

        moviePagingItems.apply {
          when {
            loadState.refresh is LoadState.Loading -> {
              item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                LoadingIndicator()
              }
            }

            loadState.refresh is LoadState.Error -> {
              val error = moviePagingItems.loadState.refresh as LoadState.Error

              item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                ErrorMessageItem(
                  errorMessage = error.error.message ?: "Error!",
                  onClickRetry = {
                    retry()
                  })
              }
            }

            loadState.append is LoadState.Loading -> {
              item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                LoadingIndicator()
              }
            }

            loadState.append is LoadState.Error -> {
              val error = moviePagingItems.loadState.append as LoadState.Error
              item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                ErrorMessageItem(
                  errorMessage = error.error.message ?: "Error!",
                  onClickRetry = {
                    retry()
                  })
              }
            }
          }
        }


      }
    }

  }
}

@Composable
fun MovieItem(movie: Movie) {

  val context = LocalContext.current
  val interactionSource = remember { MutableInteractionSource() }

  Column(modifier = Modifier
    .clickable(
      interactionSource = interactionSource,
      indication = rememberRipple(bounded = true)
    ) {
      context.showShortToast(movie.title)
    }) {
    Card(
      shape = RoundedCornerShape(8.dp),
      elevation = CardDefaults.cardElevation(
        defaultElevation = 3.dp
      ),
      colors = CardDefaults.cardColors(
        containerColor = Color.White,
      )
    ) {

      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(movie.posterPath)
          .crossfade(true)
          .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        placeholder = painterResource(id = R.drawable.movie_placeholder)
      )

    }

    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = movie.title,
      fontSize = 16.sp,
      color = Color.Black,
      modifier = Modifier.fillMaxWidth(),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = movie.releaseDate.format(),
      fontSize = 16.sp,
      color = Color.Gray,
      modifier = Modifier.fillMaxWidth()
    )

  }
}

@Composable
fun ErrorMessageItem(
  modifier: Modifier = Modifier,
  errorMessage: String,
  onClickRetry: () -> Unit
) {

  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {

    Text(
      modifier = Modifier
        .weight(2f)
        .padding(4.dp),
      text = errorMessage,
      color = Color.Red,
      fontSize = 14.sp
    )

    Button(
      modifier = Modifier.weight(1f),
      onClick = { onClickRetry() },
      colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
    ) {
      Text(text = "Retry", modifier = Modifier.padding(4.dp), fontSize = 14.sp)
    }
  }

}

@Composable
fun LoadingIndicator() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CircularProgressIndicator(
      modifier = Modifier.size(30.dp),
      color = Color(0xff4a4ae8)
    )
  }
}