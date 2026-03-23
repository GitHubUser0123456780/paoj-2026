package com.pao.laboratory05.playlist;

import java.util.Arrays;

public class Playlist {
    private String name;
    private Song[] songs = new Song[0];
    public Playlist(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void addSong(Song s){
        songs = Arrays.copyOf(songs, songs.length + 1);
        songs[songs.length-1] = s;
    }
    public void printSortedByTitle(){
        Song[] songs_copy = songs.clone();
        Arrays.sort(songs_copy);
        for(Song s:songs_copy)
            System.out.println(s);
    }
    public void printSortedByDuration(){
        Song[] songs_copy = songs.clone();
        Arrays.sort(songs_copy, new SongDurationComparator());
        for(Song s:songs_copy)
            System.out.println(s);
    }
    public int getTotalDuration(){
        int total = 0;
        for (Song s:songs)
            total+=s.durationSeconds();
        return total;
    }
}
