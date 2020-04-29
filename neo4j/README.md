# Neo4j
### Mauro Schramm

## Exercício 1
### 1.1: Retrieve all nodes from the database
match(n) return n

### 1.2: Examine the data model of the graph
CALL db.schema.visualization()

### 1.3: Retrieve all  _Person_  nodes
MATCH (p:Person) RETURN p

### 1.4: Retrieve all  _Movie_  nodes
MATCH (m:Movie) RETURN m


## Exercício 2
### 2.1: Retrieve all movies that were released in a specific year
match (m:Movie {released: 2003}) return m

### 2.2: View the retrieved results as a table
m
{
"title": "The Matrix Reloaded",
"tagline": "Free your mind",
"released": 2003
}
{
"title": "The Matrix Revolutions",
"tagline": "Everything that has a beginning has an end",
"released": 2003
}
{
"title": "Something's Gotta Give",
"released": 2003
}
##### Retrieve all movie nodes in the database and view the data as a table. Notice the values for the released property for each node.
match (m:Movie) return m

##### Try querying the graph using different years.
match (m:Movie {released: 2004}) return m

### 2.3: Query the database for all property keys
call db.propertyKeys

### 2.4: Retrieve all Movies released in a specific year, returning their titles (Instructions)
match (m:Movie {released: 1999}) return m.title, m.tagline

### 2.5: Display  _title_,  _released_, and  _tagline_  values for every  _Movie_  node in the graph
match (m:Movie) return m.title, m.released, m.tagline

### 2.6: Display more user-friendly headers in the table
MATCH (m:Movie) RETURN m.title AS `movie title`, m.released AS released, m.tagline AS tagLine


## Exercício 3
### 3.1: Display the schema of the database
CALL db.schema.visualization

### 3.2: Retrieve all people who wrote the movie  _Speed Racer_
MATCH (p:Person)-[:WROTE]->(:Movie {title: 'Speed Racer'}) RETURN p.name
#### pessoas que escreveram outros filmes
MATCH (p:Person)-[:WROTE]->(m:Movie) where m.title <> 'Speed Racer' with distinct p RETURN p.name
#### pessoas que atuaram em determinado filme
MATCH (p:Person)-[:ACTED_IN]->(:Movie {title: 'Speed Racer'}) RETURN p.name
#### pessoas que dirigiram determinado filme
MATCH (p:Person)-[:DIRECTED]->(:Movie {title: 'Speed Racer'}) RETURN p.name

### 3.3: Retrieve all movies that are connected to the person,  _Tom Hanks_
MATCH (m:Movie)<--(:Person {name: 'Tom Hanks'}) RETURN m.title
####  Retrieve all movies connected with another actor.
MATCH (m:Movie)<-[:ACTED_IN]-(p:Person) where p.name <> 'Tom Hanks' with distinct m RETURN m
#### Retrieve all people connected with a particular movie.
MATCH (:Movie {title: 'The Matrix'})<--(p:Person) with distinct p RETURN p


### 3.4: Retrieve information about the relationships  _Tom Hanks_  has with the set of movies retrieved earlier
 MATCH (m:Movie)<-[r]-(:Person {name: 'Tom Hanks'}) RETURN m.title, type(r)

### 3.5: Retrieve information about the roles that  _Tom Hanks_  acted in
MATCH (m:Movie)-[rel:ACTED_IN]-(:Person {name: 'Tom Hanks'}) RETURN m.title, rel.roles


## Exercício 4
### 4.1: Retrieve all movies that  _Tom Cruise_  acted in
MATCH (a:Person)-[:ACTED_IN]->(m:Movie) WHERE a.name = 'Tom Cruise' RETURN m.title as Movie

### 4.2: Retrieve all people that were born in the 70’s
MATCH (a:Person) WHERE a.born >= 1970 AND a.born < 1980 RETURN a.name as Name, a.born as `Year Born`

### Exercise 4.3: Retrieve the actors who acted in the movie  _The Matrix_  who were born after 1960
MATCH (a:Person)-[:ACTED_IN]->(m:Movie)
WHERE a.born > 1960 AND m.title = 'The Matrix'
RETURN a.name as Name, a.born as `Year Born`

### 4.4: Retrieve all movies by testing the node label and a property (Instructions)
MATCH (m) WHERE m:Movie AND m.released = 2000 RETURN m.title

