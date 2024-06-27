import retrofit2.Call
import retrofit2.http.GET

interface ApiServico {
    // Define o endpoint para obter a lista de músicas
    @GET("musicas")
    fun obterMusicas(): Call<List<Musica>>
}
