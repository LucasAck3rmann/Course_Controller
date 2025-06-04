# Sistema de Controle de Cursos
## Sobre o Projeto
Este repositório contém a implementação de um **Sistema de Controle de Cursos**, desenvolvido em Java como parte do desafio final do módulo de Backend do curso 3035Teach. O sistema permite o cadastro e gerenciamento de **alunos, cursos e matrículas**, utilizando conceitos de programação orientada a objetos, design patterns e banco de dados relacional.

A aplicação é totalmente interativa via **console**, sem necessidade de interface gráfica.

## Funcionalidades
- **Gerenciamento de Alunos:**
  - Cadastrar novos alunos (nome, e-mail e data de nascimento).
  - Listar todos os alunos cadastrados.
  - Buscar aluno pelo e-mail.

- **Gerenciamento de Cursos:**
  - Cadastrar novos cursos (nome, descrição e carga horária).
  - Listar todos os cursos disponíveis.
  - Buscar cursos pelo nome (busca parcial ou completa).

- **Gerenciamento de Matrículas:**
  - Realizar matrículas de alunos em cursos.
  - Listar todas as matrículas, exibindo nome do aluno, nome do curso e data da matrícula.


## Tecnologias e Ferramentas
- **Java SE 8+** (Recomendado Java 8 ou superior)
- **PostgreSQL** como banco de dados relacional
- **JDBC** para conexão com o banco
- **pgAdmin 4** para gerenciamento do banco

##  Estrutura do Banco de Dados
O sistema utiliza um banco de dados PostgreSQL com as seguintes tabelas:

- **`student`**
  - `id` (chave primária)
  - `name`
  - `email`
  - `birth_date`

- **`course`**
  - `id` (chave primária)
  - `name`
  - `description`
  - `workload`

- **`enrollment`**
  - `id` (chave primária)
  - `student_id` (chave estrangeira → student)
  - `course_id` (chave estrangeira → course)
  - `enrollment_date`

## Como Executar o Projeto

1. Instale o Java (preferencialmente versão 8 ou superior).
2. Instale o PostgreSQL e crie um banco de dados chamado **`course_system`**.
3. Execute os comandos SQL abaixo no pg4Admin para criar as tabelas:

```sql
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    workload INT NOT NULL
);

CREATE TABLE enrollment (
    id SERIAL PRIMARY KEY,
    student_id INT REFERENCES student(id),
    course_id INT REFERENCES course(id),
    enrollment_date DATE NOT NULL
);
```
<div align="center">
  <a href="https://www.3035tech.com/" target="_blank"
  ><img
    src="https://d9hhrg4mnvzow.cloudfront.net/lp.3035tech.com/96c1669d-logo-teach-horiz-branco_1000000000000000000028.png"
    style="
      width: 100px;
      padding: 10px;
      border-radius: 10px;
    "
     target="_blank"
  /></a>
</div>
