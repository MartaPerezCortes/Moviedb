package com.example.themoviedb.json_mapper;

import java.util.List;

public class MovieResponse { // hace referencia la lista de los objetos Movie
    //URL del ENDPOINT: https://api.themoviedb.org/3/movie/popular?api_key=4e21b129094e725e7136e5341af06f19
    private List<Movie> results;

    // Getters y Setters
    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
