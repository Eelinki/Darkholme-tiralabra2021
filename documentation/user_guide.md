# User guide

## Launching the application

Download the newest release and open the jar-file by double clicking it, or run it using command `java -jar Darkholme-tiralabra2021-1.0-SNAPSHOT-all.jar`.

## Using the application

![User interface](https://i.imgur.com/ti8xvew.png)

### Parameters

- Width
  - Width of the map in pixels
- Height
  - Height of the map in pixels
- Points
  - Amount of initial random points used by the Delaunay triangulation.
  - Recommended value: >5
- Seed
  - The seed used for the generation process.
- Corridors %
  - Percentage of edges from the Delaunay triangulation used to create corridors.
- Scale
  - Option to upscale the generated image.

Then click "Generate".


## Running tests

Instructions for running automated tests can be found [here](https://github.com/Eelinki/Darkholme-tiralabra2021/blob/main/documentation/testing.md)