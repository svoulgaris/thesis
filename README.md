# Zekvyrin's thesis

Well.. this is just a repository for me putting the code I write for my thesis.
Work code, test code, anything.. 

Of course any suggestion/contributions are welcome :P

### Folders

##### data
Contains example data for usage:

- users.txt		Contains sample graph's vertices (id,username)
- connections.txt	Contains sample graph's edges (source & destination vertex)

I'm assuming that this graph is a not-directed graph, so all edges are in form [Smaller Vertex Index] -> [Bigger Vertex Index]

##### projects

This forder's subfolders each contain code & sbt for a certain task

Current projects: 
- Average Clustering Coefficient


### Compile & Execute instructions

Go to the folder:
```sh
cd project/[Project's folder]
```

Compile:
```sh
sbt package
```

Execute:
```sh
spark-submit --class "[Project class]" --master local[2] target/scala-2.10/[project name]_2.10-1.0.jar
```

Example for Average Clustering Coefficient:
```sh
cd project/AvgClusterCoeff/
sbt package
spark-submit --class "AvgClusteringCoeff" --master local[2] target/scala-2.10/average-clustering-coefficient_2.10-1.0.jar
```
