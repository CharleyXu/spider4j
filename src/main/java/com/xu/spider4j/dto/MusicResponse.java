package com.xu.spider4j.dto;

import java.util.List;
import java.util.Objects;

/**
 * @author xuzc
 */
public class MusicResponse {

    private int id;
    private String name;
    private int score;
    private Album album;
    private String commentThreadId;
    private List<Artist> artists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicResponse that = (MusicResponse) o;
        return id == that.id && score == that.score && Objects.equals(name, that.name) && Objects.equals(album, that.album) && Objects.equals(commentThreadId, that.commentThreadId) && Objects.equals(artists, that.artists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, score, album, commentThreadId, artists);
    }

    public static class Album {
        private String name;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Album album = (Album) o;
            return Objects.equals(name, album.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    public static class Artist {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Artist artist = (Artist) o;
            return id == artist.id && Objects.equals(name, artist.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

}
