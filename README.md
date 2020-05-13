Movie Review Web Application
=========

A book registration project using Java + Spring Boot + JPA + Postgres + Docker.

Getting started
---------------

Download [Docker Desktop](https://www.docker.com/products/docker-desktop) for Mac or Windows. [Docker Compose](https://docs.docker.com/compose) will be automatically installed. On Linux, make sure you have the latest version of [Compose](https://docs.docker.com/compose/install/). 


## Linux Containers

The Linux stack uses Python, Node.js, .NET Core (or optionally Java), with Redis for messaging and Postgres for storage.

> If you're using [Docker Desktop on Windows](https://store.docker.com/editions/community/docker-ce-desktop-windows), you can run the Linux version by [switching to Linux containers](https://docs.docker.com/docker-for-windows/#switch-between-windows-and-linux-containers), or run the Windows containers version.

## Init
Run Maven Package:
```
nvm clean package
```

Run in this directory:
```
docker-compose up
```
Create SQL structure:
```sql
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
```

The app will be running at [http://localhost:8080/movies](http://localhost:8080/movies).

## DOCS

[https://documenter.getpostman.com/view/161834/SzmiVvaP?version=latest](https://documenter.getpostman.com/view/161834/SzmiVvaP?version=latest)
