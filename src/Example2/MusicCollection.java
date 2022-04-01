package Example2;

import java.util.List;
import java.util.stream.Collectors;

class MusicCollection {
    public static void main(String[] args) {
        List<Song> songs = List.of(
                new Song("Nothing Else Matters", 386, "Metallica", Song.Genre.METAL),
                new Song("No One", 248, "Alicia Keys", Song.Genre.POP),
                new Song("Nothing Else Matters", 386, "Metallica", Song.Genre.METAL),
                new Song("Believer", 216, "Imagine Dragons", Song.Genre.ROCK),
                new Song("Fear of The Dark", 438, "Iron Maiden", Song.Genre.METAL),
                new Song("Enter Sandman", 346, "Metallica", Song.Genre.METAL),
                new Song("The Unforgiven", 348, "Metallica", Song.Genre.METAL),
                new Song("Girl on Fire", 404, "Alicia Keys", Song.Genre.POP)
        );

       timeOfGenreSongs(songs,"METAL");
       numberOfArtistSong(songs,"Metallica");
       songsWithoutGenre(songs,"METAL");
    }

    private static void songsWithoutGenre (List<Song> songs, String genre){
        System.out.println("Piosenki bez gatunku " + genre);
        songs.stream()
                .filter(s -> !checkGenre(s,genre))
                .distinct()
                .forEach(System.out::println);
    }

    private static void numberOfArtistSong (List<Song> songs, String artist){
        long count = songs.stream()
                .filter(s -> s.getArtist().equals(artist))
                .count();
        System.out.println("Ilość piosenek artysty " + artist + " to " + count);

    }

private static void timeOfGenreSongs (List<Song> list, String genre){
    int sum = list.stream()
            .filter(s -> checkGenre(s, genre))
            .mapToInt(Song::getLength)
            .sum();
    System.out.println("Łączna długość wszyskich piosenek gatunku " + genre + " to " + sum + " sekund");
}

private static boolean checkGenre(Song song, String genre){
    String name = song.getGenre().name();
    return name.equals(genre);
}

}
