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

## License

    Copyright 2011-2015 Sergey Tarasevich

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
