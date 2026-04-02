-EM CONSTRUÇÃO-

to add
visao geral
techs usadas
arquitetura
client side caching
demo
testes

# PT-BR
### Visão Geral
Um projeto, destinado à aprendizagem pessoal, que implementa Rate Limiting e Client Side Caching com o objetivo de criar uma API robusta voltada para dados climáticos. Para acessar dados climáticos utilizo a API 3rd party [Visual Crossing Weather API](https://www.visualcrossing.com/weather-api/).

Durante o projeto, segui padrões para organizar commits através da especificação Conventional Commits e criei um padronizamento das pastas voltadas à seção de testes.

#### Tecnologias Usadas
- Java
- Maven
- Intellij IDEA
- Springboot
- JUnit
- Mockito
- Redis
- Bucket4J



Como rodar:

!Crie uma conta no [openvisual](https://www.visualcrossing.com/weather-api/) e obtenha uma API KEY!

Manualmente 💻
Pré-Requisitos

    Java 21
    Redis
    Download do .jar do projeto

Passo a passo

    Inicialize o servidor Redis.
    Abra o terminal numa pasta contendo o projeto.
    Configure as seguintes variaveis do ambiente: API_KEY, REDIS_PASSWORD.
    Use o comando "java -jar your-application.jar" para iniciar o projeto.

Pronto! O projeto inicializará e você conseguirá interagir com ele mandando requisições HTTP através de aplicativos como Postman.
Docker 🐳
Pré-Requisitos

    Docker
    Download do projeto com docker

Passo a passo

    Inicialize o docker.
    Abra o terminal numa pasta contendo o projeto.
    Altere as variaveis do ambiente no arquivo .env. 
    Use o comando 'docker-compose up'.

Pronto! O projeto inicializará e você conseguirá interagir com ele mandando requisições HTTP através de aplicativos como Postman.
