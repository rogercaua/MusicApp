package com.musicapp

import Musica
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var musicaAdapter: MusicaAdapter
    private lateinit var playerView: PlayerView
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewMusicas)
        playerView = findViewById(R.id.playerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        musicaAdapter = MusicaAdapter { musica -> tocarMusica(musica) }
        recyclerView.adapter = musicaAdapter

        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer

        buscarMusicas()
    }

    private fun buscarMusicas() {
        RetrofitInstancia.api.obterMusicas().enqueue(object : Callback<List<Musica>> {
            override fun onResponse(call: Call<List<Musica>>, response: Response<List<Musica>>) {
                if (response.isSuccessful) {
                    musicaAdapter.submitList(response.body())
                }
            }

            override fun onFailure(call: Call<List<Musica>>, t: Throwable) {
                // Tratar erro aqui
            }
        })
    }

    private fun tocarMusica(musica: Musica) {
        val mediaItem = MediaItem.fromUri(musica.arquivoAudio)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        // Aqui você pode implementar a lógica para exibir os acordes conforme o tempo da música
    }
}
