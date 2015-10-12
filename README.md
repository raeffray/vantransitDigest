# vatransitDigest

Populates a Neo4J database with Vancouver Transit information.

## Running Neo4J with Docker

```
docker run -d --name neo4j -v $PWD/neo4j-data:/data -p 7474:7474 neo4j/neo4j
```

More info at http://neo4j.com/developer/docker/.
