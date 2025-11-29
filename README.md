Vibetrack

Vibetrack é uma aplicação full stack voltada para autoconhecimento musical. O sistema registra músicas e emoções associadas, permitindo identificar padrões de humor, artistas, gêneros e hábitos de escuta ao longo do tempo.

📌 Funcionalidades principais

Registro de vibes (música + artista + gênero + emoção + timestamp)

Listagem filtrada por usuário

Exclusão de registros

Estatísticas temporais e emocionais

Identidade musical baseada nos padrões do usuário

Autenticação JWT (login, registro e proteção das rotas)

🧱 Arquitetura

A aplicação segue estrutura monorepo:

Backend (Spring Boot + Java)

Entidade AppUser

Entidade VibeEntry

DTOs organizados (requests e responses)

Service completo com validação de usuário

Repositório com consultas personalizadas

Autenticação e autorização via JWT

Endpoints REST padronizados (/api/v1/...)

Preparado para análises estatísticas

Frontend (Angular 17)

Standalone Components

Formulários reativos

Tela de login/registro

Tela de cadastro de vibes

Listagem de vibes

Integração com API via HttpClient

Estrutura pronta para gráficos e dashboards

🚀 Rotas da API (atualizado)
Autenticação

POST /api/v1/auth/register – registrar usuário

POST /api/v1/auth/login – login e geração de JWT

Vibes

POST /api/v1/vibes – criar vibe

GET /api/v1/vibes/user/{userId} – listar vibes por usuário

DELETE /api/v1/vibes/{id}/user/{userId} – deletar vibe

Estatísticas

GET /api/v1/vibes/stats/emotions/{userId} – contagem de emoções

GET /api/v1/vibes/stats/timeline/{userId} – evolução ao longo do tempo

GET /api/v1/vibes/stats/top-artists/{userId} – artistas mais frequentes

GET /api/v1/vibes/stats/top-genres/{userId} – gêneros mais escutados

🛠️ Tecnologias

Backend:
Java 17, Spring Boot 3, Spring Security, JWT, JPA/Hibernate

Frontend:
Angular 17, Typescript, Standalone Components, RxJS

Infra:
Git + GitHub

📂 Estrutura do repositório
Vibetrack/
  backend/
    src/main/java/com/vibetrack/
      config/
      controller/
      dto/
      model/
      repository/
      security/
      service/
    pom.xml

  frontend/
    src/app/
      components/
      services/
      pages/
    angular.json

  README.md

👨‍💻 Status atual do projeto

Fase: Desenvolvimento avançado

Backend com CRUD + estatísticas + JWT funcionando

DTOs padronizados

Rotas definidas e organizadas

Frontend estruturado e conectado

Em andamento: dashboards, UX, testes e polimento final

🧭 Roadmap do que ainda será implementado
🔐 Backend

Paginação dos registros

Filtros avançados (intervalo de datas, emoção, gênero)

Endpoint de dashboard consolidado

Testes unitários e de integração

ADRs e documentação técnica completa

Estrutura detalhada do README da API

Deploy futuro em Render/EC2

🎨 Frontend

Dashboard com gráficos

Animações simples e UX refinada

Tela de perfil do usuário

Melhorias no layout e responsividade

Tela de estatísticas completas

Feature de dark mode

📦 Extra

GIF de demonstração

Vídeo curto para LinkedIn

Nota de UX

README profissional do repositório

📜 Licença

Projeto de uso pessoal, acadêmico e profissional.
Licença será definida futuramente.