### 4.5: Retrieve all people that wrote movies by testing the relationship between two nodes
MATCH (a)-[rel]->(m) WHERE a:Person AND type(rel) = 'WROTE' AND m:Movie RETURN a.name as Name, m.title as Movie

### 4.6: Retrieve all people in the graph that do not have a property
MATCH (p:Person) WHERE NOT exists(p.born) RETURN p.name as Nome

### 4.7: Retrieve all people related to movies where the relationship has a property
MATCH (p:Person)-[r]->(m:Movie)
WHERE exists(r.rating)
RETURN p.name as nome, m.title as filme, r.rating as avaliação

### 4.8: Retrieve all actors whose name begins with  _James_
MATCH (p:Person)-[:ACTED_IN]->(:Movie) WHERE p.name =~ 'James.*' RETURN p.name

### 4.9: Retrieve all  _REVIEWED_  relationships from the graph with filtered results
MATCH (:Person)-[r:REVIEWED]->(m:Movie)
WHERE toLower(r.summary) CONTAINS 'fun'
RETURN  m.title as filme, r.summary as crítica, r.rating as avaliação
#### Retrieve all movies in the database that have _love_ in their _tagline_ and return the movie titles
MATCH (m:Movie)
WHERE exists(m.tagline) 
  and toLower(m.tagline) CONTAINS 'love'
RETURN  m.title as filme, m.tagline as slogan
#### Retrieve movies in the database, specifying a regular expression for the content of the _tagline_.
MATCH (m:Movie)
WHERE exists(m.tagline) 
  and toLower(m.tagline) =~ '.*story.*'
RETURN  m.title as filme, m.tagline as slogan

### 4.10: Retrieve all people who have produced a movie, but have not directed a movie
MATCH (p:Person)-[:PRODUCED]->(m:Movie) WHERE NOT ((p)-[:DIRECTED]->(:Movie)) RETURN p.name, m.title

### 4.11: Retrieve the movies and their actors where one of the actors also directed the movie
MATCH (p1:Person)-[:ACTED_IN]->(f:Movie)<-[:ACTED_IN]-(p2:Person)
WHERE exists( (p2)-[:DIRECTED]->(f) )
RETURN  p1.name as ator, p2.name as `ator/diretor`, f.title as título

### 4.12: Retrieve all movies that were released in a set of years
MATCH (f:Movie) WHERE f.released in [2000, 2004, 2008] RETURN f.title, f.released

### 4.13: Retrieve the movies that have an actor’s role that is the name of the movie
MATCH (p:Person)-[r:ACTED_IN]->(f:Movie) WHERE f.title in r.roles RETURN f.title as filme, p.name as ator


## Exercício 5
### 5.1: Retrieve data using multiple  `MATCH`  patterns
match (a:Person)-[:ACTED_IN]->(m:Movie)<-[:DIRECTED]-(d:Person),
      (a2:Person)-[:ACTED_IN]->(m)

### 5.2: Retrieve particular nodes that have a relationship
match (james:Person)<-[:FOLLOWS]->(outros:Person) where james.name = 'James Thompson' return james, outros

### 5.3: Modify the query to retrieve nodes that are exactly three hops away
match (james:Person)<-[:FOLLOWS*3]->(outros:Person) where james.name = 'James Thompson' return james, outros

### 5.4: Modify the query to retrieve nodes that are one and two hops away
match (james:Person)<-[:FOLLOWS*1..2]->(outros:Person) where james.name = 'James Thompson' return james, outros

### 5.5: Modify the query to retrieve particular nodes that are connected no matter how many hops are required
match (james:Person)<-[:FOLLOWS*]->(outros:Person) where james.name = 'James Thompson' return james, outros

### 5.6: Specify optional data to be retrieved during the query
match (p:Person) where p.name starts with 'Tom' optional match (p)-[:DIRECTED]->(m:Movie) RETURN p.name, m.title

### 5.7: Retrieve nodes by collecting a list
match (ator:Person)-[:ACTED_IN]->(filme:Movie) return ator.name as Ator, collect(filme.title) as Filmes

