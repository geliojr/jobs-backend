# PERFIL PROFISSIONAL API


## Sobre o projeto
  Api para cadastro de profissionais e seus contatos.
 
 ## Links
 ### -[Postman contendo todas as requisições de API](https://documenter.getpostman.com/view/5414747/Tzm2KdxK)
 ### - Ou Swagger rodando no localhost localhost:8080/swagger-ui.html (8080 por padrão ou na porta definida)
 # Tecnologias e ferramentas utilizadas
 - Java 11  
 - Spring Boot 2.4
 - IntelliJ IDEA
 - Postman
 - SmartGit
 - H2 databse
 - PostgreSql

 # Como rodar
 ### Requisitos:
 -Java 11, Spring boot 2.4 e Maven
 
 ```sh
git clone https://github.com/sauloiot/perfil-profissional.git
cd quotation
mvnw spring-boot:run
```
 Ou você pode abrir o projeto em uma IDE e executar o arquivo "PerfilProfissionalApplication" em src/main/java/com/ghost/perfilProfissional/PerfilProfissionalApplication.java.

### Esta api roda na porta 8080, porém é possivel alterar trocando a porta da propridade "server.port=${8081}" e ativando a mesma no arquivo application.properties.

#### O projeto possui 3 perfis: test, prod e dev. 
Por padrão o perfil inicial é o dev, o perfil dev pode rodar com H2 databse que é um banco de dados em memoria ou PostgreSQL, basta alternar entre os trechos de codigos comentados dentro do arquivo "application-dev.properties", o perfil test roda em H2 e o prod roda com PostgreSQL, para alternar é necessario alterar a linha
 ```sh
spring.profiles.active
```
do arquivo 
 ```sh
application.properties
```
Os perfis test e dev instanciam dados no banco ao iniciar o projeto. O perfil dev executa "create-drop" resetando o banco de dados a cada execução(cuidado) caso utilize o PostgreSQL.

### Para executar os testes:
-OPCIONAL: utilizar o perfil de testes (test) que utiliza o banco h2 em memoria e assim não gerando efeitos no banco real
##### Altere
 ```sh
spring.profiles.active=dev
```
para
 ```sh
spring.profiles.active=test
```
##### executar os testes na pasta src/test/java ou executar a linha: 
 ```sh
mvnw test
```

### Para executar o projeto em produção ou apenas utilizar o banco de dados nos perfis dev e prod.
altere
 ```sh
spring.profiles.active=dev
```
para
 ```sh
spring.profiles.active=prod
```
em seguida acesse o arquivo:
 ```sh
application-prod.properties
```
e altere a url do banco e a senha do banco
 ```sh
spring.datasource.url=jdbc:postgresql://URL_DO_BANCO
spring.datasource.password=SENHA_DO_BANCO
spring.datasource.username=postgres
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```

 ### Gerando a build do projeto
 A opção gera o build se estiver conectado no PostgreSql.
  ```sh
 mvnw clean package spring-boot:repackage
java -jar target/perfilProfissional-0.0.1-SNAPSHOT.jar
```

 # Author
 Saulo Ivo Oliveira Tavares
 
"# perfil-profissional" 
"# perfil-profissional" 
