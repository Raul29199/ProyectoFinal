
import com.example.proyectofinal.R


data class Song(
    val name: String,
    val coverResourceId: Int,
    val cancion: Int,
    val duration: String
)

object BBDD {
    val ListaCanciones: List<Song> = listOf(
        Song("Cacho a cacho - Estopa", R.raw.estopa, R.raw.estopa, "02:34"),
        Song("Monster Trio 「 AMV 」", R.raw.monster, R.raw.monster, "014:10"),
        Song("Mordecai y los Rigbys - Party Tonight", R.raw.mordecai, R.raw.mordecai, "03:16"),
        Song("Minero ft Rubius", R.raw.minero, R.raw.minero, "3:53"),
        Song("Yo tengo un moco", R.raw.moco, R.raw.moco, "00:47"),
        Song("Me pico un mosquito", R.raw.mosquito, R.raw.mosquito, "02:18"),
        Song("Rayden - Solo los amantes sobreviven", R.raw.rayden, R.raw.rayden, "03:13"),
        Song(
            "Santa RM - Mucho Para Mí (Ft. Franco Escamilla)",
            R.raw.santa,
            R.raw.santa,
            "05:10"
        ),
        Song("Windows Error Remix", R.raw.windows, R.raw.windows, "03:21"),
        Song(
            "Arctic Monkeys & Lana del Rey - I Wanna be Yours, Summertime Sadness",
            R.raw.morir,
            R.raw.morir,
            "03:05"
        )
    )
}