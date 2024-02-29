# Mobile application – working title: “Landmark Remark”
Landmark Remark is an application that allows users to save notes based on location on a map. These notes can be displayed on a map where they are saved and viewed by the user who created the note as well as other users of the application.

# Applications include

- Build an appropriate interface for the application.
- Integration with architectural components: MVVM(Model-View-ViewModel)
- The library uses the Google Maps API
- Use Cloud Firestore to save data
- Results return location, enter notes and can search by notes.
# Technology selection
- Language: Kotlin is often chosen over Java for Android app development because it is modern, more secure, writes more concise code, is compatible with Java, and has official support from Google.
- Interface design: because there are few interface screens and simple interface requirements, XML should be used, which helps reduce development time and increase application stability.
- Use MVVM architecture because the architecture is flexible, easy to maintain and effective for Android application development, besides the application needs high flexibility in data management and user interface.
- Use google map api: oogle Maps API is deeply integrated into Google's ecosystem, including location services, search, and other services. This makes it easier to develop applications related to locations and maps. Google Maps API provides a variety of features such as map display, positioning, location search, navigation instructions , etc. This makes it possible for the app to take advantage of a wide range of functions to provide a better user experience.
- Firebase offers a wide range of SDKs and tools for easy integration into mobile apps, especially for the Android platform. Integrating Firebase Realtime Database into Android apps is a simple process that saves development time and effort.

# Approach and handle problems

1. View current location on map: Use the Fused Location Provider API to access and display the user's current location on a map. Because Android's Fused Location Provider API provides an optimized approach to accessing the device's current location. This API automatically selects the best location source available on the device, such as GPS, cellular, or Wi-Fi, to provide the most accurate location without consuming much power.
2. Save short notes at current location:
    - When a user chooses to save a note, get their current location from the Fused Location Provider.
    - Use Firebase Realtime Database to store notes along with location information (latitude and longitude).

3. View notes saved at a stored location on a map:
    - From Firebase Realtime Database, get the list of notes saved at the archived location.
    - Use the Google Maps API to mark these locations on the map.

4. See the location, text, and username information of notes other users have saved:
    - Same as above, from Firebase Realtime Database, get the list of other users' saved notes.
    - Display location information, text, and user name on the map.

5. Search notes based on contained text or user name:
    - Add search function on UI.
    - When a user enters text or a username, perform a search query against the Firebase Realtime Database.
    - Display search results on the map.
  
  ![cautrrucapi](https://github.com/hominhduc18/landmark-remark/assets/90144686/6a1573c5-80ed-4914-93e8-0b94aef5b3f6)
