# MongoDB

### Mauro Schramm


## Exercício 1- Aquecendo com os pets

### 1. Adicione outro Peixe e um Hamster com nome Frodo

> db.pets.insert({name: "Nemo", species: "Peixe"})

WriteResult({ "nInserted" : 1 })

> db.pets.insert({name: "Frodo", species: "Hamster"})

WriteResult({ "nInserted" : 1 })

### 2. Faça uma contagem dos pets na coleção

> db.pets.find().count()

8

### 3. Retorne apenas um elemento o método prático possível

> db.pets.findOne()

{

"_id" : ObjectId("5e88dd98cf6331795c369fc1"),

"name" : "Mike",

"species" : "Hamster"

}

### 4. Identifique o ID para o Gato Kilha.

> db.pets.find({"name":"Kilha"}, {"_id":1})

{ "_id" : ObjectId("5e88ddbacf6331795c369fc3") }

### 5. Faça uma busca pelo ID e traga o Hamster Mike

> db.pets.find({"_id":ObjectId("5e88ddbacf6331795c369fc3")})

{ "_id" : ObjectId("5e88ddbacf6331795c369fc3"), "name" : "Kilha", "species" : "Gato" }

### 6. Use o find para trazer todos os Hamsters

> db.pets.find({"species":"Hamster"})

{ "_id" : ObjectId("5e88dd98cf6331795c369fc1"), "name" : "Mike", "species" : "Hamster" }

{ "_id" : ObjectId("5e88e325cf6331795c369fc8"), "name" : "Frodo", "species" : "Hamster" }

### 7. Use o find para listar todos os pets com nome Mike

> db.pets.find({"name":"Mike"})

{ "_id" : ObjectId("5e88dd98cf6331795c369fc1"), "name" : "Mike", "species" : "Hamster" }

{ "_id" : ObjectId("5e88de65cf6331795c369fc4"), "name" : "Mike", "species" : "Cachorro" }

### 8. Liste apenas o documento que é um Cachorro chamado Mike

> db.pets.find({"name":"Mike", "species":"Cachorro"})

{ "_id" : ObjectId("5e88de65cf6331795c369fc4"), "name" : "Mike", "species" : "Cachorro" }


## Exercício 2 – Mama mia!

### 1. Liste/Conte todas as pessoas que tem exatamente 99 anos. Você pode  usar um count para indicar a quantidade.

> db.italians.find({"age":99})

> db.italians.find({"age":99}).count(0)

0

### 2. Identifique quantas pessoas são elegíveis atendimento prioritário  (pessoas com mais de 65 anos)

> db.italians.find({"age":{$gt:65}}).count(0)

1726

### 3. Identifique todos os jovens (pessoas entre 12 a 18 anos).

> db.italians.find({"age":{$gte:12, $lte:18}}).count()

884

> db.italians.find({"age":{$gte:12, $lte:18}},{"firstname":1, "surname":1})

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a92"), "firstname" : "Gianni", "surname" : "Martinelli" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa3"), "firstname" : "Giuseppe", "surname" : "Martini" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aab"), "firstname" : "Luigi", "surname" : "Bianchi" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aae"), "firstname" : "Alessandra", "surname" : "Battaglia" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8abc"), "firstname" : "Giorgio", "surname" : "Grasso" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ac7"), "firstname" : "Pasquale", "surname" : "Barbieri" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ad4"), "firstname" : "Anna", "surname" : "De Rosa" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ae1"), "firstname" : "Gianni", "surname" : "De Santis" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8afe"), "firstname" : "Anna", "surname" : "Marchetti" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b03"), "firstname" : "Paolo", "surname" : "Grasso" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b08"), "firstname" : "Federico", "surname" : "Fabbri" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b0c"), "firstname" : "Giorgia", "surname" : "Palumbo" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b16"), "firstname" : "Michela", "surname" : "Conte" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b19"), "firstname" : "Michele", "surname" : "Basile" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b26"), "firstname" : "Carlo", "surname" : "Bianco" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b2e"), "firstname" : "Chiara", "surname" : "Caruso" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b3f"), "firstname" : "Paolo", "surname" : "Donati" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b4a"), "firstname" : "Nicola", "surname" : "Farina" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b4e"), "firstname" : "Antonio", "surname" : "Mariani" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b61"), "firstname" : "Teresa", "surname" : "Valentini" }

Type "it" for more

### 4. Identifique quantas pessoas tem gatos, quantas tem cachorro e quantas  não tem nenhum dos dois

> db.italians.find({"cat":{"$exists" : true}}).count()

6025

> db.italians.find({"dog":{"$exists" : true}}).count()

4060

> db.italians.find({"dog":{"$exists" : false}, "cat":{"$exists" : false}}).count()

2419

### 5. Liste/Conte todas as pessoas acima de 60 anos que tenham gato

> db.italians.find({"cat":{"$exists" : true}, "age":{"$gt" : 60}}).count()

1460

> db.italians.find({"cat":{"$exists" : true}, "age":{"$gt" : 60}})

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa7"), "firstname" : "Alessio", "surname" : "Monti", "username" : "user131", "age" : 79, "email" : "Alessio.Monti@outlook.com", "bloodType" : "AB-", "id_num" : "140183652575", "registerDate" : ISODate("2011-11-17T01:12:58.055Z"), "ticketNumber" : 8735, "jobs" : [ "Música", "Geologia" ], "favFruits" : [ "Banana", "Kiwi", "Mamão" ], "movies" : [ { "title" : "À Espera de um Milagre (1999)", "rating" : 4.09 }, { "title" : "Clube da Luta (1999)", "rating" : 4.79 }, { "title" : "O Poderoso Chefão II (1974)", "rating" : 1.09 } ], "mother" : { "firstname" : "Mauro", "surname" : "Monti", "age" : 110 }, "cat" : { "name" : "Vincenzo", "age" : 9 }, "dog" : { "name" : "Marco", "age" : 9 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aac"), "firstname" : "Stefania", "surname" : "Carbone", "username" : "user136", "age" : 62, "email" : "Stefania.Carbone@gmail.com", "bloodType" : "A+", "id_num" : "445156713538", "registerDate" : ISODate("2012-05-20T13:19:12.614Z"), "ticketNumber" : 5214, "jobs" : [ "Gestão de Recursos Humanos" ], "favFruits" : [ "Uva", "Goiaba" ], "movies" : [ { "title" : "A Vida é Bela (1997)", "rating" : 1.61 }, { "title" : "Clube da Luta (1999)", "rating" : 4.81 }, { "title" : "Harakiri (1962)", "rating" : 2.58 } ], "cat" : { "name" : "Teresa", "age" : 5 } }

...

### 6. Liste/Conte todos os jovens com cachorro

> db.italians.find({"age":{$gte:12, $lte:18}, "dog":{$exists:true}}).count(0)

355

