package com.example.watchlinkapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.watchlinkapp.Entities.Converters
import com.example.watchlinkapp.Entities.DAO.Genre.GenreDAO
import com.example.watchlinkapp.Entities.DAO.Movie.MovieDAO
import com.example.watchlinkapp.Entities.DAO.MovieGenre.MovieWithGenresDAO
import com.example.watchlinkapp.Entities.DAO.User.UserDAO
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.MovieGenre.MovieGenreCrossRef
import com.example.watchlinkapp.Entities.Model.User.User
import com.example.watchlinkapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Movie::class, Genre::class, MovieGenreCrossRef::class, User::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
    abstract fun movieWithGenresDao(): MovieWithGenresDAO
    abstract fun genreDao(): GenreDAO
    abstract fun userDao(): UserDAO

    companion object {
        private const val DB_NAME: String = "watchlink-db"
        val converters = Converters()
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private suspend fun populateDatabase(appContext: Context) {
            INSTANCE?.let { database ->
                val genre1 = Genre(1, "Action")
                val genre2 = Genre(2, "Comedy")
                val genre3 = Genre(3, "Drama")

                val genreDao: GenreDAO = database.genreDao()
                genreDao.insert(genre1)
                genreDao.insert(genre2)
                genreDao.insert(genre3)

                val user1 = User(1, "1", "1", "1", "1")

                val userDao: UserDAO = database.userDao()
                userDao.insert(user1)

                var image1 = converters.imageResourceToByteArray(appContext, R.drawable.image1)
                var image2 = converters.imageResourceToByteArray(appContext, R.drawable.image2)
                val movies = listOf(
                    Movie(
                        1,
                        "Movie A",
                        2023,
                        120.0,
                        4.5,
                        "Synopsis A",
                        "Director A",
                        image1,
                        "Phone A",
                        "emailA@example.com"
                    ),
                    Movie(
                        2,
                        "Movie B",
                        2024,
                        130.0,
                        4.2,
                        "Synopsis B",
                        "Director B",
                        image2,
                        "Phone B",
                        "emailB@example.com"
                    ),
                    // Добавьте еще фильмы по аналогии
                )

                val movieDao: MovieDAO = database.movieDao()
                movies.forEach { movie ->
                    movieDao.insert(movie)
                }

                val movieGenresDao: MovieWithGenresDAO = database.movieWithGenresDao()
                val moviesWithGenres = listOf(
                    MovieGenreCrossRef(movieId = 1, genreId = 1), // Movie A with Action
                    MovieGenreCrossRef(movieId = 1, genreId = 2), // Movie A with Comedy
                    // Add more for other movies and genres similarly
                )

                moviesWithGenres.forEach { movieGenresDao.insert(it) }
            }
        }

        fun getInstance(appContext: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                populateDatabase(appContext)
                            }
                        }
                    })
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}