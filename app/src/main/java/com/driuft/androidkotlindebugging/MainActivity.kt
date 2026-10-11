package com.driuft.androidkotlindebugging

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.driuft.androidkotlindebugging.adapter.DogTrackAdapter
import com.driuft.androidkotlindebugging.model.DogTrack
import okhttp3.Headers
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DogTrackAdapter
    private val dogTracks = mutableListOf<DogTrack>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.track_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DogTrackAdapter(dogTracks) { track ->
            val intent = Intent(this, TrackDetailActivity::class.java).apply {
                putExtra("EXTRA_DOG_TRACK", track)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        fetchDogTracks()
    }

    private fun fetchDogTracks() {
        val client = AsyncHttpClient()
        val dogApiUrl = "https://dog.ceo/api/breeds/image/random/8"

        client.get(dogApiUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                try {
                    val imageUrls = json.jsonObject.getJSONArray("message")
                    val sampleTitles = listOf(
                        "Deep Sleep Rain Sounds for Pups",
                        "Calming Golden Retriever Beats",
                        "Squeaky Toy Lofi Remix",
                        "Puppy Separation Anxiety Relief",
                        "Classical Piano for Good Boys",
                        "Thunderstorm White Noise for Dogs",
                        "Soothing Park Ambience",
                        "Soft Acoustic Guitar & Barks"
                    )
                    val sampleArtists = listOf(
                        "Pup Relaxation Symphony",
                        "Canine Frequency Lab",
                        "Woof Lofi Station",
                        "Paws & Peace",
                        "Bark Harmonic Ensemble",
                        "Zen Dog Project",
                        "K9 Sleep Association",
                        "Tail Wagger Audio"
                    )
                    val sampleDurations = listOf("3:45", "4:12", "2:58", "5:01", "3:30", "4:45", "3:10", "2:40")
                    val sampleAudioUrls = listOf(
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3"
                    )

                    dogTracks.clear()
                    for (i in 0 until imageUrls.length()) {
                        val imageUrl = imageUrls.getString(i)
                        dogTracks.add(
                            DogTrack(
                                id = "track_$i",
                                title = sampleTitles[i % sampleTitles.size],
                                artist = sampleArtists[i % sampleArtists.size],
                                duration = sampleDurations[i % sampleDurations.size],
                                imageUrl = imageUrl,
                                audioUrl = sampleAudioUrls[i % sampleAudioUrls.size]
                            )
                        )
                    }
                    adapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                // Fallback static tracks if network is unavailable
                loadFallbackTracks()
            }
        })
    }

    private fun loadFallbackTracks() {
        val fallbackImages = listOf(
            "https://images.dog.ceo/breeds/retriever-golden/n02099601_100.jpg",
            "https://images.dog.ceo/breeds/husky/n02110185_1469.jpg",
            "https://images.dog.ceo/breeds/pug/n02110958_13669.jpg",
            "https://images.dog.ceo/breeds/corgi-cardigan/n02113186_1130.jpg"
        )
        dogTracks.clear()
        dogTracks.add(DogTrack("1", "Deep Sleep Rain Sounds for Pups", "Pup Relaxation Symphony", "3:45", fallbackImages[0], "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"))
        dogTracks.add(DogTrack("2", "Calming Golden Retriever Beats", "Canine Frequency Lab", "4:12", fallbackImages[1], "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"))
        dogTracks.add(DogTrack("3", "Squeaky Toy Lofi Remix", "Woof Lofi Station", "2:58", fallbackImages[2], "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"))
        dogTracks.add(DogTrack("4", "Puppy Separation Anxiety Relief", "Paws & Peace", "5:01", fallbackImages[3], "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3"))
        adapter.notifyDataSetChanged()
    }
}
