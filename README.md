# URL Shortener MVP

A minimal viable product (MVP) for a URL shortener built with Spring Boot.

## Features

- Shorten long URLs to compact links
- Redirect short URLs to original destinations
- Basic REST API endpoints

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

### API Endpoints

- `POST /api/shorten`  
    Create a short URL  
    **Body:** `{ "url": "https://example.com" }`

- `GET /{shortCode}`  
    Redirect to the original URL

## Project Structure

- `src/main/java` — Source code
- `src/main/resources` — Configuration

## License

MIT