> db.italians.find({"age":{$gte:12, $lte:18}, "dog":{$exists:true}})

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a92"), "firstname" : "Gianni", "surname" : "Martinelli", "username" : "user110", "age" : 12, "email" : "Gianni.Martinelli@yahoo.com", "bloodType" : "AB+", "id_num" : "750364261120", "registerDate" : ISODate("2007-09-13T14:48:15.786Z"), "ticketNumber" : 3967, "jobs" : [ "Produção Cultural", "Sistemas Biomédicos" ], "favFruits" : [ "Tangerina" ], "movies" : [ { "title" : "1917 (2019)", "rating" : 3.36 }, { "title" : "Cidade de Deus (2002)", "rating" : 2.79 }, { "title" : "Forrest Gump: O Contador de Histórias (1994)", "rating" : 1.56 }, { "title" : "Interestelar (2014)", "rating" : 4.46 }, { "title" : "Forrest Gump: O Contador de Histórias (1994)", "rating" : 1.7 } ], "cat" : { "name" : "Simone", "age" : 8 }, "dog" : { "name" : "Massimiliano", "age" : 5 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aae"), "firstname" : "Alessandra", "surname" : "Battaglia", "username" : "user138", "age" : 17, "email" : "Alessandra.Battaglia@hotmail.com", "bloodType" : "AB+", "id_num" : "450707732032", "registerDate" : ISODate("2019-02-05T01:12:00.660Z"), "ticketNumber" : 5100, "jobs" : [ "Engenharia de Sistemas", "Ciência e Tecnologia" ], "favFruits" : [ "Mamão", "Mamão" ], "movies" : [ { "title" : "O Senhor dos Anéis: A Sociedade do Anel (2001)", "rating" : 4.24 } ], "cat" : { "name" : "Alex", "age" : 9 }, "dog" : { "name" : "Alessandra", "age" : 5 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8abc"), "firstname" : "Giorgio", "surname" : "Grasso", "username" : "user152", "age" : 14, "email" : "Giorgio.Grasso@uol.com.br", "bloodType" : "B+", "id_num" : "642738587133", "registerDate" : ISODate("2007-09-11T09:41:19.093Z"), "ticketNumber" : 8473, "jobs" : [ "Jogos Digitais", "Astronomia" ], "favFruits" : [ "Banana" ], "movies" : [ { "title" : "Um Estranho no Ninho (1975)", "rating" : 4.98 }, { "title" : "Batman: O Cavaleiro das Trevas (2008)", "rating" : 4.57 }, { "title" : "Um Estranho no Ninho (1975)", "rating" : 4 }, { "title" : "À Espera de um Milagre (1999)", "rating" : 0.16 } ], "cat" : { "name" : "Alex", "age" : 6 }, "dog" : { "name" : "Lorenzo", "age" : 14 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ad4"), "firstname" : "Anna", "surname" : "De Rosa", "username" : "user176", "age" : 15, "email" : "Anna.De Rosa@hotmail.com", "bloodType" : "AB-", "id_num" : "561660566514", "registerDate" : ISODate("2015-12-18T09:22:59.305Z"), "ticketNumber" : 3354, "jobs" : [ "Segurança no Trabalho", "Saúde Coletiva" ], "favFruits" : [ "Maçã", "Laranja", "Pêssego" ], "movies" : [ { "title" : "O Resgate do Soldado Ryan (1998)", "rating" : 2.53 }, { "title" : "A Viagem de Chihiro (2001)", "rating" : 1.01 } ], "dog" : { "name" : "Alessia", "age" : 4 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ae1"), "firstname" : "Gianni", "surname" : "De Santis", "username" : "user189", "age" : 17, "email" : "Gianni.De Santis@yahoo.com", "bloodType" : "A-", "id_num" : "161244404316", "registerDate" : ISODate("2009-08-27T17:00:49.343Z"), "ticketNumber" : 1561, "jobs" : [ "Ciência da Computação", "Publicidade e Propaganda" ], "favFruits" : [ "Goiaba", "Pêssego", "Melancia" ], "movies" : [ { "title" : "A Vida é Bela (1997)", "rating" : 0.52 }, { "title" : "Três Homens em Conflito (1966)", "rating" : 4.32 }, { "title" : "A Origem (2010)", "rating" : 4.25 } ], "father" : { "firstname" : "Alessandra", "surname" : "De Santis", "age" : 34 }, "cat" : { "name" : "Silvia", "age" : 17 }, "dog" : { "name" : "Silvia", "age" : 7 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8afe"), "firstname" : "Anna", "surname" : "Marchetti", "username" : "user218", "age" : 17, "email" : "Anna.Marchetti@live.com", "bloodType" : "A-", "id_num" : "308562032581", "registerDate" : ISODate("2017-09-02T07:45:19.665Z"), "ticketNumber" : 3149, "jobs" : [ "Biotecnologia e Bioquímica" ], "favFruits" : [ "Laranja", "Tangerina", "Laranja" ], "movies" : [ { "title" : "Guerra nas Estrelas (1977)", "rating" : 4.01 }, { "title" : "Parasita (2019)", "rating" : 1.65 } ], "cat" : { "name" : "Alessandra", "age" : 13 }, "dog" : { "name" : "Patrizia", "age" : 10 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b0c"), "firstname" : "Giorgia", "surname" : "Palumbo", "username" : "user232", "age" : 18, "email" : "Giorgia.Palumbo@hotmail.com", "bloodType" : "B-", "id_num" : "408523836308", "registerDate" : ISODate("2014-04-21T04:28:17.805Z"), "ticketNumber" : 7384, "jobs" : [ "Biblioteconomia" ], "favFruits" : [ "Melancia", "Mamão", "Pêssego" ], "movies" : [ { "title" : "Um Estranho no Ninho (1975)", "rating" : 1.7 } ], "cat" : { "name" : "Mattia", "age" : 6 }, "dog" : { "name" : "Giacomo", "age" : 3 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b4a"), "firstname" : "Nicola", "surname" : "Farina", "username" : "user294", "age" : 14, "email" : "Nicola.Farina@uol.com.br", "bloodType" : "B-", "id_num" : "863752026863", "registerDate" : ISODate("2014-08-09T11:40:53.160Z"), "ticketNumber" : 9488, "jobs" : [ "Oceanografia", "História" ], "favFruits" : [ "Maçã" ], "movies" : [ { "title" : "Os Sete Samurais (1954)", "rating" : 4.46 }, { "title" : "O Resgate do Soldado Ryan (1998)", "rating" : 1.38 } ], "mother" : { "firstname" : "Angelo", "surname" : "Farina", "age" : 29 }, "cat" : { "name" : "Maurizio", "age" : 6 }, "dog" : { "name" : "Nicola", "age" : 5 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8bc1"), "firstname" : "Cinzia", "surname" : "Gentile", "username" : "user413", "age" : 14, "email" : "Cinzia.Gentile@yahoo.com", "bloodType" : "O-", "id_num" : "245713148532", "registerDate" : ISODate("2017-11-04T14:24:47.698Z"), "ticketNumber" : 3872, "jobs" : [ "Design de Moda", "Jornalismo" ], "favFruits" : [ "Kiwi", "Melancia" ], "movies" : [ { "title" : "A Viagem de Chihiro (2001)", "rating" : 4.94 }, { "title" : "Coringa (2019)", "rating" : 1.67 }, { "title" : "O Poderoso Chefão (1972)", "rating" : 4.37 } ], "cat" : { "name" : "Fabio", "age" : 8 }, "dog" : { "name" : "Giacomo", "age" : 13 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8bea"), "firstname" : "Matteo", "surname" : "D’Amico", "username" : "user454", "age" : 13, "email" : "Matteo.D’Amico@hotmail.com", "bloodType" : "A-", "id_num" : "123623271214", "registerDate" : ISODate("2013-11-25T04:18:25.341Z"), "ticketNumber" : 8478, "jobs" : [ "Engenharia de Telecomunicações", "Gestão de Recursos Humanos" ], "favFruits" : [ "Kiwi" ], "movies" : [ { "title" : "À Espera de um Milagre (1999)", "rating" : 2.73 } ], "dog" : { "name" : "Paolo", "age" : 2 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8bf8"), "firstname" : "Antonella", "surname" : "Negri", "username" : "user468", "age" : 18, "email" : "Antonella.Negri@uol.com.br", "bloodType" : "AB-", "id_num" : "525235513732", "registerDate" : ISODate("2008-05-20T18:35:45.652Z"), "ticketNumber" : 9288, "jobs" : [ "Produção Têxtil" ], "favFruits" : [ "Mamão", "Maçã" ], "movies" : [ { "title" : "O Poderoso Chefão (1972)", "rating" : 3.29 }, { "title" : "A Felicidade Não se Compra (1946)", "rating" : 0.33 } ], "dog" : { "name" : "Fabrizio", "age" : 13 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c00"), "firstname" : "Mario", "surname" : "Amato", "username" : "user476", "age" : 13, "email" : "Mario.Amato@outlook.com", "bloodType" : "A+", "id_num" : "826015160053", "registerDate" : ISODate("2011-07-22T14:56:35.612Z"), "ticketNumber" : 2340, "jobs" : [ "Fonoaudiologia", "Ciências Contábeis" ], "favFruits" : [ "Kiwi", "Tangerina" ], "movies" : [ { "title" : "Clube da Luta (1999)", "rating" : 3.87 } ], "mother" : { "firstname" : "Luigi", "surname" : "Amato", "age" : 43 }, "cat" : { "name" : "Claudia", "age" : 6 }, "dog" : { "name" : "Stefano", "age" : 13 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c05"), "firstname" : "Alessio", "surname" : "Ferri", "username" : "user481", "age" : 15, "email" : "Alessio.Ferri@gmail.com", "bloodType" : "O-", "id_num" : "114474504226", "registerDate" : ISODate("2009-05-10T04:10:58.252Z"), "ticketNumber" : 1624, "jobs" : [ "Agrimensura" ], "favFruits" : [ "Maçã" ], "movies" : [ { "title" : "Um Sonho de Liberdade (1994)", "rating" : 0.78 }, { "title" : "Os Sete Samurais (1954)", "rating" : 2.91 }, { "title" : "Cidade de Deus (2002)", "rating" : 3.64 } ], "father" : { "firstname" : "Elisa", "surname" : "Ferri", "age" : 49 }, "dog" : { "name" : "Luigi", "age" : 0 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c33"), "firstname" : "Elisabetta", "surname" : "Conte", "username" : "user527", "age" : 18, "email" : "Elisabetta.Conte@outlook.com", "bloodType" : "B+", "id_num" : "650762707884", "registerDate" : ISODate("2015-03-18T09:24:05.734Z"), "ticketNumber" : 4581, "jobs" : [ "Jornalismo" ], "favFruits" : [ "Uva", "Laranja" ], "movies" : [ { "title" : "Star Wars, Episódio V: O Império Contra-Ataca (1980)", "rating" : 3 }, { "title" : "O Poderoso Chefão (1972)", "rating" : 2.4 } ], "cat" : { "name" : "Gianluca", "age" : 16 }, "dog" : { "name" : "Gianluca", "age" : 8 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c84"), "firstname" : "Mario", "surname" : "Rizzo", "username" : "user608", "age" : 18, "email" : "Mario.Rizzo@gmail.com", "bloodType" : "B+", "id_num" : "715108675561", "registerDate" : ISODate("2020-02-07T06:09:46.483Z"), "ticketNumber" : 3621, "jobs" : [ "Produção Multimídia", "Rochas Ornamentais" ], "favFruits" : [ "Uva" ], "movies" : [ { "title" : "Intocáveis (2011)", "rating" : 1.79 }, { "title" : "O Senhor dos Anéis: As Duas Torres (2002)", "rating" : 3.9 }, { "title" : "A Origem (2010)", "rating" : 0.65 }, { "title" : "O Poderoso Chefão II (1974)", "rating" : 2.19 } ], "cat" : { "name" : "Giovanni", "age" : 13 }, "dog" : { "name" : "Massimiliano", "age" : 1 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8cbe"), "firstname" : "Alberto", "surname" : "Bernardi", "username" : "user666", "age" : 13, "email" : "Alberto.Bernardi@yahoo.com", "bloodType" : "O+", "id_num" : "533858530202", "registerDate" : ISODate("2018-07-03T01:51:21.714Z"), "ticketNumber" : 6103, "jobs" : [ "Estudos de Gênero e Diversidade", "Saneamento Ambiental" ], "favFruits" : [ "Pêssego" ], "movies" : [ { "title" : "Gladiador (2000)", "rating" : 0.83 }, { "title" : "Clube da Luta (1999)", "rating" : 4.41 }, { "title" : "Star Wars, Episódio V: O Império Contra-Ataca (1980)", "rating" : 0.09 }, { "title" : "Star Wars, Episódio V: O Império Contra-Ataca (1980)", "rating" : 3.28 } ], "cat" : { "name" : "Matteo", "age" : 17 }, "dog" : { "name" : "Giulia", "age" : 7 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8ce2"), "firstname" : "Enzo ", "surname" : "Basile", "username" : "user702", "age" : 17, "email" : "Enzo .Basile@hotmail.com", "bloodType" : "O-", "id_num" : "278636837471", "registerDate" : ISODate("2010-09-01T04:29:46.234Z"), "ticketNumber" : 6361, "jobs" : [ "Computação", "Construção Naval" ], "favFruits" : [ "Uva", "Goiaba" ], "movies" : [ { "title" : "O Silêncio dos Inocentes (1991)", "rating" : 4.79 }, { "title" : "O Senhor dos Anéis: As Duas Torres (2002)", "rating" : 4.81 }, { "title" : "O Senhor dos Anéis: O Retorno do Rei (2003)", "rating" : 2.61 }, { "title" : "Coringa (2019)", "rating" : 1.42 } ], "cat" : { "name" : "Giovanna", "age" : 17 }, "dog" : { "name" : "Eleonora", "age" : 6 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8cf9"), "firstname" : "Elisabetta", "surname" : "Pellegrini", "username" : "user725", "age" : 13, "email" : "Elisabetta.Pellegrini@live.com", "bloodType" : "AB-", "id_num" : "080481708176", "registerDate" : ISODate("2016-04-01T05:38:16.849Z"), "ticketNumber" : 6390, "jobs" : [ "Engenharia Ambiental e Sanitária", "Linguística" ], "favFruits" : [ "Laranja", "Melancia", "Banana" ], "movies" : [ { "title" : "Parasita (2019)", "rating" : 0.74 }, { "title" : "O Senhor dos Anéis: As Duas Torres (2002)", "rating" : 0.82 }, { "title" : "Coringa (2019)", "rating" : 1.03 }, { "title" : "Matrix (1999)", "rating" : 1.53 }, { "title" : "Seven: Os Sete Crimes Capitais (1995)", "rating" : 2.92 } ], "dog" : { "name" : "Carlo", "age" : 6 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d1c"), "firstname" : "Claudio", "surname" : "Marini", "username" : "user760", "age" : 14, "email" : "Claudio.Marini@yahoo.com", "bloodType" : "A+", "id_num" : "656745482836", "registerDate" : ISODate("2011-11-23T01:52:38.208Z"), "ticketNumber" : 6163, "jobs" : [ "Gestão de Segurança Privada", "Engenharia de Produção" ], "favFruits" : [ "Laranja", "Goiaba", "Goiaba" ], "movies" : [ { "title" : "Um Sonho de Liberdade (1994)", "rating" : 3.82 }, { "title" : "Harakiri (1962)", "rating" : 3.3 }, { "title" : "Um Sonho de Liberdade (1994)", "rating" : 3.87 }, { "title" : "Gladiador (2000)", "rating" : 3.2 } ], "dog" : { "name" : "Elisabetta", "age" : 4 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d2c"), "firstname" : "Matteo", "surname" : "Grasso", "username" : "user776", "age" : 13, "email" : "Matteo.Grasso@gmail.com", "bloodType" : "AB+", "id_num" : "687613254261", "registerDate" : ISODate("2008-01-22T19:37:47.585Z"), "ticketNumber" : 7711, "jobs" : [ "Teologia" ], "favFruits" : [ "Uva", "Goiaba" ], "movies" : [ { "title" : "Intocáveis (2011)", "rating" : 1.72 }, { "title" : "Três Homens em Conflito (1966)", "rating" : 3.72 }, { "title" : "A Viagem de Chihiro (2001)", "rating" : 4.81 } ], "dog" : { "name" : "Domenico", "age" : 4 } }

