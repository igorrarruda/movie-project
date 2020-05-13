CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE movie (
    id BIGSERIAL NOT NULL,
    title VARCHAR(255) UNIQUE NOT NULL,
    synopsis TEXT NOT NULL,
    release_year NUMERIC(4,0) NOT NULL,
    movie_crew TEXT NOT NULL,
    movie_cast TEXT NOT null,    
	PRIMARY KEY (id)
);

CREATE TABLE review (
    id BIGSERIAL NOT NULL,
    review_user VARCHAR(255) NOT NULL,
    review_comment VARCHAR(255) NOT NULL,
    rate NUMERIC(1,0) NOT NULL,
    movie_id BIGINT NOT null,
	PRIMARY KEY (id),
	FOREIGN KEY (movie_id) REFERENCES movie(id)
);
