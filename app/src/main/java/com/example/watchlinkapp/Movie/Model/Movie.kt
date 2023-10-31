package com.example.watchlinkapp.Movie.Model

import com.example.watchlinkapp.R
import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val releaseYear: Int,
    val duration: Double,
    val rating: Double,
    val synopsis: String,
    val director: String,
    val imageResourceId: Int
) : Serializable

fun getMovies(): List<Movie> {
    return listOf(
        Movie(
            1,
            "Inception",
            "Sci-Fi",
            2010,
            2.28,
            8.8,
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            "Christopher Nolan",
            R.drawable.image1
        ),
        Movie(
            2,
            "The Shawshank Redemption",
            "Drama",
            1994,
            2.22,
            9.3,
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            "Frank Darabont",
            R.drawable.image2
        ),
        Movie(
            3,
            "The Godfather",
            "Crime",
            1972,
            2.58,
            9.2,
            "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
            "Francis Ford Coppola",
            R.drawable.image3
        ),
        Movie(
            4,
            "The Dark Knight",
            "Action",
            2008,
            2.32,
            9.0,
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            "Christopher Nolan",
            R.drawable.image4
        ),
        Movie(
            5,
            "Pulp Fiction",
            "Crime",
            1994,
            2.34,
            8.9,
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            "Quentin Tarantino",
            R.drawable.image5
        ),
        Movie(
            6,
            "Interstellar",
            "Sci-Fi",
            2014,
            2.49,
            8.6,
            "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
            "Christopher Nolan",
            R.drawable.image6
        ),
        Movie(
            7,
            "The Matrix",
            "Sci-Fi",
            1999,
            2.16,
            8.7,
            "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            "Lana Wachowski, Lilly Wachowski",
            R.drawable.image7
        ),
        Movie(
            8,
            "Gravity",
            "Sci-Fi",
            2013,
            1.91,
            7.7,
            "Two astronauts work together to survive after an accident leaves them stranded in space.",
            "Alfonso Cuarón",
            R.drawable.image8
        ),
        Movie(
            9,
            "Forrest Gump",
            "Drama",
            1994,
            2.22,
            8.8,
            "The presidencies of Kennedy and Johnson, the Vietnam War, and the Watergate scandal unfold through the perspective of an Alabama man with an IQ of 75.",
            "Robert Zemeckis",
            R.drawable.image9
        ),
        Movie(
            10,
            "The Green Mile",
            "Drama",
            1999,
            3.09,
            8.6,
            "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.",
            "Frank Darabont",
            R.drawable.image10
        ),
        Movie(
            11,
            "The Pursuit of Happyness",
            "Drama",
            2006,
            1.89,
            8.0,
            "A struggling salesman takes custody of his son as he's poised to begin a life-changing professional endeavor.",
            "Gabriele Muccino",
            R.drawable.image11
        ),
        Movie(
            12,
            "The Departed",
            "Crime",
            2006,
            2.31,
            8.5,
            "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.",
            "Martin Scorsese",
            R.drawable.image12
        ),
        Movie(
            13,
            "Goodfellas",
            "Crime",
            1990,
            2.25,
            8.7,
            "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.",
            "Martin Scorsese",
            R.drawable.image13
        ),
        Movie(
            14,
            "Die Hard",
            "Action",
            1988,
            2.12,
            8.2,
            "An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.",
            "John McTiernan",
            R.drawable.image14
        ),
        Movie(
            15,
            "Mad Max: Fury Road",
            "Action",
            2015,
            2.0,
            8.1,
            "In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search of her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.",
            "George Miller",
            R.drawable.image15
        ),
        Movie(
            16,
            "Reservoir Dogs",
            "Crime",
            1992,
            1.40,
            8.3,
            "When a simple jewelry heist goes horribly wrong, the surviving criminals begin to suspect that one of them is a police informant.",
            "Quentin Tarantino",
            R.drawable.image16
        ),
        Movie(
            17,
            "Django Unchained",
            "Crime",
            2012,
            2.45,
            8.4,
            "With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.",
            "Quentin Tarantino",
            R.drawable.image17
        )
    )
}