Type "it" for more

### 7. Utilizando o $where, liste todas as pessoas que tem gato e cachorro

???

### 8. Liste todas as pessoas mais novas que seus respectivos gatos.

> db.italians.find({"$expr" : {"$lt" : ["$age", "$cat.age"]}}, {"firstname":1, "age":1, "cat.age":1})

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a8c"), "firstname" : "Patrizia", "age" : 7, "cat" : { "age" : 12 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8a9b"), "firstname" : "Elisa", "age" : 3, "cat" : { "age" : 15 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa1"), "firstname" : "Alessandra", "age" : 3, "cat" : { "age" : 6 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab1"), "firstname" : "Laura", "age" : 0, "cat" : { "age" : 2 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab5"), "firstname" : "Fabrizio", "age" : 3, "cat" : { "age" : 11 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ac9"), "firstname" : "Davide", "age" : 0, "cat" : { "age" : 10 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ace"), "firstname" : "Davide", "age" : 7, "cat" : { "age" : 16 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b16"), "firstname" : "Michela", "age" : 13, "cat" : { "age" : 14 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b24"), "firstname" : "Daniele", "age" : 7, "cat" : { "age" : 10 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b2d"), "firstname" : "Giorgia", "age" : 3, "cat" : { "age" : 15 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b30"), "firstname" : "Valentina", "age" : 1, "cat" : { "age" : 2 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b31"), "firstname" : "Martina", "age" : 4, "cat" : { "age" : 14 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b38"), "firstname" : "Massimiliano", "age" : 7, "cat" : { "age" : 17 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b3d"), "firstname" : "Claudio", "age" : 0, "cat" : { "age" : 16 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b3f"), "firstname" : "Paolo", "age" : 13, "cat" : { "age" : 15 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b44"), "firstname" : "Roberto", "age" : 0, "cat" : { "age" : 14 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b4f"), "firstname" : "Giacomo", "age" : 1, "cat" : { "age" : 5 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b5d"), "firstname" : "Angelo", "age" : 7, "cat" : { "age" : 15 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b64"), "firstname" : "Gianluca", "age" : 3, "cat" : { "age" : 16 } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b70"), "firstname" : "Alessandra", "age" : 9, "cat" : { "age" : 17 } }

Type "it" for more

### 9. Liste as pessoas que tem o mesmo nome que seu bichano (gatou ou  cachorro)

