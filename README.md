# Vibetrack (MVP finalizado)

Aplicação full-stack para autoconhecimento musical: registre músicas + emoções e visualize padrões de humor, artistas, gêneros e hábitos de escuta ao longo do tempo.

<p align="center">
  <img src="docs/screenshots/login.png" alt="Vibetrack - Login" width="900" />
</p>

## Sobre o projeto
O Vibetrack nasceu como um projeto de portfólio para praticar arquitetura full-stack, autenticação com JWT e consumo de API com Angular.
O foco foi entregar um MVP funcional — com decisões e concessões reais — e documentar o suficiente para qualquer pessoa conseguir rodar localmente.

> Status: **MVP finalizado (portfólio)**.  
> Roadmap permanece no fim do README para possíveis evoluções.

## Funcionalidades
- Autenticação JWT (registro, login e rotas protegidas).
- Registro de vibes (música + artista + gênero + emoção + timestamp).
- Listagem de vibes por usuário.
- Exclusão de registros.
- Estatísticas:
  - Contagem de emoções.
  - Evolução temporal (timeline).
  - Top artistas.
  - Top gêneros.

## Stack
**Backend**
- Java 17
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA / Hibernate
- Banco: H2 (local/dev)

**Frontend**
- Angular 17 (Standalone Components)
- TypeScript + RxJS
- HttpClient para integração com API

## Arquitetura (resumo)
Monorepo com separação clara entre frontend e backend:
- `backend/`: API REST em camadas (Controller → Service → Repository → Entity) + DTOs
- `frontend/`: SPA Angular (pages/components/services + models)

### Segurança e isolamento por usuário
A autenticação é baseada em JWT.
Mesmo em rotas que incluem `userId`, o backend valida **ownership**: o `userId` informado precisa corresponder ao usuário autenticado pelo token (evitando acesso a dados de terceiros).

## Rotas da API
### Autenticação
- `POST /api/v1/auth/register` – registrar usuário
- `POST /api/v1/auth/login` – login e geração de JWT

### Vibes
- `POST /api/v1/vibes` – criar vibe
- `GET /api/v1/vibes/user/{userId}` – listar vibes por usuário
- `DELETE /api/v1/vibes/{id}/user/{userId}` – deletar vibe

### Estatísticas
- `GET /api/v1/vibes/stats/emotions/{userId}` – contagem de emoções
- `GET /api/v1/vibes/stats/timeline/{userId}` – evolução ao longo do tempo
- `GET /api/v1/vibes/stats/top-artists/{userId}` – artistas mais frequentes
- `GET /api/v1/vibes/stats/top-genres/{userId}` – gêneros mais escutados

## Como rodar localmente
### Pré-requisitos
- Java 17+
- Node.js + npm

### Backend
cd backend
./gradlew bootRun

text

### Frontend
cd frontend
npm install
ng serve

text

Acesse:
- Frontend: http://localhost:4200
- Backend: http://localhost:8080

> Configurações: mantidas no “configure” do projeto (application properties/yml), conforme o setup local.

## Screenshot
- Tela de login (UI inicial) — `docs/screenshots/login.png`

## Decisões e limitações (MVP)
- Banco H2 local para facilitar execução e demonstração rápida.
- Deploy e “produção 24/7” ficaram fora do escopo do MVP.
- O foco foi concluir o fluxo principal (auth + CRUD + stats) e manter um roadmap claro.

## Roadmap (se um dia evoluir)
- Paginação e filtros avançados (datas, emoção, gênero).
- Dashboard consolidado com gráficos.
- Testes unitários e de integração.
- Documentação técnica/ADRs.
- Deploy (Render/EC2) e banco persistente (PostgreSQL).

## Licença
Projeto de uso pessoal, acadêmico e profissional. Licença será definida futuramente.
