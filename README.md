A_distance_B

A simple Android application designed to calculate the distance between two geographical points using a device's GPS. The app has been extended to transmit tracking data to a remote server, laying the groundwork for a fully functional tracking service.

✨ Features

    Server Point Tracking (NEW): When Point A or B coordinates are recorded, the data is immediately sent to a remote API server for centralized storage and future route analysis.

    GPS Location Tracking: Gets real-time geographical coordinates for two distinct points (A and B).

    Distance Calculation: Accurately calculates the distance in meters between the two recorded points.

    Responsive UI: The layout is proportional and visually consistent across various Android screen sizes.

    Animated Elements: Interactive image buttons respond to touch with smooth scaling animation.

    Modular Codebase: The project is structured with a clear separation of concerns for easy maintenance and scaling.

🛠️ Technologies Used

    Language: Kotlin

    Location Services: Google Play Services Location (play-services-location)

    Networking: OkHttp (for performing asynchronous POST requests to the API)

    JSON Serialization: Gson (for converting data objects into JSON format)

    Audio: MediaPlayer

    Code Management: git (using a PAT for secure authentication)

📂 Project Structure (New Modules Added)

    MainActivity.kt: Handles UI logic, event listeners, and application flow coordination.

    LocationHelper.kt: A helper class dedicated to requesting GPS permissions and retrieving location data.

    GpsTrackerManager.kt: (NEW MODULE) Handles JSON formatting and sending data to the remote server.

    DataModels.kt & Utils.kt: (NEW FILES) Contain data models (data class) for network requests and helper functions for date/time formatting.

    ImageButtonAnimator.kt: Reusable class for handling button touch animations.

🚀 Getting Started

To run this project, clone the repository and open it in Android Studio.

Dependencies (build.gradle)

The project requires the following dependencies for location and network functionality:
Gradle

dependencies {
    // ...
    
    implementation 'com.google.android.gms:play-services-location:21.0.1'
    implementation 'com.squareup.okhttp3:okhttp:4.11.0' 
    implementation 'com.google.code.gson:gson:2.10.1' 
}

Permissions (AndroidManifest.xml)

Location and Internet access permissions are required::
    
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />


P.S. "Ось така ху.ня Малята! д.Панас"
 
