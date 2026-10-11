# AND101 Project 3 - Woofify (Spotify for Dogs)

Submitted by: **Keoni Perez Labrador**

Time spent: **3** hours spent in total

## Summary

**Woofify (Spotify for Dogs)** is an Android app that features a curated Spotify-style music streaming experience tailored for dogs! Features include fetching live dog album artwork using the **Dog.ceo API** via **AsyncHttpClient**, loading artwork asynchronously with **Glide**, displaying tracks in a responsive **RecyclerView**, and streaming calming audio via **MediaPlayer** on a Now Playing detail screen.

If I had to describe this project in three (3) emojis, they would be: 🐕 🎵 ✨

## Application Features

The following REQUIRED features are completed:

- [x] 🐕 Display a list of items using a `RecyclerView` with a `LinearLayoutManager`
- [x] 🌐 Fetch data asynchronously from an API using `AsyncHttpClient`
- [x] 📦 Parse API data into custom Kotlin Data Classes (`DogTrack`)
- [x] 🎨 Load images asynchronously into list rows using `Glide`
- [x] 📱 Custom `CardView` row layout (`item_dog_track.xml`)

The following STRETCH features are implemented:

- [x] 🎵 Tap on any track to open a **Now Playing Detail Screen** (`TrackDetailActivity`)
- [x] ▶️ Live audio streaming and play/pause controls using Android's `MediaPlayer`
- [x] 📡 Offline fallback data handling for network resilience

## Video Demo

Here's a video demo of the app's implemented features:

[Loom Video Demo] https://www.loom.com/share/95ca9b79204e4d2ca9e55ac99501e401

## Notes

During this project, I learned and applied Android development concepts including RecyclerView Adapters, ViewHolder pattern, AsyncHttpClient networking, JSON parsing, Glide image caching, Intent data passing, and MediaPlayer audio streaming.

## License

Copyright 2026 **Keoni Perez Labrador**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
