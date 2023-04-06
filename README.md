# Pleno

**OBJETIVO:** Criar um sistema de autenticação.

**Descrição do Projeto**

- Criar um endpoint que receba os dados de um usuário:
    - id
    - nome
    - telefone (opcional)
    - email (único)
    - data de criação
    - data de atualização
    - senha
- Criar um endpoint que receba o email e senha do usuário e retorne um token (JWT)
- Criar endpoint que liste os usuários da plataforma com paginação retornando apenas id e nome
- Criar um endpoint que o usuário possa atualizar suas informações (utilizando o token do login)

**Critérios de avaliação:**

- A aplicação cumpre os requisitos do teste.
- O repositório possui instruções de como rodar o projeto.
- Padrão de arquitetura definido.
- Performance.
- Legibilidade do código.

**Pontos de atenção:**

- O Teste deve ser escrito na linguagem JAVA com Springboot.
- Fique a vontade para decidir a tecnologia de banco de dados utilizada.
- Fique a vontade para adicionar bibliotecas externas ao projeto.