
/**
 * @author (Name: Muhammad Qasim ID: c3360527)
 * @version (28/04/2023)
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class SongCollection {

    Album[] albums = new Album[4]; // Album arrays
    int albumSize = 0; // initial size of album

    Scanner input = new Scanner(System.in); // user input
    String name = "";
    String gName;
    int dName;

    public void readExternalFile(String fileName) {
        try {
            Scanner fileReader = new Scanner(new FileInputStream(fileName));
            Album album = null;
            int index = 0;
            Song song = null;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                } else if (line.trim().startsWith("Album")) {
                    String[] parts = line.trim().split("\\s");
                    for (int i = 0; i < albums.length; i++) {
                        if (albums[i] == null) {
                            albums[i] = new Album(line.replace(parts[0] + " ", ""));
                            albumSize++;
                            index = i;
                            break;
                        }
                    }
                } else if (line.trim().startsWith("Songs")) {
                    continue;
                }
                if (line.trim().startsWith("Name")) {
                    String[] parts = line.trim().split("\\s");
                    song = new Song(line.replace(parts[0] + " ", ""));
                } else if (line.trim().startsWith("Artist")) {
                    String[] parts = line.trim().split("\\s");
                    song.setArtist(line.replace(parts[0] + " ", ""));
                } else if (line.trim().startsWith("Duration")) {
                    String[] parts = line.trim().split("\\s");
                    song.setDuration(Integer.parseInt(line.replace(parts[0] + " ", "")));
                } else if (line.trim().startsWith("Genre")) {
                    String[] parts = line.trim().split("\\s");
                    song.setGenre(line.replace(parts[0] + " ", ""));
                }
                Song[] songs = albums[index].getSongs();
                for (int i = 0; i < songs.length; i++) {
                    if (songs[i] == null) {
                        albums[index].setSong(i, song);
                        break;
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found.\n");
        }
    }

    // menu
    int selection() {
        String checktype;
        System.out.println();
        System.out.println();
        System.out.println("*************************************");
        System.out.println("1. Read an external file ");
        System.out.println("2. Add Album");
        System.out.println("3. Add Song");
        System.out.println("4. Delete Album");
        System.out.println("5. Delete Song");
        System.out.println("6. list of all songs");
        System.out.println("7. list of Albums");
        System.out.println("8. list of songs with genre");
        System.out.println("9. Songs with specific duration in seconds ");
        System.out.println("10. Exit");
        System.out.print("Make your choice:");
        checktype = input.next();
        while (check(checktype)) {
            System.out.print("Enter numbers only:");
            checktype = input.next();
        }
        return Integer.parseInt(checktype);
    }

    int cont() {
        String check2;
        System.out.println("Do you wish to continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        check2 = input.next();
        while (check2(check2)) {
            System.out.print("invalid input.!" + "kindly enter 1 for Yes or 2 for No");
            check2 = input.next();
        }

        return Integer.parseInt(check2);
    }

    // adding album
    public void add_album() {
        System.out.print("Enter the name of the Album:");
        input.nextLine();
        name = input.nextLine().toLowerCase();
        boolean check = true;
        if (albumSize <= 3) {
            for (int i = 0; i < albumSize; i++) {
                if (albums[i].getName().equals(name)) {
                    System.out.println("album already exist.");
                    check = false;
                }
            }
        } else {
            System.out.println("Album is full.");
            return;
        }
        if (check) {

            albums[albumSize++] = new Album(name);
            System.out.println("Album is added successfully");
        }
    }

    // checking song before user enter new song
    public void check_song() {
        if (albumSize == 0) {
            System.out.println("No albums found. Please create an album first.");
            return;
        }

        if (albumSize != 0) {
            System.out.print("enter the name of the song: ");
            input.nextLine();
            String s_Name = input.nextLine();
            boolean check = true;
            for (int i = 0; i < albumSize; i++) {
                if (albums[i] != null) {
                    Song[] songs = albums[i].getSongs();
                    for (int j = 0; j < songs.length; j++) {
                        if (albums[i].getSong(j) != null) {
                            if (albums[i].getSong(j).getName().equals(s_Name)) {
                                // System.out.println("Song already exist.!!");
                                System.out.println("Album name: " + albums[i].getName() + ", ["
                                        + albums[i].getSong(j).toString() + "]");
                                check = true;

                            }
                        }
                    }
                }
            }
            if (check) {
                int cont = 0;
                while (cont != 3) {
                    cont = cont();
                    if (cont == 1) {
                        add_song();
                        break;
                    } else if (cont == 2) {
                        selection();
                        break;
                    }

                }
            }
        }
    }

    // add song
    public void add_song() {

        System.out.print("Enter the Album name: ");
        input.nextLine();
        String aName = input.nextLine().toLowerCase();

        System.out.print("Enter Song name: ");
        String sName = input.nextLine().toLowerCase();

        System.out.print("Enter Artist name: ");
        String artistName = input.nextLine().toLowerCase();

        System.out.print("Enter Song duration: ");
        String check = input.next();
        while (check(check)) {
            System.out.print("Enter numbers only:");
            check = input.next();
        }
        dName = Integer.parseInt(check);

        input.nextLine();

        System.out.print("Enter Genre name: ");
        String checkGenre = input.nextLine().toLowerCase();
        while (!isValidGenre(checkGenre)) {
            System.out.println("Invalid genre.!");
            System.out.print("Kindly enter pop, rock, hip-hop or bossa nova:");
            checkGenre = input.nextLine();
        }

        gName = checkGenre;
        for (int i = 0; i < albumSize; i++) {
            if (!albums[i].getName().equals(aName)) {
                System.out.println("Album doesn't exist.");
            } else if (albums[i].getName().equals(aName)) {
                if (albums[i].getTotalTime() + dName > 60 * albums[i].getMAX_TIME()) {
                    System.out.print("Duration exceeds the max limit of Album..!");
                    return;
                }
                if (albums[i].getSongs()[0] == null) {
                    Song newSong = new Song(sName);
                    newSong.setArtist(artistName);
                    newSong.setDuration(dName);
                    newSong.setGenre(gName);
                    albums[i].setSong(0, newSong);
                    System.out.println("Song added sucessfully.");

                } else if (albums[i].getSong(0).getName().equals(sName)
                        && albums[i].getSong(0).getArtist().equals(artistName)
                        && albums[i].getSong(0).getDuration() == dName) {
                    System.out.println("Song already exist. ");
                    System.out.println("Name: " + albums[i].getSong(0).getName());
                    System.out.println("Artist: " + albums[i].getSong(0).getArtist());
                    System.out.println("Duration: " + albums[i].getSong(0).getDuration());
                    System.out.println("Genre: " + albums[i].getSong(0).getGenre());
                } else if (albums[i].getSong(1) == null) {
                    Song newSong = new Song(sName);
                    newSong.setArtist(artistName);
                    newSong.setDuration(dName);
                    newSong.setGenre(gName);
                    albums[i].setSong(1, newSong);
                    System.out.println("Song added sucessfully.");
                } else if (albums[i].getSong(1).getName().equals(sName)
                        && albums[i].getSong(1).getArtist().equals(artistName)
                        && albums[i].getSong(1).getDuration() == dName) {
                    System.out.println("Song already exist. ");
                    System.out.println("Name: " + albums[i].getSong(1).getName());
                    System.out.println("Artist: " + albums[i].getSong(1).getArtist());
                    System.out.println("Duration: " + albums[i].getSong(1).getDuration());
                    System.out.println("Genre: " + albums[i].getSong(1).getGenre());
                } else if (albums[i].getSong(2) == null) {
                    Song newSong = new Song(sName);
                    newSong.setArtist(artistName);
                    newSong.setDuration(dName);
                    newSong.setGenre(gName);
                    albums[i].setSong(2, newSong);
                    System.out.println("Song added sucessfully.");
                } else if (albums[i].getSong(2).getName().equals(sName)
                        && albums[i].getSong(2).getArtist().equals(artistName)
                        && albums[i].getSong(2).getDuration() == dName) {
                    System.out.println("Song already exist. ");
                    System.out.println("Name: " + albums[i].getSong(2).getName());
                    System.out.println("Artist: " + albums[i].getSong(2).getArtist());
                    System.out.println("Duration: " + albums[i].getSong(2).getDuration());
                    System.out.println("Genre: " + albums[i].getSong(2).getGenre());
                } else if (albums[i].getSong(3) == null) {
                    Song newSong = new Song(sName);
                    newSong.setArtist(artistName);
                    newSong.setDuration(dName);
                    newSong.setGenre(gName);
                    albums[i].setSong(3, newSong);
                    System.out.println("Song added sucessfully.");
                } else if (albums[i].getSong(3).getName().equals(sName)
                        && albums[i].getSong(3).getArtist().equals(artistName)
                        && albums[i].getSong(3).getDuration() == dName) {
                    System.out.println("Song already exist. ");
                    System.out.println("Name: " + albums[i].getSong(3).getName());
                    System.out.println("Artist: " + albums[i].getSong(3).getArtist());
                    System.out.println("Duration: " + albums[i].getSong(3).getDuration());
                    System.out.println("Genre: " + albums[i].getSong(3).getGenre());
                } else if (albums[i].getSong(4) == null) {
                    Song newSong = new Song(sName);
                    newSong.setArtist(artistName);
                    newSong.setDuration(dName);
                    newSong.setGenre(gName);
                    albums[i].setSong(4, newSong);
                    System.out.println("Song added sucessfully.");
                } else if (albums[i].getSong(4).getName().equals(sName)
                        && albums[i].getSong(4).getArtist().equals(artistName)
                        && albums[i].getSong(4).getDuration() == dName) {
                    System.out.println("Song already exist. ");
                    System.out.println("Name: " + albums[i].getSong(4).getName());
                    System.out.println("Artist: " + albums[i].getSong(4).getArtist());
                    System.out.println("Duration: " + albums[i].getSong(4).getDuration());
                    System.out.println("Genre: " + albums[i].getSong(4).getGenre());
                } else {
                    System.out.println("Album is full.!");
                }
            }
        }

    }

    // check genre
    private boolean isValidGenre(String checkGenre) {
        String[] validGenres = { "rock", "pop", "hip-hop", "bossa nova" };
        for (String validGenre : validGenres) {
            if (validGenre.equals(checkGenre)) {
                return true;
            }
        }
        return false;
    }

    // delete album
    public void remove_album() {
        System.out.print("Enter the name of the Album: ");
        input.nextLine();
        name = input.nextLine().toLowerCase();

        int index = -1;
        for (int i = 0; i < albumSize; i++) {
            if (albums[i].getName().equals(name)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < albumSize - 1; i++) {
                albums[i] = albums[i + 1];
            }
            albums[albumSize - 1] = null;
            albumSize--;
            System.out.print("Album deleted successfully..!");
        } else {
            System.out.println("Album not found");
        }

    }

    // resetting album arrays
    public void resetAlbumArray(int index) {
        if (index == 4) {
            albums[index] = null;
        }
        for (int i = index; i <= albumSize; i++) {
            albums[i] = albums[i + 1];
        }
    }

    // delete song
    public void delSong() {
        System.out.print("Enter Album name: ");
        input.nextLine();
        String aName = input.nextLine();
        System.out.print("Enter Song name: ");
        input.nextLine();
        String sName = input.nextLine();
        boolean albumFound = false;

        for (int i = 0; i < albumSize; i++) {
            if (albums[i].getName().equals(aName)) {
                albums[i].delSongByName(sName);
                albumFound = true;
                break; // Added break statement to exit the loop once album is found
            }
        }

        if (!albumFound) {
            System.out.print("No album found.!");
        }
    }

    // list songs
    public void list_songs() {
        if (albumSize == 0) {
            System.out.println("No songs exist!");
        } else {
            for (int i = 0; i < albumSize; i++) {
                System.out.println(albums[i].getAllSongs());
            }
        }
    }

    // display albums
    public void a_List() {
        if (albumSize == 0) {
            System.out.println("Album is empty.");
            return;
        }
        for (int i = 0; i < albumSize; i++) {
            System.out.println(albums[i]);
        }

    }

    // list of song with genre
    public void L_SongsG() {
        System.out.print("Enter the Genre: ");
        input.nextLine();
        name = input.nextLine().toLowerCase();
        boolean check = true;
        for (int i = 0; i < albumSize; i++) {
            if (albums[i] != null) {
                Song[] songs = albums[i].getSongs();
                for (int j = 0; j < songs.length; j++) {
                    if (songs[j] != null) {
                        if (songs[j].getGenre().equals(name)) {
                            System.out.println(songs[j].toString());
                            check = false;
                        }
                    }
                }
            }
        }
        if (check) {
            System.out.println("no songs found in the genre.");
        }
    }

    // get songs under Duration
    public void D_Songs() {
        System.out.print("Enter the Duration in seconds: ");
        String checktype = input.next();
        while (check(checktype)) {
            System.out.print("Numbers only:");
            checktype = input.next();
        }
        int duration = Integer.parseInt(checktype);
        boolean check = true;
        for (int i = 0; i < albumSize; i++) {
            if (albums[i] != null) {
                Song[] songs = albums[i].getSongs();
                for (int j = 0; j < songs.length; j++) {
                    if (songs[j] != null) {
                        if (songs[j].getDuration() == duration) {
                            System.out.println(songs[j].toString());
                            check = false;
                        }
                    }
                }
            }
        }
        if (check) {
            System.out.println("no songs found under the duration.");
        }
    }

    // check entry
    public static boolean check(String check) {
        boolean type = false;
        int index = 0;
        while (index < check.length()) {
            if (!(check.charAt(index) >= '0' && check.charAt(index) <= '9')) {
                type = true;
            }
            index++;
        }
        return type;
    }

    public static boolean check2(String check2) {
        boolean type = false;
        int index = 0;
        while (index < check2.length()) {
            if (!(check2.charAt(index) == '1' || check2.charAt(index) == '2')) {
                type = true;
            }
            index++;
        }
        return type;
    }

    public void readExternalFile() {
        System.out.print("Enter the file name: ");
        String file = input.next();
        readExternalFile(file);
        System.out.println("\nFile read successfully.\n");
    }

    // control section
    public void run() {
        // readExternalFile("ReginaCollection.txt");
        int sel = 0;
        while (sel != 10) {
            sel = selection();
            if (sel == 1) {
                readExternalFile();

            } else if (sel == 2) {
                add_album();

            } else if (sel == 3) {

                check_song();
            } else if (sel == 4) {
                remove_album();

            } else if (sel == 5) {
                delSong();

            } else if (sel == 6) {
                list_songs();

            } else if (sel == 7) {
                a_List();

            } else if (sel == 8) {
                L_SongsG();

            } else if (sel == 9) {
                D_Songs();

            } else if (sel == 10) {
                break;
            }

        }
    }

    public static void main(String[] args) {
        SongCollection sg = new SongCollection();
        sg.run();
    }

    public int getAlbumSize() {
        return albumSize;
    }

    public void setAlbumSize(int albumSize) {
        this.albumSize = albumSize;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
