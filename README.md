<div align="center">
  <h1>Transactions - Desafio Backend Itaú Unibanco</h1>
  <img src="https://skillicons.dev/icons?i=java,spring,docker,gradle">
</div>
<br>



<br>

## 🔗 Link do Desafio
O desafio original pode ser encontrado no repositório:
[Itaú Unibanco - Desafio de Programação](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior).

<br>

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem principal para o desenvolvimento.
- **Spring Boot 3**: Framework para criação da API REST.
- **Swagger**: Documentação automática da api.
- **Docker & Docker Compose**: Orquestração dos serviços.

<br>

## 📝 Metodologia

- **Clean Architecture**: Separação clara das responsabilidades em camadas (entities, use cases, controllers, repositories), seguindo os princípios de Clean Architecture.
- **SOLID**: Aplicação dos princípios SOLID para garantir um código modular, extensível e de fácil manutenção.

<br>

## 📦 Como Executar o Projeto

### 1️⃣ Clonar o repositório

```sh
git clone https://github.com/leandrosantino/transactions.git
cd transactions
```

### 2️⃣ Configurar o ambiente
Certifique-se de ter Docker e Docker Compose instalados.

### 3️⃣ Compilar o projeto
```sh
./gradlew bootJar
```

### 4️⃣ Subir os containers
```sh
docker-compose up -d
```
A API estará disponível em http://localhost:8080

<br>

## 📌 Documentação da API
Após subir os containers, acesse o endpoint abaixo para visualizar a documentação da API.

```
GET /swagger-ui/index.html
```