### 5.8: Retrieve all movies that  _Tom Cruise_  has acted in and the co-actors that acted in the same movie by collecting a list
match (tom:Person)-[:ACTED_IN]->(filme:Movie)<-[:ACTED_IN]-(outros:Person)
where tom.name = 'Tom Cruise'
return filme.title as Filme, collect(outros.name) as Atores

### 5.9: Retrieve nodes as lists and return data associated with the corresponding lists
match (crit:Person)-[:REVIEWED]->(filme:Movie)
return filme.title as Filme, collect(crit.name) as `Críticos`, count(crit) as `Número de Críticos`

### 5.10: Retrieve nodes and their relationships as list
match (dir:Person)-[:DIRECTED]->(filme:Movie)<-[:ACTED_IN]-(ator:Person)
return dir.name as Diretor, collect(ator.name) as Atores, count(ator) as `Núm Atores`

### 5.11: Retrieve the actors who have acted in exactly five movies
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
with ator.name as Ator, collect(filme.title) as Filmes, count(filme) as NumFilmes
where NumFilmes = 5
return Ator, Filmes

### 5.12: Retrieve the movies that have at least 2 directors with other optional data
match (dir:Person)-[:DIRECTED]->(filme:Movie)
with filme, count(dir) as NumDir
where NumDir >= 2
optional match (crit:Person)-[:REVIEWED]->(filme)
return filme.title as Filme, collect(crit.name) as `Críticos`


## Exercício 6
### 6.1: Execute a query that returns duplicate records.
MATCH (a:Person)-[:ACTED_IN]->(m:Movie)
WHERE m.released >= 1990 AND m.released < 2000
RETURN DISTINCT m.released, m.title, collect(a.name)

### 6.2: Modify the query to eliminate duplication
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
where filme.released >=1990 and filme.released < 2000
return filme.released as Ano, collect(filme.title) as Filmes, collect(ator.name) as Atores

### 6.3: Modify the query to eliminate more duplication.
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
where filme.released >=1990 and filme.released < 2000
return filme.released as Ano, collect(distinct filme.title) as Filmes, collect(ator.name) as Atores

### 6.4: Sort results returned
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
where filme.released >=1990 and filme.released < 2000
return filme.released as Ano, collect(distinct filme.title) as Filmes, collect(ator.name) as Atores
order by filme.released desc

### 6.5: Retrieve the top 5 ratings and their associated movies
match (crit:Person)-[r:REVIEWED]->(filme:Movie)
return filme.title as Filme, r.rating as `Avaliação`
order by r.rating desc limit 5

### 6.6: Retrieve all actors that have not appeared in more than 3 movies
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
with ator.name as Ator, collect(filme.title) as Filmes, count(filme) as NumFilmes
where NumFilmes <=3
return Ator, Filmes


## Exercício 7
### 7.1: Collect and use lists
match (ator:Person)-[:ACTED_IN]->(filme:Movie)<-[:PRODUCED]-(prod:Person)
with filme, collect(distinct ator.name) as elenco, collect(distinct prod.name) as produtores
return filme.title as Filme, elenco as Elenco, produtores as Produtores
order by size(elenco)

### 7.2: Collect a list
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
with ator, collect(distinct filme.title) as filmografia
where size(filmografia) > 5
return ator.name as Ator, filmografia
order by size(filmografia)

### 7.3: Unwind a list
match (ator:Person)-[:ACTED_IN]->(filme:Movie)
with ator, collect(distinct filme) as filmografia
where size(filmografia) > 5
with ator, filmografia unwind filmografia as filme
return ator.name as Ator, filme.title

### 7.4: Perform a calculation with the date type
MATCH (tom:Person)-[:ACTED_IN]->(f:Movie)
WHERE tom.name = 'Tom Hanks'
RETURN  f.title, f.released, date().year  - f.released as `Anos desde lanc.`, f.released  - tom.born AS `Idade de Tom`
ORDER BY `Anos desde lanc.`


## Exercício 8
### 8.1: Create a  _Movie_  node
 CREATE (:Movie {title: 'Forrest Gump'})

### 8.2: Retrieve the newly-created node
match (fg:Movie) where fg.title = 'Forrest Gump' return fg

### 8.3: Create a  _Person_  node
CREATE (:Person {name: 'Robin Wright'}) 

### 8.4: Retrieve the  _Person_  node you just created by its  _name_
match (rw:Person) where rw.name = 'Robin Wright' return rw

