# Implementation

## Data structures

The final generated dungeon is saved into a two-dimensional array (int[][]).

## Performance

### Time complexity

- Delaunay triangulation (Bowyer-Watson) O(n<sup>2</sup>)
- Minimum spanning tree (Kruskal) O(E log E), E = amount of edges
- Bresenham's line algorithm O(n)


## Input data

User can specify:
- The amount of points
- The size of the map
- Percentage of edges from the Delaynay graph used to create corridors
- The seed used for the generation process.


## Sources

- [Procedural Dungeon Generation #2](https://github.com/a327ex/blog/issues/7) by a327ex and TinyKeepDev
- [Making maps with noise functions](https://www.redblobgames.com/maps/terrain-from-noise/) by Red Blob Games
- [Bowyer–Watson algorithm](https://en.wikipedia.org/wiki/Bowyer%E2%80%93Watson_algorithm) Wikipedia
- [Bresenham's line algorithm](https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm) Wikipedia