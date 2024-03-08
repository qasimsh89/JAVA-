

/**
 * in this file we are storing values of songs in and returning it.
 *
 * @author (Name: Muhammad Qasim ID: c3360527)
 * @version (28/04/2023)
 */
public class Song {
	private String name;
	private String artist;
	private int duration;
	private String genre;

	// add comments

	public Song(String name) {
		this.name = name;
		this.artist = artist;
		this.duration = 0;
		this.genre = genre;

	}

	/**
	 * @return string return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return string name return
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist name set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return int return
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration setting duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return genre return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre set genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;

	}

	public String toString() {
		return "Song ( Name: " + this.name + ", Artist: " + this.artist + ", Duration: " + this.duration + ", Genre: "
				+ this.genre + ")";
	}
}
