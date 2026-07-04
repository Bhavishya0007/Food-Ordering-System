# Food Order System

Low-level design of a restaurant food ordering and rating system, similar to
DoorDash / Uber Eats. The same food item (e.g. `veg-burger`)
can be sold by multiple restaurants, each tracked with its own rating.

## Build & run

Standard Maven layout (`java/src/main/java`, `java/src/test/java`), built with
`java/pom.xml`. JUnit 5 (`junit-jupiter`) is a normal test-scoped dependency
resolved from Maven Central.

```bash
mvn test              # compile + run unit tests
mvn compile exec:java # run the Main demo walkthrough
```

`mvn`/`java` may not be on `PATH` by default on macOS even with a JDK
installed via Homebrew — point at it explicitly if needed, e.g.
`PATH="$(brew --prefix openjdk)/bin:$PATH"`.