
/**
 * @author (Name: Muhammad Qasim ID: c3360527)
 * @version (28/04/2023)
 */

public class Album {

    // instance variables
    private String name;
    private Song[] songs;
    private int totalTime;
    private final int MAX_TIME = 12; // 12 minutes

    /**
     * Constructor for objects of class Album
     */
    public Album(String a_name) {
        // initialise instance variables
        this.name = a_name;
        songs = new Song[5];
        this.totalTime = 0;

    }

    public String toString() { // returing stored values
        String str = "Album (Name:" + this.name + ", Songs: [";
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                str += songs[i];
            }
        }
        str += "] , Total time: [" + this.getTotalTime() + "] , Max limit: ["
                + this.MAX_TIME + "] )";
        return str;
    }

    public Song[] getSongs() {
        return songs;
    }

    // songs strings
    public String getAllSongs() {
        String str = "";
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                str += songs[i];
            }
        }
        if (str.equals("")) {
            return "No Songs found.";
        } else {
            return str;
        }

    }

    // song control set and get.
    public Song getSong(int i) {
        if (i >= 0 && i < songs.length) {
            return songs[i];
        } else {
            return null;
        }
    }

    public void setSong(int i, Song newSong) {
        if (i >= 0 && i < songs.length) {
            songs[i] = newSong;
        }
    }

    // delete song
    public void delSongByName(String name) {
        boolean found = false;
        for (int i = 0; i < songs.length; i++) {
            if (getSong(i) != null) {
                if (getSong(i).getName().equals(name)) {
                    delSong(0);
                    System.out.println("Song deleted sucessfully");
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No songs found.!");
        }

    }

    // setting songs
    public void delSong(int index) {
        if (index == 4) {
            setSong(index, null);
        }
        for (int i = index; i <= songs.length; i++) {
            setSong(i, getSong(i + 1));
        }
    }

    // genre
    public String getGenre() {
        String str = "";
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                str += songs[i].getGenre();
            }
        }
        if (str.equals("")) {
            return "No Genre found.";
        } else {
            return str;
        }
    }

    // duration
    public String getDuration() {
        String str = "";
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                str += songs[i].getDuration();
            }
        }
        if (str.equals("")) {
            return "No songs found under the provided duration.";
        } else {
            return str;
        }

    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return String return the total time
     */
    public int getTotalTime() {
        totalTime = 0;
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                totalTime += songs[i].getDuration();
            }
        }
        return totalTime;

    }

    /**
     * @return String return the Max time
     */
    public int getMAX_TIME() {
        return MAX_TIME;
    }

}