### 8.5: Add a label to a node
MATCH (f:Movie) WHERE f.released < 2010 SET f:OlderMovie RETURN DISTINCT labels(f)

### 8.6: Retrieve the node using the new label
MATCH (fv:OlderMovie) RETURN fv.title, fv.released

### 8.7: Add the  _Female_  label to selected nodes
MATCH (r:Person) WHERE r.name STARTS WITH 'Robin' SET r:Female

### 8.8: Retrieve all  _Female_  nodes
match (f:Female) return f

### 8.9: Remove the  _Female_  label from the nodes that have this label
MATCH (f:Female) REMOVE f:Female

### 8.10: View the current schema of the graph
call db.schema.visualization()

### 8.11: Add properties to a movie
match (fg:Movie)
where fg.title = 'Forrest Gump'
SET fg.released = 1994,
    fg.tagline = "Life is like a box of chocolates...you never know what you're gonna get.",
    fg.lengthInMinutes = 142

### 8.12: Retrieve an  _OlderMovie_  node to confirm the label and properties
#### colocar label
match (fg:Movie) where fg.title = "Forrest Gump" set fg:OlderMovie RETURN labels(fg)
#### consultar filme
match (fg:OlderMovie) where fg.title = "Forrest Gump" RETURN fg

### 8.13: Add properties to the person,  _Robin Wright_
match (rw:Person) where rw.name = "Robin Wright" set rw.born = 1966, rw.birthPlace = 'Dallas' 

### 8.14: Retrieve an updated  _Person_  node
match (rw:Person) where rw.name = "Robin Wright" return rw

### 8.15: Remove a property from a  _Movie_  node
match (fg:Movie) where fg.title = 'Forrest Gump' set fg.lengthInMinutes = null

### 8.16: Retrieve the node to confirm that the property has been removed
match (fg:Movie) where fg.title = 'Forrest Gump' return fg

### 8.17: Remove a property from a  _Person_  node
match (rw:Person) where rw.name = "Robin Wright" remove rw.birthPlace

### 8.18: Retrieve the node to confirm that the property has been removed
match (rw:Person) where rw.name = "Robin Wright" return rw


## Exercício 9
### 9.1: Create  _ACTED_IN_  relationships
match (fg:Movie)
where fg.title = 'Forrest Gump'
match (ator:Person)
where ator.name = 'Tom Hanks' OR ator.name = 'Robin Wright' OR ator.name = 'Gary Sinise'
create (ator)-[:ACTED_IN]->(fg)

### 9.2: Create  _DIRECTED_  relationships
match (fg:Movie)
where fg.title = 'Forrest Gump'
match (diretor:Person)
where diretor.name = 'Robert Zemeckis'
create (diretor)-[:DIRECTED]->(fg)

### 9.3: Create a  _HELPED_  relationship
match (th:Person), (gs:Person) where th.name = 'Tom Hanks' and gs.name = 'Gary Sinise' create (th)-[:HELPED]->(gs)

### 9.4: Query nodes and new relationships
match (p:Person)-[r]-(f:Movie) where f.title = 'Forrest Gump' return p, r, f

### 9.5: Add properties to relationships
match (fg:Movie)<-[r:ACTED_IN]-(ator:Person)
where fg.title = 'Forrest Gump'
set r.roles =
case ator.name
  when 'Tom Hanks' then ['Forrest Gump']
  when 'Robin Wright' then ['Jenny Curran']
  when 'Gary Sinise' then ['Lieutenant Dan Taylor']
end

### 9.6: Add a property to the  _HELPED_  relationship
match (gs:Person)<-[r:HELPED]-(th:Person)
where gs.name = 'Gary Sinise' and th.name = 'Tom Hanks'
set r.research = 'war history'

### 9.7: View the current list of property keys in the graph
call db.propertyKeys

### 9.8: View the current schema of the graph
call db.schema.visualization

### 9.9: Retrieve the names and roles for actors
match (fg:Movie)<-[r:ACTED_IN]-(ator:Person)
where fg.title = 'Forrest Gump'
return ator.name as Ator, r.roles as `Papéis`

### 9.10: Retrieve information about any specific relationships
match (n1)-[r:HELPED]-(n2) return n1,r,n2