> db.italians.find( {$or: [ {"$expr" : {"$eq" : ["$firstname", "$cat.name"]}}, {"$expr" : {"$eq" : ["$firstname", "$dog.name"]}} ]}, {"firstname":1, "cat.name":1, "dog.name":1})

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a8c"), "firstname" : "Patrizia", "cat" : { "name" : "Patrizia" }, "dog" : { "name" : "Cristian" } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aae"), "firstname" : "Alessandra", "cat" : { "name" : "Alex" }, "dog" : { "name" : "Alessandra" } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ad0"), "firstname" : "Laura", "cat" : { "name" : "Tiziana" }, "dog" : { "name" : "Laura" } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b1f"), "firstname" : "Matteo", "cat" : { "name" : "Matteo" }, "dog" : { "name" : "Gianni" } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b39"), "firstname" : "Monica", "cat" : { "name" : "Michele" }, "dog" : { "name" : "Monica" } }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8b4a"), "firstname" : "Nicola", "cat" : { "name" : "Maurizio" }, "dog" : { "name" : "Nicola" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8b9d"), "firstname" : "Carlo", "dog" : { "name" : "Carlo" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8bba"), "firstname" : "Patrizia", "cat" : { "name" : "Patrizia" }, "dog" : { "name" : "Giovanna" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c20"), "firstname" : "Daniele", "cat" : { "name" : "Daniele" }, "dog" : { "name" : "Simone" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c31"), "firstname" : "Emanuele", "dog" : { "name" : "Emanuele" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8ca9"), "firstname" : "Tiziana", "cat" : { "name" : "Tiziana" }, "dog" : { "name" : "Cristian" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8cb5"), "firstname" : "Maurizio", "cat" : { "name" : "Maurizio" }, "dog" : { "name" : "Pasquale" } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8ce1"), "firstname" : "Raffaele", "cat" : { "name" : "Raffaele" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8cfd"), "firstname" : "Patrizia", "cat" : { "name" : "Patrizia" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d13"), "firstname" : "Elisabetta", "cat" : { "name" : "Federica" }, "dog" : { "name" : "Elisabetta" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d37"), "firstname" : "Gianluca", "cat" : { "name" : "Gianluca" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d4b"), "firstname" : "Sabrina", "cat" : { "name" : "Sabrina" }, "dog" : { "name" : "Mirko" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d4f"), "firstname" : "Raffaele", "cat" : { "name" : "Raffaele" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8df1"), "firstname" : "Rita", "cat" : { "name" : "Rita" } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8e10"), "firstname" : "Riccardo", "cat" : { "name" : "Giovanna" }, "dog" : { "name" : "Riccardo" } }

Type "it" for more

### 10. Projete apenas o nome e sobrenome das pessoas com tipo de sangue de  fator RH negativo

> db.italians.find( { "bloodType" : /-/}, {"firstname":1, "surname":1})

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a8d"), "firstname" : "Antonio", "surname" : "Barbieri" }

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a8f"), "firstname" : "Fabio", "surname" : "Silvestri" }

{ "_id" : ObjectId("5e88f6bf713dadabb4da8a94"), "firstname" : "Alex", "surname" : "Benedetti" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8a99"), "firstname" : "Mauro", "surname" : "Rinaldi" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8a9c"), "firstname" : "Simone", "surname" : "Moretti" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8a9e"), "firstname" : "Emanuele", "surname" : "Serra" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa0"), "firstname" : "Manuela", "surname" : "Grassi" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa1"), "firstname" : "Alessandra", "surname" : "Ferri" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa2"), "firstname" : "Giulia", "surname" : "Galli" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa3"), "firstname" : "Giuseppe", "surname" : "Martini" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa5"), "firstname" : "Giacomo", "surname" : "Ferretti" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa6"), "firstname" : "Vincenzo", "surname" : "Pagano" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aa7"), "firstname" : "Alessio", "surname" : "Monti" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aab"), "firstname" : "Luigi", "surname" : "Bianchi" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8aad"), "firstname" : "Gabiele", "surname" : "De Angelis" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab0"), "firstname" : "Filipo", "surname" : "Lombardi" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab1"), "firstname" : "Laura", "surname" : "Fiore" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab3"), "firstname" : "Riccardo", "surname" : "Bruno" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab6"), "firstname" : "Marta", "surname" : "Piras" }

{ "_id" : ObjectId("5e88f6c0713dadabb4da8ab7"), "firstname" : "Silvia", "surname" : "Battaglia" }

Type "it" for more

### 11. Projete apenas os animais dos italianos. Devem ser listados os animais  com nome e idade. Não mostre o identificado do mongo (ObjectId)

> db.italians.find( {$or : [ {cat : {$exists : true} }, {dog : {$exists : true}} ] } , {"cat.name":1, "cat.age":1, "dog.name":1, "dog.age":1, "_id":0 })

{ "dog" : { "name" : "Emanuele", "age" : 11 } }

{ "cat" : { "name" : "Patrizia", "age" : 12 }, "dog" : { "name" : "Cristian", "age" : 16 } }

{ "dog" : { "name" : "Filipo", "age" : 2 } }

{ "dog" : { "name" : "Massimo", "age" : 1 } }

{ "cat" : { "name" : "Chiara", "age" : 5 } }

{ "dog" : { "name" : "Emanuela", "age" : 6 } }

{ "cat" : { "name" : "Simone", "age" : 8 }, "dog" : { "name" : "Massimiliano", "age" : 5 } }

{ "cat" : { "name" : "Fabio", "age" : 2 }, "dog" : { "name" : "Sara", "age" : 9 } }

{ "cat" : { "name" : "Matteo", "age" : 4 }, "dog" : { "name" : "Alessandro", "age" : 15 } }

{ "cat" : { "name" : "Stefano", "age" : 9 }, "dog" : { "name" : "Luigi", "age" : 2 } }

{ "cat" : { "name" : "Giusy", "age" : 6 } }

{ "cat" : { "name" : "Martina", "age" : 11 }, "dog" : { "name" : "Massimiliano", "age" : 5 } }

{ "dog" : { "name" : "Fabrizio", "age" : 0 } }

{ "cat" : { "name" : "Patrizia", "age" : 10 }, "dog" : { "name" : "Barbara", "age" : 0 } }

{ "cat" : { "name" : "Alessio", "age" : 13 } }

{ "cat" : { "name" : "Silvia", "age" : 15 } }

{ "cat" : { "name" : "Emanuele", "age" : 10 }, "dog" : { "name" : "Gianni", "age" : 6 } }

{ "cat" : { "name" : "Davide", "age" : 17 } }

{ "cat" : { "name" : "Massimo", "age" : 14 } }

{ "cat" : { "name" : "Chiara", "age" : 15 } }

Type "it" for more

### 12. Quais são as 5 pessoas mais velhas com sobrenome Rossi?

> db.italians.find( {"surname" : "Rossi"}, {"firstname":1, "surname":1, "age":1 }).sort({"age" : -1}).limit(5)

{ "_id" : ObjectId("5e88f6c3713dadabb4da8ec6"), "firstname" : "Antonella", "surname" : "Rossi", "age" : 79 }

{ "_id" : ObjectId("5e88f6c5713dadabb4da9198"), "firstname" : "Rosa", "surname" : "Rossi", "age" : 78 }

{ "_id" : ObjectId("5e88f6d3713dadabb4daa6f4"), "firstname" : "Laura", "surname" : "Rossi", "age" : 78 }

{ "_id" : ObjectId("5e88f6d5713dadabb4daaa20"), "firstname" : "Filipo", "surname" : "Rossi", "age" : 77 }

{ "_id" : ObjectId("5e88f6c8713dadabb4da960c"), "firstname" : "Monica", "surname" : "Rossi", "age" : 76 }

### 13. Crie um italiano que tenha um leão como animal de estimação. Associe  um nome e idade ao bichano

> db.italians.insertOne ( {"firstname" : "Enzo", "surname" : "Ferrari", "lion" : { "name" : "Simba", "age" : 10 }} )

{

"acknowledged" : true,

"insertedId" : ObjectId("5e8be97c27de93302734b8b1")

}

### 14. Infelizmente o Leão comeu o italiano. Remova essa pessoa usando o Id.

> db.italians.remove( {"_id" :  ObjectId("5e8be97c27de93302734b8b1")})

WriteResult({ "nRemoved" : 1 })

### 15. Passou um ano. Atualize a idade de todos os italianos e dos bichanos em  1

> db.italians.update( {"age" : {$exists : true }}, {"$inc" : {"age" : 1}}, {multi : true})

WriteResult({ "nMatched" : 10002, "nUpserted" : 0, "nModified" : 10002 })

>

> db.italians.update( {"cat.age" : {$exists : true }}, {"$inc" : {"cat.age" : 1}}, {multi : true})

WriteResult({ "nMatched" : 6027, "nUpserted" : 0, "nModified" : 6027 })

>

