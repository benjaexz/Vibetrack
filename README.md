# Vibetrack

Vibetrack é uma aplicação full stack voltada para autoconhecimento musical. O sistema registra músicas e emoções associadas, permitindo que o usuário descubra padrões de gênero, artistas, hábitos de escuta e estados emocionais ao longo do tempo.

---

## 📌 Funcionalidades principais

* Registro de "vibes" (música + emoção + contexto)
* Listagem e visualização dos registros
* Análises temporais básicas
* Identidade musical: padrões de humor, gêneros, artistas mais frequentes

---

## 🧱 Arquitetura

A aplicação segue uma arquitetura **monorepo**, dividida em:

### **Backend (Spring Boot)**

* Java + Spring Boot
* Endpoints REST
* Entidade `VibeEntry`
* Camadas: Controller, Service, Repository
* Preparado para integração futura com banco de dados

### **Frontend (Angular)**

* Angular Standalone Components
* Tela simples para cadastrar e visualizar vibes
* Comunicação com a API via HttpClient

---

## 🚀 Rotas da API (provisórias)

* `POST /vibes` — cria novo registro
* `GET /vibes` — lista todos os registros
* `GET /vibes/{id}` — obtém um registro específico

---

## 🛠️ Tecnologias

* **Backend:** Java 17, Spring Boot
* **Frontend:** Angular 17
* **Controle de versão:** Git + GitHub

---

## 📂 Estrutura do repositório

```
Vibetrack/
  backend/
    src/
    pom.xml
  frontend/
    src/
    angular.json
  README.md
```

---

## 👨‍💻 Status do projeto

Fase: *Desenvolvimento ativo*. Backend estruturado, frontend funcional com standalone components, integração a caminho.

---

## 🧭 Próximos passos

* Finalizar integração front ⇆ back
* Criar documentação completa da API
* Adicionar testes
* Criar ADRs
* Criar demonstração visual (gif ou vídeo)
* Preparar nota de UX para o projeto

---

## 📜 Licença

Projeto de uso pessoal e educacional. Licença a definir.
