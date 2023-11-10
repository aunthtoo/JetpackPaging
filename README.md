# Pagination with Jetpack Compose

## Description

This is a sample project implementing pagination in Jetpack Compose using Paging Compose library.

## Result App

You can download the result app [here](releaseApk/JetpackPagingApp.apk).
<br>
<div align="center">
  <img src="gif/jetpack%20paing.gif" alt="Popular Movie List App" width="300">
</div>

## Installation
To build the project in your environment, you need to replace my TMDB API key with yours in the `local.properties` file. Just insert `apiKey="your api key"` line inside that file. And follow the instructions below.<br>
- Firstly, clone the repository and navigate to the project folder using the `cd` command.
```bash
git clone https://github.com/aunthtoo/JetpackPaging.git
cd JetpackPaging
```
- Build and intall the debug app with the following command.
```bash
./gradlew installDebug 
```
That's all.
<br>

You can also read the Medium article about this project [here.](https://medium.com/@ankhtooaung.aha/infinite-listing-with-paging-in-jetpack-compose-2ac947240006)