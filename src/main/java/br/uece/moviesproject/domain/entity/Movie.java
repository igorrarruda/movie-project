package br.uece.moviesproject.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String synopsis;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "movie_crew", nullable = false)
    private String crew;

    @Column(name = "movie_cast", nullable = false)
    private String cast;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Transient
    private double averageRatings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @PostLoad
    private void postLoad() {
        List<Review> reviews = getReviews();
        if (reviews.isEmpty()) {
            return;
        }
        int votesSum = reviews.stream().reduce(0, (subtotal, element) -> subtotal + element.getRate(), Integer::sum);
        int votesTotal = reviews.size();
        this.averageRatings = (double) votesSum / votesTotal;
    }

    public double getAverageRatings() {
        return averageRatings;
    }
}