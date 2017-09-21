package mobilestudio.io.core.moviesapp.model;

import java.io.Serializable;

/**
 * Created by Sayed on 9/21/2017.
 */

public class Movie implements Serializable {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getRelaseDate() {
        return relaseDate;
    }

    public void setRelaseDate(String relaseDate) {
        this.relaseDate = relaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoteaverage() {
        return voteaverage;
    }

    public void setVoteaverage(String voteaverage) {
        this.voteaverage = voteaverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String id ;
    private String posterUrl;
    private String relaseDate;
    private String overview;
    private String voteaverage;
    private String title;


}
