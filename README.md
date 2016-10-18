# Android-Universal-Image-Loader-Custom-Uri-Impl-and-Cache

This custom implementation of Universal Image Loader allows you to use your specific URI for local bitmap displaying and saving/getting  from disk cache.

### Remember official acceptable example URI's

``` java
"http://site.com/image.png" // from Web
"file:///mnt/sdcard/image.png" // from SD card
"file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
"content://media/external/images/media/13" // from content provider
"content://media/external/video/media/13" // from content provider (video thumbnail)
"assets://image.png" // from assets
"drawable://" + R.drawable.img // from drawables (non-9patch images)
```
### Now you can use your own uri by using these classes

## Downloads
####compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'

####You have to add UIL dependency in your app level build.gradle , and copy these helper classes to your project

