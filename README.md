<p align="center">
    <a href="https://play.google.com/store/apps/details?id=com.rudrakaniya.mymeetings" target="_blank" rel="noopener noreferrer">
        <img src="https://i.imgur.com/exAW5CZ.png" alt="logo" width="110px" hight="110px"/>
    </a>
</p>

<p align="center">
    <img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" alt="license">
  <img src="https://travis-ci.com/dunwu/blog.svg?branch=master" alt="build">
</p>

<h1 align="center">MyMeetings</h1>

> The MyMeetings app helps manage all-important meetings in one place, chaos :)

To add a meeting copy to your meeting invitation message and paste it into the new meeting section of the app, MyMeetings will automatically detect the URL, meetingID, password, and date. And you can verify that information and save it. 
<br><br>
MyMeetings will give you two reminder Vai notifications, one before 15 minutes and the other at the exact time of the meeting. You can join the meeting just by clicking on notifications.
<br>
The app flawlessly supports Dark theme as well as Light theme, adapts according to the system default theme.
<br><br>

## Supported Platforms
<table>
  <tr>
    <td><img src="https://i.imgur.com/EUV0Eap.png" width=45 height=45></td>
    <td><img src="https://i.imgur.com/qwqPl7A.png" width=45 height=45></td>
    <td><img src="https://i.imgur.com/ses14t2.png" width=45 height=45 ></td>
    <td><img src="https://i.imgur.com/qXeiD9P.png" width=45 height=45></td>
  </tr>
</table>

<br>
Built with a bunch of things, but to name a few:

- [Room library is used to store meeting information locally (SQLite database)](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase)
- [Android AlarmManager is used to call a service to notify the user before a meeting](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase)
- [Regex is used to automatically detect information related to a meeting](https://developer.android.com/reference/java/util/regex/Pattern)
- [The project structure is designed with MVVM architectural pattern](https://www.journaldev.com/20292/android-mvvm-design-pattern)
- [Google's Material Theming is used to design the app to ensure that the app is easy to use and looks fancy at the same time](https://material.io/develop/android)


## Install 

Search `MyMeetings` on Google Play Store, or 
> [MyMeetings](https://play.google.com/store/apps/details?id=com.rudrakaniya.mymeetings) ↗


## Images 
<table>
  <tr>
    <td>Home Screen</td>
     <td>Meeting Info</td>
     <td>Paste Message</td>
    <td>Edit Meeting Info</td>
  </tr>
  <tr>
    <td><img src="https://i.imgur.com/OZfUQau.png" width=195 ></td>
    <td><img src="https://i.imgur.com/cxrYVRX.png" width=195 ></td>
    <td><img src="https://i.imgur.com/4ZbN7Zq.png" width=195 ></td>
    <td><img src="https://i.imgur.com/dDYmo3r.png" width=195 ></td>
  </tr>
 </table>

<p align="center">
    <img src="https://i.imgur.com/lntoTjO.jpg" alt="banner" width="85%" hight="85%">
</p>

Thanks for the resources,
- [Using AlarmManager like a pro](https://android.jlelse.eu/using-alarmmanager-like-a-pro-20f89f4ca720)
- [Android AlarmManager As Deep As Possible](https://proandroiddev.com/android-alarmmanager-as-deep-as-possible-909bd5b64792)
- [Android — Scheduling Alarms with Precise Delivery Time using AlarmManager
](https://medium.com/@igordias/android-scheduling-alarms-with-precise-delivery-time-using-alarmmanager-75c409f3bde0)


last updated on 26th of December, 2020 02:02 pm, 
