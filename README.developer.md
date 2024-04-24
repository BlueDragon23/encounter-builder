# Development

The project consists of a SvelteKit project alongside a Spring Boot backend.

The SvelteKit project handles routing and basic data loading. The Spring Boot side handles the actual logic of managing encounters and other entities. 

## Spring Boot

To run the Spring Boot server, use `./gradlew bootRun`

To start the database, the file `bin/secrets/db_password.txt` must exist and contain the postgres password

## SvelteKit

To run the SvelteKit application, use `npm run preview` from the `svelte` directory