> db.italians.update( {"dog.age" : {$exists : true }}, {"$inc" : {"dog.age" : 1}}, {multi : true})

WriteResult({ "nMatched" : 4062, "nUpserted" : 0, "nModified" : 4062 })

### 16. O Corona Vírus chegou na Itália e misteriosamente atingiu pessoas  somente com gatos e de 66 anos. Remova esses italianos.

> db.italians.remove( {$and : [{"age" : 66 }, {"cat" : {$exists : true}} ]})

WriteResult({ "nRemoved" : 97 })

### 17. Utilizando o framework agregate, liste apenas as pessoas com nomes  iguais a sua respectiva mãe e que tenha gato ou cachorro.

> db.italians.aggregate( [ { $match: { $or: [ { cat: {$exists: true} }, { dog: {$exists: true} } ] } }, { $project: {firstname: 1, mother: 1, cat: 1, dog: 1, "isEqual": { "$cmp": ["$firstname", "$mother.firstname"]} } }, {"$match": {"isEqual": 0}} ])

{ "_id" : ObjectId("5e88f6c4713dadabb4da9049"), "firstname" : "Raffaele", "mother" : { "firstname" : "Raffaele", "surname" : "Ferretti", "age" : 85 }, "cat" : { "name" : "Cristian", "age" : 3 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c4713dadabb4da9094"), "firstname" : "Giovanni", "mother" : { "firstname" : "Giovanni", "surname" : "Mazza", "age" : 33 }, "cat" : { "name" : "Raffaele", "age" : 8 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c4713dadabb4da9124"), "firstname" : "Filipo", "mother" : { "firstname" : "Filipo", "surname" : "Coppola", "age" : 34 }, "cat" : { "name" : "Elisa", "age" : 12 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c5713dadabb4da92cb"), "firstname" : "Emanuela", "mother" : { "firstname" : "Emanuela", "surname" : "Martino", "age" : 61 }, "cat" : { "name" : "Sabrina", "age" : 12 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c6713dadabb4da93fd"), "firstname" : "Antonella", "mother" : { "firstname" : "Antonella", "surname" : "Gentile", "age" : 72 }, "cat" : { "name" : "Giorgio", "age" : 9 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c7713dadabb4da95cb"), "firstname" : "Lucia", "mother" : { "firstname" : "Lucia", "surname" : "Palmieri", "age" : 88 }, "cat" : { "name" : "Daniele", "age" : 13 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c9713dadabb4da976b"), "firstname" : "Cristian", "mother" : { "firstname" : "Cristian", "surname" : "Villa", "age" : 57 }, "cat" : { "name" : "Luigi", "age" : 12 }, "dog" : { "name" : "Claudio", "age" : 9 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6c9713dadabb4da9866"), "firstname" : "Vincenzo", "mother" : { "firstname" : "Vincenzo", "surname" : "Conti", "age" : 66 }, "cat" : { "name" : "Rita", "age" : 11 }, "dog" : { "name" : "Alessio", "age" : 6 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6cb713dadabb4da9bff"), "firstname" : "Gianluca", "mother" : { "firstname" : "Gianluca", "surname" : "Leone", "age" : 30 }, "dog" : { "name" : "Emanuela", "age" : 1 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6cc713dadabb4da9d02"), "firstname" : "Domenico", "mother" : { "firstname" : "Domenico", "surname" : "Santoro", "age" : 34 }, "cat" : { "name" : "Antonio", "age" : 2 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6cf713dadabb4daa202"), "firstname" : "Stefano", "mother" : { "firstname" : "Stefano", "surname" : "Sanna", "age" : 88 }, "cat" : { "name" : "Michele", "age" : 7 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6d5713dadabb4daaa24"), "firstname" : "Federica", "mother" : { "firstname" : "Federica", "surname" : "Carbone", "age" : 90 }, "cat" : { "name" : "Enzo ", "age" : 7 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6d5713dadabb4daaa44"), "firstname" : "Riccardo", "mother" : { "firstname" : "Riccardo", "surname" : "Battaglia", "age" : 21 }, "cat" : { "name" : "Davide", "age" : 1 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6d7713dadabb4daae62"), "firstname" : "Emanuele", "mother" : { "firstname" : "Emanuele", "surname" : "Milani", "age" : 78 }, "cat" : { "name" : "Rosa", "age" : 7 }, "dog" : { "name" : "Cristina", "age" : 9 }, "isEqual" : 0 }

{ "_id" : ObjectId("5e88f6d7713dadabb4daae68"), "firstname" : "Daniele", "mother" : { "firstname" : "Daniele", "surname" : "D’Angelo", "age" : 45 }, "cat" : { "name" : "Elena", "age" : 4 }, "dog" : { "name" : "Lorenzo", "age" : 12 }, "isEqual" : 0 }

### 18. Utilizando aggregate framework, faça uma lista de nomes única de  nomes. Faça isso usando apenas o primeiro nome

> db.italians.aggregate( [ {$group: {_id: "$firstname" } }, {$sort: {_id:1}} ])

{ "_id" : "Alberto" }

{ "_id" : "Alessandra" }

{ "_id" : "Alessandro" }

{ "_id" : "Alessia" }

{ "_id" : "Alessio" }

{ "_id" : "Alex" }

{ "_id" : "Andrea" }

{ "_id" : "Angela" }

{ "_id" : "Angelo" }

{ "_id" : "Anna" }

{ "_id" : "Antonella" }

{ "_id" : "Antonio" }

{ "_id" : "Barbara" }

{ "_id" : "Carlo" }

{ "_id" : "Chiara" }

{ "_id" : "Cinzia" }

{ "_id" : "Claudia" }

{ "_id" : "Claudio" }

{ "_id" : "Cristian" }

{ "_id" : "Cristina" }

Type "it" for more

### 19. Agora faça a mesma lista do item acima, considerando nome completo.

> db.italians.aggregate( [ {$group: {_id: {firstname:"$firstname", surname:"$surname" } } }, {$sort: { _id:1 } } ])

{ "_id" : { "firstname" : "Alberto", "surname" : "Amato" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Barbieri" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Barone" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Basile" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Battaglia" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Bellini" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Benedetti" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Bernardi" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Bianchi" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Bruno" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Caputo" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Carbone" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Cattaneo" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Conte" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Coppola" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Costa" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Costatini" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "Damico" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "De Angelis" } }

{ "_id" : { "firstname" : "Alberto", "surname" : "D’Amico" } }

Type "it" for more

### 20. Procure pessoas que gosta de Banana ou Maçã, tenham cachorro ou gato,  mais de 20 e menos de 60 anos.

> db.italians.find( { $and: [ {$or: [ { cat: {$exists: true}  }, { dog: {$exists: true} } ]}, {age: {$in: [20,60]}}, {$or: [{favFruits: "Banana"}, {favFruits: "Maçã"}]}]} , {"firstname":1, age:1, cat:1, dog:1, favFruits:1 })

{ "_id" : { "_id" : ObjectId("5e88f6c1713dadabb4da8b92"), "firstname" : "Alessia", "age" : 60, "favFruits" : [ "Banana", "Tangerina" ], "cat" : { "name" : "Anna", "age" : 8 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8c5d"), "firstname" : "Alessandra", "age" : 20, "favFruits" : [ "Melancia", "Maçã" ], "cat" : { "name" : "Massimo", "age" : 8 } }

{ "_id" : ObjectId("5e88f6c1713dadabb4da8ca3"), "firstname" : "Roberto", "age" : 60, "favFruits" : [ "Banana" ], "cat" : { "name" : "Elisa", "age" : 1 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d33"), "firstname" : "Angelo", "age" : 60, "favFruits" : [ "Banana", "Uva", "Goiaba" ], "cat" : { "name" : "Giacomo", "age" : 1 }, "dog" : { "name" : "Pietro", "age" : 1 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8d84"), "firstname" : "Giovanna", "age" : 20, "favFruits" : [ "Kiwi", "Kiwi", "Maçã" ], "cat" : { "name" : "Massimo", "age" : 12 }, "dog" : { "name" : "Giuseppe", "age" : 4 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8dc3"), "firstname" : "Pietro", "age" : 20, "favFruits" : [ "Pêssego", "Banana", "Goiaba" ], "cat" : { "name" : "Elena", "age" : 8 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8e09"), "firstname" : "Maurizio", "age" : 20, "favFruits" : [ "Maçã", "Kiwi", "Uva" ], "cat" : { "name" : "Elena", "age" : 3 } }

{ "_id" : ObjectId("5e88f6c2713dadabb4da8e1b"), "firstname" : "Salvatore", "age" : 60, "favFruits" : [ "Banana", "Uva" ], "cat" : { "name" : "Valentina", "age" : 11 }, "dog" : { "name" : "Cinzia", "age" : 6 } }

{ "_id" : ObjectId("5e88f6c3713dadabb4da8eb7"), "firstname" : "Mirko", "age" : 60, "favFruits" : [ "Banana", "Laranja", "Maçã" ], "cat" : { "name" : "Giovanna", "age" : 12 }, "dog" : { "name" : "Maria", "age" : 7 } }

{ "_id" : ObjectId("5e88f6c3713dadabb4da8ee2"), "firstname" : "Lucia", "age" : 20, "favFruits" : [ "Maçã" ], "dog" : { "name" : "Gianni", "age" : 10 } }

{ "_id" : ObjectId("5e88f6c3713dadabb4da8f1a"), "firstname" : "Rita", "age" : 20, "favFruits" : [ "Banana", "Mamão", "Kiwi" ], "cat" : { "name" : "Giovanna", "age" : 10 } }

{ "_id" : ObjectId("5e88f6c4713dadabb4da9010"), "firstname" : "Giovanna", "age" : 20, "favFruits" : [ "Uva", "Maçã", "Melancia" ], "cat" : { "name" : "Pietro", "age" : 1 } }

{ "_id" : ObjectId("5e88f6c4713dadabb4da9041"), "firstname" : "Nicola", "age" : 20, "favFruits" : [ "Banana" ], "cat" : { "name" : "Domenico", "age" : 5 } }

{ "_id" : ObjectId("5e88f6c4713dadabb4da90d1"), "firstname" : "Silvia", "age" : 20, "favFruits" : [ "Maçã" ], "cat" : { "name" : "Eleonora", "age" : 11 } }

{ "_id" : ObjectId("5e88f6c5713dadabb4da9194"), "firstname" : "Alex", "age" : 60, "favFruits" : [ "Banana", "Laranja" ], "cat" : { "name" : "Marco", "age" : 1 } }

{ "_id" : ObjectId("5e88f6c5713dadabb4da91d3"), "firstname" : "Ilaria", "age" : 60, "favFruits" : [ "Uva", "Maçã" ], "cat" : { "name" : "Enzo ", "age" : 4 } }

{ "_id" : ObjectId("5e88f6c5713dadabb4da924c"), "firstname" : "Andrea", "age" : 20, "favFruits" : [ "Banana" ], "dog" : { "name" : "Daniela", "age" : 13 } }

{ "_id" : ObjectId("5e88f6c5713dadabb4da9275"), "firstname" : "Antonella", "age" : 20, "favFruits" : [ "Kiwi", "Banana", "Melancia" ], "cat" : { "name" : "Emanuela", "age" : 3 }, "dog" : { "name" : "Maurizio", "age" : 11 } }

{ "_id" : ObjectId("5e88f6c5713dadabb4da9295"), "firstname" : "Mattia", "age" : 20, "favFruits" : [ "Banana" ], "cat" : { "name" : "Cristina", "age" : 9 }, "dog" : { "name" : "Stefania", "age" : 4 } }

{ "_id" : ObjectId("5e88f6c6713dadabb4da93a9"), "firstname" : "Matteo", "age" : 20, "favFruits" : [ "Banana", "Uva", "Kiwi" ], "cat" : { "name" : "Lorenzo", "age" : 17 } }

Type "it" for more

## Exercício 3 - Stockbrokers

### 1. Liste as ações com profit acima de 0.5 (limite a 10 o resultado)

> db.stocks.find ( {"Profit Margin": {$gt: 0.5}} ).limit(10)

{ "_id" : ObjectId("52853800bb1177ca391c180f"), "Ticker" : "AB", "Profit Margin" : 0.896, "Institutional Ownership" : 0.368, "EPS growth past 5 years" : -0.348, "Total Debt/Equity" : 0, "Return on Assets" : 0.086, "Sector" : "Financial", "P/S" : 13.25, "Change from Open" : 0.0047, "Performance (YTD)" : 0.3227, "Performance (Week)" : -0.0302, "Insider Transactions" : 0.5973, "P/B" : 1.4, "EPS growth quarter over quarter" : 2.391, "Payout Ratio" : 1.75, "Performance (Quarter)" : 0.0929, "Forward P/E" : 12.58, "P/E" : 15.82, "200-Day Simple Moving Average" : 0.0159, "Shares Outstanding" : 92.26, "Earnings Date" : ISODate("2013-10-24T12:30:00Z"), "52-Week High" : -0.1859, "Change" : -0.0009, "Analyst Recom" : 3, "Volatility (Week)" : 0.0264, "Country" : "USA", "Return on Equity" : 0.087, "50-Day Low" : 0.123, "Price" : 21.5, "50-Day High" : -0.0574, "Return on Investment" : 0.033, "Shares Float" : 86.66, "Dividend Yield" : 0.0743, "EPS growth next 5 years" : 0.08, "Industry" : "Asset Management", "Beta" : 1.63, "Operating Margin" : 1, "EPS (ttm)" : 1.36, "PEG" : 1.98, "Float Short" : 0.0253, "52-Week Low" : 0.4687, "Average True Range" : 0.59, "EPS growth next year" : 0.0654, "Sales growth past 5 years" : -0.298, "Company" : "AllianceBernstein Holding L.P.", "Gap" : -0.0056, "Relative Volume" : 0.63, "Volatility (Month)" : 0.0298, "Market Cap" : 1985.39, "Volume" : 199677, "Short Ratio" : 6.3, "Performance (Half Year)" : -0.1159, "Relative Strength Index (14)" : 50.05, "Insider Ownership" : 0.002, "20-Day Simple Moving Average" : -0.007, "Performance (Month)" : 0.0847, "P/Free Cash Flow" : 93.21, "Institutional Transactions" : 0.0818, "Performance (Year)" : 0.3884, "LT Debt/Equity" : 0, "Average Volume" : 348.08, "EPS growth this year" : 1.567, "50-Day Simple Moving Average" : 0.0458 }

{ "_id" : ObjectId("52853801bb1177ca391c1895"), "Ticker" : "AGNC", "Profit Margin" : 0.972, "Institutional Ownership" : 0.481, "EPS growth past 5 years" : -0.0107, "Total Debt/Equity" : 8.56, "Return on Assets" : 0.022, "Sector" : "Financial", "P/S" : 3.77, "Change from Open" : 0.0102, "Performance (YTD)" : -0.1652, "Performance (Week)" : -0.017, "Insider Transactions" : 0.4931, "P/B" : 0.86, "EPS growth quarter over quarter" : -8.2, "Payout Ratio" : 0.79, "Performance (Quarter)" : -0.0083, "Forward P/E" : 7.64, "P/E" : 3.68, "200-Day Simple Moving Average" : -0.1282, "Shares Outstanding" : 390.6, "Earnings Date" : ISODate("2013-10-28T20:30:00Z"), "52-Week High" : -0.2938, "P/Cash" : 3.93, "Change" : 0.0131, "Analyst Recom" : 2.6, "Volatility (Week)" : 0.0268, "Country" : "USA", "Return on Equity" : 0.205, "50-Day Low" : 0.0695, "Price" : 21.71, "50-Day High" : -0.1066, "Return on Investment" : 0.015, "Shares Float" : 383.97, "Dividend Yield" : 0.1493, "EPS growth next 5 years" : 0.035, "Industry" : "REIT - Residential", "Beta" : 0.51, "Sales growth quarter over quarter" : 0.073, "Operating Margin" : 0.67, "EPS (ttm)" : 5.82, "PEG" : 1.05, "Float Short" : 0.0311, "52-Week Low" : 0.1117, "Average True Range" : 0.52, "EPS growth next year" : -0.3603, "Company" : "American Capital Agency Corp.", "Gap" : 0.0028, "Relative Volume" : 0.71, "Volatility (Month)" : 0.02, "Market Cap" : 8370.56, "Volume" : 4576064, "Gross Margin" : 0.746, "Short Ratio" : 1.69, "Performance (Half Year)" : -0.2136, "Relative Strength Index (14)" : 43.53, "Insider Ownership" : 0.003, "20-Day Simple Moving Average" : -0.0318, "Performance (Month)" : -0.042, "Institutional Transactions" : 0.0077, "Performance (Year)" : -0.1503, "LT Debt/Equity" : 0, "Average Volume" : 7072.83, "EPS growth this year" : -0.169, "50-Day Simple Moving Average" : -0.0376 }

....

### 2. Liste as ações com perdas (limite a 10 novamente)

> db.stocks.find ( {"Profit Margin": {$lt: 0}} ).limit(10)

{ "_id" : ObjectId("52853800bb1177ca391c180c"), "Ticker" : "AAV", "Profit Margin" : -0.232, "Institutional Ownership" : 0.58, "EPS growth past 5 years" : -0.265, "Total Debt/Equity" : 0.32, "Current Ratio" : 0.8, "Return on Assets" : -0.032, "Sector" : "Basic Materials", "P/S" : 2.64, "Change from Open" : 0.0286, "Performance (YTD)" : 0.1914, "Performance (Week)" : 0.0158, "Quick Ratio" : 0.8, "P/B" : 0.63, "EPS growth quarter over quarter" : 1.556, "Performance (Quarter)" : 0.0349, "200-Day Simple Moving Average" : 0.0569, "Shares Outstanding" : 168.38, "Earnings Date" : ISODate("2011-03-16T04:00:00Z"), "52-Week High" : -0.1242, "Change" : 0.0233, "Analyst Recom" : 2.7, "Volatility (Week)" : 0.0381, "Country" : "Canada", "Return on Equity" : -0.055, "50-Day Low" : 0.1127, "Price" : 3.95, "50-Day High" : -0.0436, "Return on Investment" : -0.068, "Shares Float" : 167.07, "Industry" : "Oil & Gas Drilling & Exploration", "Beta" : 2.05, "Sales growth quarter over quarter" : 0.399, "Operating Margin" : 0.102, "EPS (ttm)" : -0.34, "Float Short" : 0.0008, "52-Week Low" : 0.4158, "Average True Range" : 0.12, "EPS growth next year" : -0.667, "Sales growth past 5 years" : -0.121, "Company" : "Advantage Oil & Gas Ltd.", "Gap" : -0.0052, "Relative Volume" : 0.85, "Volatility (Month)" : 0.0303, "Market Cap" : 649.96, "Volume" : 116750, "Gross Margin" : 0.682, "Short Ratio" : 0.89, "Performance (Half Year)" : 0.0078, "Relative Strength Index (14)" : 52.62, "Insider Ownership" : 0.0025, "20-Day Simple Moving Average" : -0.0001, "Performance (Month)" : 0.0158, "Institutional Transactions" : 0.0402, "Performance (Year)" : 0.1386, "LT Debt/Equity" : 0.32, "Average Volume" : 149.81, "EPS growth this year" : 0.42, "50-Day Simple Moving Average" : 0.023 }

{ "_id" : ObjectId("52853800bb1177ca391c1815"), "Ticker" : "ABCD", "Profit Margin" : -0.645, "Institutional Ownership" : 0.186, "EPS growth past 5 years" : -0.195, "Current Ratio" : 1.4, "Return on Assets" : -0.416, "Sector" : "Services", "P/S" : 0.41, "Change from Open" : 0, "Performance (YTD)" : 0.2072, "Performance (Week)" : 0.0229, "Quick Ratio" : 1.2, "Insider Transactions" : -0.0267, "EPS growth quarter over quarter" : 1.022, "Performance (Quarter)" : -0.0496, "200-Day Simple Moving Average" : 0.0446, "Shares Outstanding" : 47.36, "Earnings Date" : ISODate("2013-11-07T21:30:00Z"), "52-Week High" : -0.2757, "P/Cash" : 1.37, "Change" : 0, "Analyst Recom" : 2, "Volatility (Week)" : 0.0737, "Country" : "USA", "Return on Equity" : 3.596, "50-Day Low" : 0.072, "Price" : 1.34, "50-Day High" : -0.2299, "Return on Investment" : -0.876, "Shares Float" : 15.11, "Industry" : "Education & Training Services", "Beta" : 1.7, "Sales growth quarter over quarter" : 0.059, "Operating Margin" : 0.048, "EPS (ttm)" : -2.06, "Float Short" : 0.0007, "52-Week Low" : 0.5952, "Average True Range" : 0.09, "Sales growth past 5 years" : 0.084, "Company" : "Cambium Learning Group, Inc.", "Gap" : 0, "Relative Volume" : 0.04, "Volatility (Month)" : 0.0584, "Market Cap" : 63.46, "Volume" : 1600, "Gross Margin" : 0.552, "Short Ratio" : 0.21, "Performance (Half Year)" : 0.1356, "Relative Strength Index (14)" : 48.07, "Insider Ownership" : 0.003, "20-Day Simple Moving Average" : 0.0037, "Performance (Month)" : -0.0074, "P/Free Cash Flow" : 2.47, "Institutional Transactions" : -0.095, "Performance (Year)" : 0.6543, "Average Volume" : 48.58, "EPS growth this year" : -1.533, "50-Day Simple Moving Average" : -0.064 }

### 3. Liste as 10 ações mais rentáveis

> db.stocks.find ( {}, {"Company": 1, "Profit Margin": 1} ).sort({"Profit Margin": -1}).limit(10)

{ "_id" : ObjectId("52853801bb1177ca391c1af3"), "Profit Margin" : 0.994, "Company" : "BP Prudhoe Bay Royalty Trust" }

{ "_id" : ObjectId("52853802bb1177ca391c1b69"), "Profit Margin" : 0.994, "Company" : "Cascade Bancorp" }

{ "_id" : ObjectId("5285380bbb1177ca391c2c3c"), "Profit Margin" : 0.99, "Company" : "Pacific Coast Oil Trust" }

{ "_id" : ObjectId("52853808bb1177ca391c281b"), "Profit Margin" : 0.986, "Company" : "Enduro Royalty Trust" }

{ "_id" : ObjectId("5285380fbb1177ca391c318e"), "Profit Margin" : 0.982, "Company" : "Whiting USA Trust II" }

{ "_id" : ObjectId("52853808bb1177ca391c27bd"), "Profit Margin" : 0.976, "Company" : "MV Oil Trust" }

{ "_id" : ObjectId("52853801bb1177ca391c1895"), "Profit Margin" : 0.972, "Company" : "American Capital Agency Corp." }

{ "_id" : ObjectId("5285380ebb1177ca391c3101"), "Profit Margin" : 0.971, "Company" : "VOC Energy Trust" }

{ "_id" : ObjectId("52853807bb1177ca391c279a"), "Profit Margin" : 0.97, "Company" : "Mesa Royalty Trust" }

{ "_id" : ObjectId("52853809bb1177ca391c2946"), "Profit Margin" : 0.97, "Company" : "One Liberty Properties Inc." }

### 4. Qual foi o setor mais rentável?

> db.stocks.aggregate ([ {$group: {_id: "$Industry", "Avg Profit Margin": {$avg: "$Profit Margin"}}}, {$sort: {"Avg Profit Margin": -1}}, {$limit: 1} ])

{ "_id" : "Diversified Investments", "Avg Profit Margin" : 0.48268 }

### 5. Ordene as ações pelo profit e usando um cursor, liste as ações.

> var cursor = db.stocks.find().sort({"Profit Margin": -1})

> cursor.forEach(function(c) { print (tojson(c)) })

{

"_id" : ObjectId("52853801bb1177ca391c1af3"),

"Ticker" : "BPT",

"Profit Margin" : 0.994,

"Institutional Ownership" : 0.098,

"EPS growth past 5 years" : 0.025,

"Total Debt/Equity" : 0,

"Sector" : "Basic Materials",

"P/S" : 8.81,

"Change from Open" : 0.0125,

"Performance (YTD)" : 0.2758,

"Performance (Week)" : -0.018,

"P/B" : 2620,

"EPS growth quarter over quarter" : -0.087,

"Payout Ratio" : 1.001,

"Performance (Quarter)" : -0.0556,

"P/E" : 8.87,

"200-Day Simple Moving Average" : -0.0305,

"Shares Outstanding" : 21.4,

"Earnings Date" : ISODate("2013-11-11T05:00:00Z"),

"52-Week High" : -0.159,

"P/Cash" : 1682.04,

"Change" : 0.0064,

"Volatility (Week)" : 0.0151,

"Country" : "USA",

"50-Day Low" : 0.0136,

"Price" : 79.1,

"50-Day High" : -0.0973,

"Shares Float" : 21.4,

"Dividend Yield" : 0.1103,

"Industry" : "Oil & Gas Refining & Marketing",

"Beta" : 0.77,

"Sales growth quarter over quarter" : -0.086,

"Operating Margin" : 0.994,

"EPS (ttm)" : 8.86,

"Float Short" : 0.0173,

"52-Week Low" : 0.3422,

"Average True Range" : 1.37,

"Sales growth past 5 years" : 0.024,

"Company" : "BP Prudhoe Bay Royalty Trust",

"Gap" : -0.0061,

"Relative Volume" : 0.93,

"Volatility (Month)" : 0.016,

"Market Cap" : 1682.04,

"Volume" : 71575,

"Short Ratio" : 4.41,

"Performance (Half Year)" : 0.0022,

"Relative Strength Index (14)" : 38.01,

"20-Day Simple Moving Average" : -0.0318,

"Performance (Month)" : -0.079,

"Institutional Transactions" : -0.0057,

"Performance (Year)" : 0.1837,

"LT Debt/Equity" : 0,

"Average Volume" : 84.15,

"EPS growth this year" : -0.012,

"50-Day Simple Moving Average" : -0.0496

}

{

"_id" : ObjectId("52853802bb1177ca391c1b69"),

"Ticker" : "CACB",

"Profit Margin" : 0.994,

"Institutional Ownership" : 0.547,

"EPS growth past 5 years" : -0.584,

"Total Debt/Equity" : 0,

"Return on Assets" : 0.039,

"Sector" : "Financial",

"P/S" : 4.66,

"Change from Open" : -0.0137,

"Performance (YTD)" : -0.1869,

"Performance (Week)" : 0.0079,

"Insider Transactions" : 1.0755,

"P/B" : 1.28,

"EPS growth quarter over quarter" : 25.422,

"Payout Ratio" : 0,

"Performance (Quarter)" : -0.1314,

"Forward P/E" : 42.42,

"P/E" : 4.71,

"200-Day Simple Moving Average" : -0.1709,

"Shares Outstanding" : 47.17,

"Earnings Date" : ISODate("2013-11-13T21:30:00Z"),

"52-Week High" : -0.2994,

"P/Cash" : 2.26,

"Change" : -0.0118,

"Analyst Recom" : 3,

"Volatility (Week)" : 0.0353,

"Country" : "USA",

"Return on Equity" : 0.336,

"50-Day Low" : 0.006,

"Price" : 5.03,

"50-Day High" : -0.2066,

"Return on Investment" : 0.346,

"Shares Float" : 40.67,

"EPS growth next 5 years" : 0.05,

"Industry" : "Regional - Pacific Banks",

"Beta" : 2.34,

"Sales growth quarter over quarter" : -0.101,

"Operating Margin" : 0.027,

"EPS (ttm)" : 1.08,

"PEG" : 0.94,

"Float Short" : 0.0088,

"52-Week Low" : 0.0817,

"Average True Range" : 0.19,

"EPS growth next year" : -0.8904,

"Sales growth past 5 years" : -0.203,

"Company" : "Cascade Bancorp",

"Gap" : 0.002,

"Relative Volume" : 1.35,

"Volatility (Month)" : 0.0399,

"Market Cap" : 240.11,

"Volume" : 21432,

"Short Ratio" : 20.55,

"Performance (Half Year)" : -0.1239,

"Relative Strength Index (14)" : 29.61,

"Insider Ownership" : 0.009,

"20-Day Simple Moving Average" : -0.0729,

"Performance (Month)" : -0.1039,

"Institutional Transactions" : 0.0004,

"Performance (Year)" : 0.0241,

"LT Debt/Equity" : 0,

"Average Volume" : 17.39,

"EPS growth this year" : 1.12,

"50-Day Simple Moving Average" : -0.116

}

...

  
### 6. Renomeie o campo “Profit Margin” para apenas “profit”.

> db.stocks.updateMany( {}, { $rename: { "Profit Margin": "profit" } } )

{ "acknowledged" : true, "matchedCount" : 6756, "modifiedCount" : 4302 }

  
### 7. Agora liste apenas a empresa e seu respectivo resultado

> db.stocks.find({}, {Company: 1, profit: 1})

{ "_id" : ObjectId("52853800bb1177ca391c1800"), "Company" : "Alcoa, Inc.", "profit" : 0.013 }

{ "_id" : ObjectId("52853800bb1177ca391c17ff"), "Company" : "Agilent Technologies Inc.", "profit" : 0.137 }

{ "_id" : ObjectId("52853800bb1177ca391c1802"), "Company" : "iShares MSCI AC Asia Information Tech" }

{ "_id" : ObjectId("52853800bb1177ca391c1801"), "Company" : "WCM/BNY Mellon Focused Growth ADR ETF" }

{ "_id" : ObjectId("52853800bb1177ca391c1803"), "Company" : "Altisource Asset Management Corporation" }

{ "_id" : ObjectId("52853800bb1177ca391c1804"), "Company" : "Atlantic American Corp.", "profit" : 0.056 }

{ "_id" : ObjectId("52853800bb1177ca391c180b"), "Company" : "Almaden Minerals Ltd." }

{ "_id" : ObjectId("52853800bb1177ca391c1805"), "Company" : "Aaron's, Inc.", "profit" : 0.06 }

{ "_id" : ObjectId("52853800bb1177ca391c180c"), "Company" : "Advantage Oil & Gas Ltd.", "profit" : -0.232 }

{ "_id" : ObjectId("52853800bb1177ca391c180e"), "Company" : "iShares MSCI All Country Asia ex Jpn Idx" }

{ "_id" : ObjectId("52853800bb1177ca391c180d"), "Company" : "Atlas Air Worldwide Holdings Inc.", "profit" : 0.071 }

{ "_id" : ObjectId("52853800bb1177ca391c1807"), "Company" : "AAON Inc.", "profit" : 0.105 }

{ "_id" : ObjectId("52853800bb1177ca391c180f"), "Company" : "AllianceBernstein Holding L.P.", "profit" : 0.896 }

{ "_id" : ObjectId("52853800bb1177ca391c1811"), "Company" : "ABB Ltd.", "profit" : 0.069 }

{ "_id" : ObjectId("52853800bb1177ca391c180a"), "Company" : "American Assets Trust, Inc.", "profit" : 0.155 }

{ "_id" : ObjectId("52853800bb1177ca391c1812"), "Company" : "AbbVie Inc.", "profit" : 0.24 }

{ "_id" : ObjectId("52853800bb1177ca391c1810"), "Company" : "Abaxis Inc.", "profit" : 0.1 }

{ "_id" : ObjectId("52853800bb1177ca391c1815"), "Company" : "Cambium Learning Group, Inc.", "profit" : -0.645 }

{ "_id" : ObjectId("52853800bb1177ca391c1814"), "Company" : "Ameris Bancorp", "profit" : 0.166 }

{ "_id" : ObjectId("52853800bb1177ca391c1816"), "Company" : "Advisory Board Co.", "profit" : 0.055 }

Type "it" for more

  
### 8. Analise as ações. É uma bola de cristal na sua mão... Quais as três ações  você investiria?

> // ações com menor índice preço/lucro

> db.stocks.find ( {"P/E": {$exists: true}}, {"Company": 1, "P/E": 1} ).sort({"P/E": 1}).limit(3)

{ "_id" : ObjectId("52853801bb1177ca391c1b07"), "P/E" : 0.01, "Company" : "Berkshire Hathaway Inc." }

{ "_id" : ObjectId("5285380bbb1177ca391c2bf7"), "P/E" : 0.02, "Company" : "RINO International Corporation" }

{ "_id" : ObjectId("52853805bb1177ca391c229f"), "P/E" : 0.05, "Company" : "Home Bancorp, Inc." }

## Exercício 3 – Fraude na Enron!

### 1. Liste as pessoas que enviaram e-mails (de forma distinta, ou seja, sem  repetir). Quantas pessoas são?

> db.enron.aggregate([ { $group: {_id: "$sender"} }, {$count: "Total de pessoas"} ])

{ "Total de pessoas" : 2200 }

> db.enron.aggregate([ { $group: {_id: "$sender"} } ])

{ "_id" : "jdmnash@home.com" }

{ "_id" : "alberto_jaramillo@putnaminv.com" }

{ "_id" : "laurel.crafts@library.gatech.edu" }

{ "_id" : "kenneth.booi@mirant.com" }

{ "_id" : "melvin.anderson@enron.com" }

{ "_id" : "mhollifi@mail.smu.edu" }

{ "_id" : "rebekah.rushing@enron.com" }

{ "_id" : "president@weforum.org" }

{ "_id" : "pravas@ruf.rice.edu" }

{ "_id" : "mark.palmer@enron.com" }

{ "_id" : "lgunnell@msgidirect.com" }

{ "_id" : "production@gatepoint.net" }

{ "_id" : "scott.affelt@enron.com" }

{ "_id" : "jtoland@forrester.com" }

{ "_id" : "mack@orci.com" }

{ "_id" : "terrance.devereaux@enron.com" }

{ "_id" : "harrisonj@argentgroupltd.com" }

{ "_id" : "brian.oxley@enron.com" }

{ "_id" : "ann.lofton@compassbnk.com" }

{ "_id" : "spitandimage@earthlink.net" }

Type "it" for more

  
### 2. Contabilize quantos e-mails tem a palavra “fraud”

> db.enron.find( {text: /\bfraud\b/i}).count()

22
