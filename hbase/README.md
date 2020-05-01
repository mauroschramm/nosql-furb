# HBase

### Mauro Schramm

## Preparando tabela e dados
### 1. Crie a tabela com 2 famílias de colunas:  
#### a. personal-data  
#### b. professional-data
create 'italians', 'personal-data', 'professional-data'
Created table italians

### 2. Importe o arquivo via linha de comando
root@abbaa06d1297:/tmp# hbase shell ./italians.txt

## Executando operações
### 1. Adicione mais 2 italianos mantendo adicionando informações como data  de nascimento nas informações pessoais e um atributo de anos de  experiência nas informações profissionais;  
put 'italians', '11', 'personal-data:name', 'Gean Carlini'
put 'italians', '11', 'personal-data:born', '1970-01-01'
put 'italians', '11', 'professional-data:years-exp', '20'
put 'italians', '12', 'personal-data:name', 'Marco Polo'
put 'italians', '12', 'personal-data:born', '1254-01-01'
put 'italians', '12', 'professional-data:years-exp', '40'

### 2. Adicione o controle de 5 versões na tabela de dados pessoais.
 alter 'italians', NAME => 'personal-data', VERSIONS => 5
 alter 'italians', NAME => 'professional-data', VERSIONS => 5

### 3. Faça 5 alterações em um dos italianos;  
 put 'italians', '12', 'personal-data:name', 'Marco Pollo'  
 put 'italians', '12', 'personal-data:name', 'Marco Polo'  
 put 'italians', '12', 'personal-data:born', '1254-06-01'  
 put 'italians', '12', 'professional-data:years-exp', '45'  
 put 'italians', '12', 'professional-data:years-exp', '44'  
  
### 4. Com o operador get, verifique como o HBase armazenou o histórico.  
 get 'italians', 12, {COLUMN => ['personal-data', 'professional-data'], VERSIONS => 5}  
COLUMN                                                CELL  
 personal-data:born                                   timestamp=1588194882741, value=1254-06-01  
 personal-data:born                                   timestamp=1588193013534, value=1254-01-01  
 personal-data:name                                   timestamp=1588194852159, value=Marco Polo  
 personal-data:name                                   timestamp=1588194843236, value=Marco Pollo  
 personal-data:name                                   timestamp=1588192938902, value=Marco Polo  
 professional-data:years-exp                          timestamp=1588194955440, value=44  
 professional-data:years-exp                          timestamp=1588194902204, value=45  
 professional-data:years-exp                          timestamp=1588193028059, value=40  
1 row(s)  
Took 0.0450 seconds  

### 5. Utilize o scan para mostrar apenas o nome e profissão dos italianos.
 scan 'italians', {COLUMNS => ['personal-data:name', 'professional-data:role']}
ROW                                                   COLUMN+CELL
 1                                                    column=personal-data:name, timestamp=1588182749427, value=Paolo Sorrentino
 1                                                    column=professional-data:role, timestamp=1588182749476, value=Gestao Comercial
 10                                                   column=personal-data:name, timestamp=1588182749760, value=Giovanna Caputo
 10                                                   column=professional-data:role, timestamp=1588182749774, value=Comunicacao Institucional
 11                                                   column=personal-data:name, timestamp=1588191568097, value=Gean Carlini
 12                                                   column=personal-data:name, timestamp=1588194852159, value=Marco Polo
 2                                                    column=personal-data:name, timestamp=1588182749493, value=Domenico Barbieri
 2                                                    column=professional-data:role, timestamp=1588182749509, value=Psicopedagogia
 3                                                    column=personal-data:name, timestamp=1588182749531, value=Maria Parisi
 3                                                    column=professional-data:role, timestamp=1588182749545, value=Optometria
 4                                                    column=personal-data:name, timestamp=1588182749559, value=Silvia Gallo
 4                                                    column=professional-data:role, timestamp=1588182749576, value=Engenharia Industrial Madeireira
 5                                                    column=personal-data:name, timestamp=1588182749612, value=Rosa Donati
 5                                                    column=professional-data:role, timestamp=1588182749632, value=Mecatronica Industrial
 6                                                    column=personal-data:name, timestamp=1588182749646, value=Simone Lombardo
 6                                                    column=professional-data:role, timestamp=1588182749661, value=Biotecnologia e Bioquimica
 7                                                    column=personal-data:name, timestamp=1588182749680, value=Barbara Ferretti
 7                                                    column=professional-data:role, timestamp=1588182749693, value=Libras
 8                                                    column=personal-data:name, timestamp=1588182749707, value=Simone Ferrara
 8                                                    column=professional-data:role, timestamp=1588182749720, value=Engenharia de Minas
 9                                                    column=personal-data:name, timestamp=1588182749732, value=Vincenzo Giordano
 9                                                    column=professional-data:role, timestamp=1588182749746, value=Marketing
12 row(s)
Took 0.3782 seconds

### 6. Apague os italianos com row id ímpar
deleteall 'italians', '1'
Took 0.0407 seconds
deleteall 'italians', '3'
Took 0.0033 seconds
deleteall 'italians', '5'
Took 0.0030 seconds
deleteall 'italians', '7'
Took 0.0078 seconds
deleteall 'italians', '9'
Took 0.0023 seconds
deleteall 'italians', '11'
Took 0.0101 seconds

### 7. Crie um contador de idade 55 para o italiano de row id 5.
incr 'italians', '5', 'personal-data:age', 55
COUNTER VALUE = 55
Took 0.0259 seconds

### 8. Incremente a idade do italiano em 1
incr 'italians', '5', 'personal-data:age'
COUNTER VALUE = 56
Took 0.0112 seconds
