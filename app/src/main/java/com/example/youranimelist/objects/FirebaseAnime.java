package com.example.youranimelist.objects;

import java.io.Serializable;

public class FirebaseAnime implements Serializable {

    String name;
    int episodes;
    double rating;
    String description;
    String genre;

    FirebaseAnime() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public FirebaseAnime(String name, int episodes, double rating, String description, String genre) {
        this.name = name;
        this.episodes = episodes;
        this.rating = rating;
        this.description = description;
        this.genre = genre;



    }

    public String epsToString() {
        return " " + episodes ;
    }

    public String ratingToString(){
        return rating + "/10";
    }


}
