# Specifications

The project uses Java 11.

It uses a combination of graph and pathfinding algorithms to generate a dungeon type map for roguelike video games.

## Algorithms and generation process

1. Generate a random point cloud in a 2d matrix (this could be improved to use poisson disk sampling).
1. Create a graph using Delaunay triangulation (Bowyer-Watson).
1. Find the graphs minimum spanning tree (Prim).
1. Add few edges back from the Delaunay graph to create more corridors, resulting in a less linear dungeon.
1. Create randomly sized rooms for each of the points.
1. Finally create the corridors between the rooms.

Sources:

- [Procedural Dungeon Generation #2](https://github.com/a327ex/blog/issues/7) by a327ex and TinyKeepDev
- [Making maps with noise functions](https://www.redblobgames.com/maps/terrain-from-noise/) by Red Blob Games
- [Bowyer–Watson algorithm](https://en.wikipedia.org/wiki/Bowyer%E2%80%93Watson_algorithm) Wikipedia

### Input data

User can specify:
- The amount of points
- The size of the map
- Percentage of edges from the Delaynay graph used to create corridors
- The seed used for the generation process.


### Time complexity targets

- Delaunay triangulation O(n<sup>2</sup>)
- Prim's algorithm O(n + m log n)

## Data structures

The generated dungeon is saved as a two-dimensional array, and can be exported as a bitmap image.

Prim's algorithm requires the use of binary heaps.

Random function with seed is required for testing purposes.

## User interface

The user interface is made using JavaFX. User can specify the input data and hit generate button,
and the generated dungeon will be shown in the GUI. 

## Tiralabra

Bachelor's Programme in Computer Science.

Documentation is in English.
