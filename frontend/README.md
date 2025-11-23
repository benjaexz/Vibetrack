# Vibetrack - Frontend

Frontend do projeto **Vibetrack**, sistema de gerenciamento de usuários integrado com backend em Spring Boot.

> **Status atual:** Frontend inicializado, backend funcionando, integração planejada. As telas e componentes ainda serão implementados.

---

## Tecnologias utilizadas

* **Angular 17** – Framework para construção do frontend.
* **TypeScript** – Linguagem principal do frontend.
* **CSS** – Estilização básica.
* **Node.js & NPM** – Gerenciamento de pacotes e scripts do Angular.
* **Git/GitHub** – Controle de versão e hospedagem do repositório.

---

## Estrutura do projeto

```
frontend/
├── src/
│   ├── app/               # Diretório para futuros componentes e módulos
│   ├── assets/            # Arquivos estáticos (imagens, ícones, etc.)
│   ├── styles.css         # Estilos globais
│   └── main.ts            # Entrada do Angular
├── angular.json           # Configuração do Angular CLI
├── package.json           # Dependências e scripts
└── tsconfig.json          # Configuração do TypeScript
```

---

## Funcionalidades previstas

* Integração com backend para gerenciamento de usuários:

  * `GET /users` – Listar usuários
  * `POST /users` – Criar usuário
  * `PUT /users/:id` – Atualizar usuário
  * `DELETE /users/:id` – Remover usuário

* Futuro desenvolvimento:

  * Formulários de cadastro e edição
  * Listagem e visualização de usuários
  * Interface responsiva e estilizada

---

## Como rodar localmente

1. Clone o repositório:

```bash
git clone https://github.com/benjaexz/Vibetrack-frontend.git
```

2. Instale dependências:

```bash
npm install
```

3. Inicie o servidor de desenvolvimento:

```bash
ng serve
```

4. Acesse no navegador:

```
http://localhost:4200
```

> Certifique-se que o backend esteja rodando em `http://localhost:8080` para que a integração funcione corretamente.

---

## Próximos passos

* Implementar todos os componentes e telas do frontend.
* Testar integração completa com o backend.
* Adicionar documentação e exemplos de uso detalhados.

---

## Autor

**Jacó Lima Pimentel** – Desenvolvedor Full Stack
GitHub: [benjaexz](https://github.com/benjaexz)
LinkedIn: [Jacó Lima](https://www.linkedin.com/in/jacolima)
