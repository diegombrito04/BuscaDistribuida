# BuscaDistribuída

Um sistema de busca distribuída desenvolvido em Java utilizando comunicação via sockets, com servidores e cliente. Este projeto implementa a comunicação entre servidores e cliente para buscar informações a partir de arquivos JSON (`data_A.json` e `data_B.json`).

---

## Descrição do Projeto

O projeto busca simular um sistema de busca distribuída onde:
- **Servidor A** carrega o arquivo `data_A.json` e realiza buscas locais.
- **Servidor B** carrega o arquivo `data_B.json` e responde às requisições encaminhadas pelo Servidor A.
- **Cliente** realiza consultas enviando identificadores específicos para o Servidor A, que consulta localmente e também se comunica com o Servidor B para retornar respostas mais completas.

---

## Requisitos

Para rodar o projeto, você precisará:

1. **Java 17** (ou superior).
2. **Maven** para gerenciamento de dependências.
3. Os arquivos **`data_A.json`** e **`data_B.json`**, localizados na pasta `src/main/resources`.

---

## Como Executar

### 1. Configuração Inicial

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd BuscaDistribuída
2. Certifique-se de que os arquivos data_A.json e data_B.json estão localizados na pasta src/main/resources.
3. Compile o projeto:
   ```bash
   mvn clean compile
   
### 2. Executar os Servidores e o Cliente
1. Inicie o Servidor B:
   ```bash
   mvn exec:java@run-server-b
   ```
   Saída esperada no terminal:
     ```bash
    Dados do arquivo data_B.json carregados com sucesso!
    Servidor B escutando na porta 12346
2. Inicie o Servidor A:
     ```bash
   mvn exec:java@run-server-a
     ```
   Saída esperada no terminal:
     ```bash
    Dados do arquivo data_A.json carregados com sucesso!
    Servidor A escutando na porta 12345
3. Inicie o Cliente:
    ```bash
    mvn exec:java@run-client
    ```
    Durante a execução, o cliente solicitará identificadores (e.g.,5000). O Cliente enviará a consulta ao Servidor A, que buscará localmente e, se necessário, consultará o Servidor B.
    
## Estrutura de Diretórios
   ```bash
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── busca/
│   │           └── distribuida/
│   │               ├── client/
│   │               │   └── Client.java
│   │               ├── server/
│   │               │   ├── ServerA.java
│   │               │   └── ServerB.java
│   │               └── utils/
│   │                   └── JsonParser.java
│   └── resources/
│       ├── data_A.json
│       └── data_B.json
└── test/
  ```
 ## Fluxo de Comunicação
 ### Diagrama de Comunicação
 ```bash
 (Client) --(Socket)--> (Server A) --(Socket)--> (Server B)
 ```
 ## Etapas
1. O Cliente envia uma consulta para o Servidor A.
2. O Servidor A verifica no arquivo data_A.json.
3. Caso necessário, o Servidor A encaminha a requisição ao Servidor B, que busca no arquivo data_B.json.
4. As respostas são combinadas e enviadas de volta ao Cliente.

## Formato dos Dados
Os arquivos JSON possuem as chaves label e title.
 ● label: Define o rótulo associado ao identificador.
 ● title: Define o título associado ao identificador.
### Exemplo:
```bash
"5000": {
    "label": "astro-ph",
    "title": "Weak Gravity Conjecture, Black Hole Entropy, and Modular Invariance"
}
```
## Exemplo de Uso
### Entrada do Cliente:
```bash
5000
```
Saída esperada:
```bash
Rótulo: astro-ph | Título: Weak Gravity Conjecture, Black Hole Entropy, and Modular Invariance
```
## Organização do Código
### Principais Componentes
-  **Servidor A** (`ServerA.java`):
  Gerencia as buscas no data_A.json e encaminha ao Servidor B se necessário.
- **Servidor B** (`ServerB.java`):
 Responde a consultas utilizando o data_B.json.
- **Cliente** (`Client.java`):
 Envia consultas e processa respostas.
- **JsonParser** (`JsonParser.java`):
 Utilitário para manipulação de arquivos JSON.


