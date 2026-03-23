package com.pao.laboratory05.playlist;
import java.util.Comparator;

public class SongDurationComparator implements Comparator<Song> {
    public int compare(Song s1, Song s2){
        if (s1.durationSeconds() > s2.durationSeconds())
            return 1;
        if (s1.durationSeconds() == s2.durationSeconds())
            return 0;
        return -1;
    }
}
