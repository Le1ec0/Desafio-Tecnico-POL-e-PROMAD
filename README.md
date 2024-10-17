# Desafio POL e PROMAD

> Esse projeto teve como foco estruturar um banco de dados relacional para consulta de processos, inserção de novos processos, exclusão de processos já existentes e inserção de réus aos processos.

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas para as seguintes tarefas:

- [x] Banco de dados relacional - Concluído
- [x] API - Concluído
- [x] Testes - Concluído
- [ ] Terminar a implementação do Swagger
- [ ] Aprimorar a segurança dos dados

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão mais recente de `<SDK Java / PostgreSQL / Postman>`
- Você tem uma máquina `<Windows>`.

## 🚀 Instalando <Desafio POL e PROMAD>

Para executar o <Desafio POL e PROMAD>, siga estas etapas:

Windows:

Instale o Java SDK, PostgreSQL e o Postman.

## ☕ Usando <Desafio POL e PROMAD>

Para executar o projeto <Desafio POL e PROMAD>, siga estas etapas:

Crie o banco de dados no PostgreSQL usando o comando SQL (ou crie manualmente usando o pgAdmin)

```

CREATE DATABASE processo_db;

```

Para executar a API navegue para a pasta "processo-service" e execute o comando:

```

mvn spring-boot:run

```

OBS: As credenciais para acesso ao banco são:
Usuário: postgres
Senha: leandro

As credenciais estão no arquivo `application.properties`.

O arquivo para importação e utilização dos endpoints está na raiz do projeto com o nome de `Processos.postman_collection.json`.

Para listar os processos utilize o endpoint http://localhost:8080/processos/Listar

Para criar um novo processo utilize o endpoint http://localhost:8080/processos/Criar?numero_processo=12 (utilizando 12 como o número do novo processo, à exemplo)

Para deletar um processo existente utilize o endpoint http://localhost:8080/processos/Deletar?numero_processo=12 (utilizando 12 como o número do processo existente, à exemplo)

Para adicionar um réu à um processo utilize o endpoint http://localhost:8080/processos/InserirReu?numero_processo=12453453&nome=leandro (utilizando 12 como o número do processo existente e o nome leandro, à exemplo)

## 📫 Contribuindo para o <Desafio POL e PROMAD>

Para contribuir com <Desafio POL e PROMAD>, siga estas etapas:

1. Faça um fork este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

## 🤝 Colaboradores

Primeiramente agradeço a oportunidade de fazer parte desse processo seletivo. Essa foi uma ótima oportunidade de colocar meus conhecimentos em prática.

Para mais contato:

<table>
  <tr>
    <td align="center">
      <a href="#" title="[Leandro I. S.](https://www.linkedin.com/in/leandro-izidoro-a5143471/)">
        <img src="https://media.licdn.com/dms/image/v2/C4D03AQGOAPucBMaiIw/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1643297539246?e=1733356800&v=beta&t=Gh_j5J2M9VR7eL0p1Lu9Bkf2zm0AB0bEd3OkTcCSDpU" width="100px;" alt="Leandro I. S."/><br>
        <sub>
          <b>Leandro I. S.</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

Saudações à equipe POL e PROMAD, que me guiaram até aqui e proporcionaram essa oportunidade.

## 😄 Seja um dos contribuidores

## 📝 Créditos

Todos os créditos estão reservados à POL e PROMAD.
