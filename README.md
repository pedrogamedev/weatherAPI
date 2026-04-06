# PT-BR
### Visão Geral
Um projeto, destinado à aprendizagem pessoal, que implementa Rate Limiting e Client Side Caching com o objetivo de criar uma API robusta voltada para dados climáticos. Para acessar dados climáticos foi-se utilizado a API 3rd party [Visual Crossing Weather API](https://www.visualcrossing.com/weather-api/).

Durante o projeto, segui padrões para organizar commits através da especificação Conventional Commits e criei um padronizamento das pastas voltadas à seção de testes.
A API 3rd party tem um limite diário de requisições.

#### Tecnologias Usadas
- Java
- Maven
- Intellij IDEA
- Springboot
- JUnit
- Mockito
- Redis
- Bucket4J

### Arquitetura 

O projeto não segue o padrão REST nem implementa HATEOAS.
É dividido nas seguintes camadas:

- Controller: responsável pelo processamento de requisições.
- Service: responsável pela regra de negócio, a conexão com o servidor redis e a conexão com a API 3rd party.
- Redis: responsável pelo client-side caching.
- Bucket4J: responsável pelo rate-limiting.

<img width="1920" height="1080" alt="projarchitecture" src="https://github.com/user-attachments/assets/435157f2-f6f2-46ea-b97d-999ff4cc58c8" />


### Demo
#### Pegar informações num período de até 5 dias -> (GET) /weather/get

<div align=center>
    <img width="720" height="700" alt="image" src="https://github.com/user-attachments/assets/9fa03f64-954b-4a96-9358-c7d119036e49" />
</div>

Pega informações do clima num determinado local num período de até 5 dias. Necessário colocar o local (location), data de início (startDate) e data de término (endDate). Retorna horário, temperatura média, descrição e sensação térmica.
### Como rodar:

----> Para obter uma API KEY, crie uma conta no [openvisual](https://www.visualcrossing.com/weather-api/) <----

#### Manualmente 💻
##### Pré-Requisitos

- Java 21
- Redis
- Download do .jar do projeto

##### Passo a passo

1. Inicialize o servidor Redis.
2. Abra o terminal numa pasta contendo o projeto.
3. Configure as seguintes variavéis do ambiente: API_KEY, REDIS_PASSWORD.
4.  Use o comando "java -jar your-application.jar" para iniciar o projeto.

Pronto! O projeto inicializará e você conseguirá interagir com ele mandando requisições HTTP através de aplicativos como Postman.

#### Docker 🐳
##### Pré-Requisitos

- Docker
- Download do projeto com docker

##### Passo a passo

1. Inicialize o docker.
2. Abra o terminal numa pasta contendo o projeto.
3. Altere as variáveis do ambiente no arquivo .env. 
4. Use o comando 'docker-compose up'.

Pronto! O projeto inicializará e você conseguirá interagir com ele mandando requisições HTTP através de aplicativos como Postman.

##### Testes
Os testes seguem a seguinte nomenclatura para seus nomes:

_nomeOriginalDaFunção_EstadoAtual_ComportamentoDesejado_

Então, quando testamos, com o objetivo de averiguar o "caminho feliz", a função _receberItemQuebrado_, esperamos que receba _ItemQuebrado_ e retorne _ItemConsertado_. Logo o teste terá o seguinte nome:

_receberItemQuebrado_RecebeItemQuebrado_RetornaItemConsertado

Em testes que averiguamos os codigos de status HTTP retornados, adicionamos o resultado ao _ComportamentoDesejado_.

##### Arquitetura de diretórios
Foi-se divido em 3 pastas principais
- Slice, para slice tests.
- Unit, para testes de unidade.
Cada uma contém pastas replicando a estrutura de pastas do projeto, abrangendo somente as camadas que foram testadas.

