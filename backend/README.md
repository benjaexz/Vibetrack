# Vibetrack Backend

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green?logo=spring)](https://spring.io/projects/spring-boot)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen)](https://github.com/benjaexz/vibetrack-backend)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

---

## 🚀 Visão Geral

O **backend do Vibetrack** fornece uma API REST completa para gerenciamento de usuários.
Ele é responsável por armazenar dados, gerenciar operações CRUD e servir dados para o frontend Angular.

---

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.5.6**
* **Spring Data JPA**
* **H2 Database (In-Memory)**
* **Maven**
* **Tomcat Embedded**
* **REST API**
* **CORS configurado para Angular (`http://localhost:4200`)**

---

## 📂 Estrutura do Projeto

```text
vibetrack-backend/
├─ src/main/java/com/jaco/vibetrack/
│  ├─ controller/        # Endpoints REST (AppUserController.java)
│  ├─ model/             # Modelos de dados (AppUser.java)
│  ├─ repository/        # Repositórios JPA (AppUserRepository.java)
│  └─ VibetrackApplication.java
├─ src/main/resources/
│  └─ application.properties  # Configurações do Spring Boot e H2
├─ pom.xml                   # Dependências Maven
└─ README.md                 # Este arquivo
```

---

## 🔹 Funcionalidades Implementadas

* **Criar usuário** → `POST /users`
* **Listar usuários** → `GET /users`
* **Atualizar usuário pelo ID** → `PUT /users/{id}`
* **Deletar usuário pelo ID** → `DELETE /users/{id}`
* **Integração com frontend Angular** via CORS

---

## ⚙️ Como Rodar

1. Clonar o repositório:

```bash
git clone https://github.com/benjaexz/vibetrack-backend.git
cd vibetrack-backend
```

2. Rodar com Maven:

```bash
./mvnw spring-boot:run
```

> No Windows sem `./mvnw`:

```bash
mvn spring-boot:run
```

3. A API estará disponível em `http://localhost:8080`.

4. Console H2 para testes:

* URL: `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:testdb`
* Usuário: `SA`
* Senha: (vazio)

---

## 🔗 Integração com Frontend

O frontend Angular consome os endpoints da API:

* **GET /users** → Listar usuários
* **POST /users** → Criar usuário
* **PUT /users/{id}** → Atualizar usuário
* **DELETE /users/{id}** → Deletar usuário

Todos os endpoints foram testados e estão prontos para integração.

---

## ⚠️ Observações

* Atualmente utiliza **H2 in-memory** (ideal para desenvolvimento).
  Para produção, recomenda-se migrar para **PostgreSQL**, MySQL ou outro banco persistente.
* Documentação futura poderá incluir exemplos com **Postman** ou **cURL**.
* Projeto versionado via GitHub e pronto para integração com frontend.

---

## ✅ Status do Projeto

* Backend: ✅ Funcional (CRUD completo)
* Frontend: ⚙ Em desenvolvimento (integração pronta)

---

## 📜 Licença

MIT License © 2025 Jacó Lima Pimentel
