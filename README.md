# Submission Documentation

## Overview of the Project

This app and its source code are submitted in fulfilment of the Picnic Mobile Developer Technical interview. It relies on the [Giphy API](https://developers.giphy.com/docs/api/) and contains a home screen with a search functionality and a random gif functionality as per the requirements.

The search functionality also allows users to click on any of the search results to view more information about the result.

It is built with the following technologies;

- Kotlin version 1.9.10
- Jetpack Compose version 2023.08.00

## Project Structure and General Architecture

The project uses the `Clean Architecture` in combination with the `MVVM Pattern`. The main module of the project contains two packages;

1. **Core**

   This package contains common or shared classes and utilities. It contains subpackages for the various app utilities organized by function.

2. **Features**

   This package contains packages for the various features in the app. In this case, only one feature(home) was created.

   Each feature package is further subdivided into three layers;

   - _Data Layer_ - This layer contains `data transfer objects`,`repository implementations`, `data sources`, and `models` that help in fetching and parsing data from external sources.
   - _Domain Layer_ - This layer contains the business logic of the app and has packages such as `repository contracts`, `entities` and `usecases`.
   - _Presentation Layer_ - This contains the `components`, `screens`, `events`, `states` and `viewmodels` that help in preparing and rendering the data received for output.

## High Level Decisions

1. **Compose vs XML**

   One of the decisions I had to make in this project was choosing the type of Front-end to use. Android offers two options, XML which is the older technology and Jetpack Compose, a newer declarative-style approach to UI development.

   XML offers a few advantages such as faster load speeds and better resource consumption but in the end, I chose Jetpack Compose for the following reasons;

   - Ease of development because it requires less files, code and time to develop Compose UIs.
   - Ease of testing as it is easy to test individual Compose views unlike XML.
   - Compose takes advantage of Kotlin features such as null safety which makes UI less prone to crashes and null pointer exceptions.

2. **KAPT vs KSP**

   Kotlin Annotation Processing Tool (KAPT) and Kotlin Symbol Processing(KSP) are annotation processors that allow you to use Java annotation processors with Kotlin code.

   While KAPT has broader library support as it is the older and more mature technology, I chose to go with KSP for the following reasons;

   - KSP outperforms KAPT in most scenarios and is up to 2x faster than KAPT which translates to faster build times.
   - KSP is custom made for Kotlin and understands Kotlin-specific features such as nullability better.
   - Google is phasing out KAPT and using KSP future proofs the project against future deprecations of KAPT.

3. **Local Caching vs No Caching**

   Caching is an important aspect of development and user experience and is encouraged in most scenarios. It also ensures the load on the server being called is minimal by reducing unnecessary API calls and fetching previous data from the cache instead.

   However, for this task, I decided not to use caching for the following reasons;

   - Most of the data fetched by the app is either time-sensitive or should be refreshed often(such as the random GIFs every 10 seconds).
   - The data being fetched is not consistent and relies on randomness (for the random GIFs) or a search query and thus should be updated as often as possible.

4. **Multi-module vs Single module approach**

   A multi-module approach to app development offers several advantages such as faster incremental build times, better scaling, code reusablity and improved maintenability of code.

   However, for this task, I decided to use a single-module approach, simply because of the small size of the app and the fact that scaling is not and is not expected to be a requirement for this app.

   I, however, organized the app in such a way that it can be easily converted into a multi-module app if such a need were to present itself.

5. **When to refresh the timer (on image load vs on data load)**

   One of the requirements of the project was to ensure that the GIFs in the home page are refreshed after every 10 seconds. I still, however, had to make a decision on _when_ to trigger the countdown. Two options presented themselves; once the image data had been fetched from the server or once the image itself has been loaded.

   In this case, I chose to go with the second option:- Once the image has been loaded so that the user has 10 seconds to view the fully-loaded image, instead of counting down and refreshing the image regardless of whether the user had seen it or not.

6. **Immediate vs Debounced Call on search**

   Another requirement was that images should be searched as the user is typing. However, this can be expensive on the user since it requires more bandwidth, processing power and data to make a call after every character.

   To improve this and still be within the required specifications, I choose a debounced approach, with a 500ms delay. Once the user types a character, the app schedules a call 500ms into the future. If another character is typed before the call can be made, the scheduled call is canceled and another call is scheduled from that time.

   This helps reduce the amount of calls made to the API which translates to less server-load and can potentially reduce costs, especially for rate-limited services, without affecting the user experience.

7. **Securing the API Key**

   To secure the API Key provided, I created a new properties file `apikeys.properties` and then add the API Key as shown below

   ```properties
   API_KEY="secret_key"
   ```

   I then checked this file into `.gitignore` so that it is not tracked by the version control.

   To reference this key, I added the following code to the app-level `build.gradle` file

   ```gradle
   android {
    defaultConfig {
        ...

        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val apiKey = properties.getProperty("API_KEY") ?: ""
        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )
    }
   }
   ```

   The key can then be referenced in the app using `BuildConfig.API_KEY` when making calls.

## Third-Party Dependencies Used

Some of the external dependencies used in this app include;

- **_Coil_** - Used for image loading and gif playing. It was chosen for its easy compatibility with Jetpack Compose as well as its ability to cache images and prevent unnecessary loads for previously loaded images.

- **_Dagger Hilt_** - Used in dependency management, which helps improve the testabilty of the code while at the same time reducing coupling and chaining of dependencies.

- **_Retrofit_** - This is a library built on top of OKHttp3 to make it easy to parse API calls. It helps centralize network calls and reduces the amount of time that could be sent in manually configuring calls with OkHttp3.

- **_OKhttp3_** - This library was added to help in functionality like caching and priming network calls, and injecting interceptors such as the logging interceptor used in this project to debug API tests.

- **_Paging3_** - The Paging3 was chosen to help in paging the results of the search feature in the app and provide an infinite list.

- **_Nhaarman Test Library_** - The Nhaarman libraries are test-only libraries that help in testing and verifying some aspects of the app such as if and how many a specific method was called.

## Worth Noting

- **Flaky UI Tests** - Some of the UI Acceptance Tests are flaky due to the unpredictable times taken to load data from the server. I have worked to proof them against these uncertainties but should one of them fail, a rerun should fix that.
- **Omitted Component Tests** - Very simple components such as the Loaders and the Gif Card have not been tested as I was able to test them as part of their containing components and in the acceptance tests.
