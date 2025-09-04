# A_distance_B
A simple Android application designed to calculate the distance between two geographical points using a device's GPS. The app features a clean user interface with animated buttons and background music.

Features

    GPS Location Tracking: Get real-time geographical coordinates for two distinct points (A and B).

    Distance Calculation: Accurately calculate the distance in meters between the two recorded points.

    Responsive UI: The layout is designed to be proportional and visually consistent across various Android screen sizes and densities.

    Animated Elements: Interactive image buttons that respond to touch with a smooth scaling animation.

    Background Audio: A subtle, looping audio track to enhance the user experience.

    Modular Codebase: The project is structured with separate classes for location services (LocationHelper.kt), button animations (ImageButtonAnimator.kt), and UI logic (MainActivity.kt) for easy maintenance and scalability.

Project Structure

    MainActivity.kt: Handles UI logic, event listeners, and coordinates the flow of the application.

    LocationHelper.kt: A helper class dedicated to requesting GPS permissions and retrieving location data.

    ImageButtonAnimator.kt: A reusable class for handling button touch animations.

    res/layout/activity_main.xml: The main layout file defining the app's user interface.

    res/anim/scale_animation.xml: XML file defining the scaling animation for the developer information image.

    res/raw/: Directory for storing audio assets (background_music.mp3, click_sound.mp3).

Getting Started

To run this project, clone the repository and open it in Android Studio. Ensure you have the Google Play Services location library added to your build.gradle file.
```
  dependencies {
      implementation 'com.google.android.gms:play-services-location:21.0.1'
  }
```
The app requires the following permissions, which must be declared in AndroidManifest.xml:
```XML

<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```
Author

https://github.com/VitalyRedB

 