### 9.11: Modify a property of a relationship
match (fg:Movie)<-[r:ACTED_IN]-(gs:Person)
where fg.title = 'Forrest Gump' and gs.name = 'Gary Sinise'
  set r.roles = ['Lt. Dan Taylor']
return gs.name as Ator, r.roles as `Papéis`

### 9.12: Remove a property from a relationship
match (gs:Person)<-[r:HELPED]-(th:Person) where gs.name = 'Gary Sinise' and th.name = 'Tom Hanks' remove r.research

### 9.13: Confirm that your modifications were made to the graph
match (f:Movie)<-[r:ACTED_IN]-(a:Person) WHERE f.title = 'Forrest Gump' return a, r, f


## Exercício 10
### 10.1: Delete a relationship
match (:Person)-[h:HELPED]->(:Person) delete h

### 10.2: Confirm that the relationship has been deleted
match (:Person)-[h:HELPED]->(:Person) return h

### 10.3: Retrieve a movie and all of its relationships
match (fg:Movie)-[r]-(outro) where fg.title = 'Forrest Gump' return fg, r, outro

### 10.4: Try deleting a node without detaching its relationships
match (fg:Movie) where fg.title = 'Forrest Gump' delete fg

### 10.5: Delete a  _Movie_  node, along with its relationships
match (fg:Movie) where fg.title = 'Forrest Gump' detach delete fg

### 10.6: Confirm that the  _Movie_  node has been deleted
 match (fg:Movie)-[r]-(outro) where fg.title = 'Forrest Gump' return fg, r, outro


## Exercício 11
### 11.1: Use  `MERGE`  to create a  _Movie_  node
merge (f:Movie {title: 'Forrest Gump'}) on create set f.released = 1994 return f

### 11.2: Use  `MERGE`  to update a node
merge (f:Movie {title: 'Forrest Gump'})
on create set f.released = 1994
on match set f.tagline = "Life is like a box of chocolates...you never know what you're gonna get."
return f

### 11.3: Use  `MERGE`  to create a  _Production_  node
merge (p:Production {title: 'Forrest Gump'}) on create set p.year = 1994 return p

### 11.4: Find all labels for nodes with a property value
match (n) where n.title = 'Forrest Gump' return labels(n)

### 11.5: Use  `MERGE`  to update a  _Production_  node
merge (fg:Production {title: 'Forrest Gump'}) on match set fg.company = 'Paramount Pictures' return fg

### 11.6: Use  `MERGE`  to add a label to a node
merge (fg:Movie {title: 'Forrest Gump'}) on match set fg:OlderMovie return labels(fg)

### 11.7: Use  `MERGE`  to create two nodes and a single relationship
 MERGE (p:Person {name: 'Robert Zemeckis'})-[:DIRECTED]->(m {title: 'Forrest Gump'})

### 11.8: Use the same  `MERGE`  statement to attempt to create two nodes and a single relationship
MERGE (p:Person {name: 'Robert Zemeckis'})-[:DIRECTED]->(m {title: 'Forrest Gump'})

### 11.9: Find the correct  _Person_  node to delete
MATCH (p:Person {name: 'Robert Zemeckis'})-[rel]-(x) WHERE NOT EXISTS (p.born) RETURN p, rel, x

### 11.10: Delete this  _Person_  node, along with its relationships
MATCH (p:Person {name: 'Robert Zemeckis'})--() WHERE NOT EXISTS (p.born) DETACH DELETE p

### 11.11: Find the correct  _Forrest Gump_  node to delete
MATCH (m) WHERE m.title = 'Forrest Gump' AND labels(m) = [] RETURN m, labels(m)

### 11.12: Delete the  _Forrest Gump_  node
MATCH (m) WHERE m.title = 'Forrest Gump' AND labels(m) = [] DETACH DELETE m

### 11.13: Use  `MERGE`  to create the  _DIRECTED_  relationship
match (rz:Person), (fg:Movie)
where rz.name = 'Robert Zemeckis' and fg.title = 'Forrest Gump'
merge (rz)-[:DIRECTED]->(fg)

### 11.14: Use  `MERGE`  to create the  _ACTED_IN_  relationship
match (atores:Person), (fg:Movie {title:'Forrest Gump'})
where atores.name in ['Tom Hanks', 'Gary Sinise', 'Robin Wright']
merge (atores)-[:ACTED_IN]->(fg)
