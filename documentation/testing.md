# Testing

## Unit tests

Unit tests can be run with command
```
gradlew test
```

To generate a code coverage report
```
gradlew test jacocoTestReport 
```
The report can be found in `build/reports/jacoco/test/html/index.html`.

### How the unit tests work

Unit tests make sure that parts of the algorithms work as intended, and the generator is working correctly.

The tests use validated pre-generated data, and tests are run with a specific seed.
