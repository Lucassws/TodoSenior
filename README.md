## Introdução
O aplicativo de lista de tarefas foi escrito para usar Java Spring e Angular.


## Requirements

### Backend
- Docker (Developer edition) [Download](https://www.docker.com/products/docker-desktop/)
- Java JDK *Version 17.0.11* (ou superior) [Download](https://www.oracle.com/br/java/technologies/downloads/) (apenas para editar ou revisar o código)
- IntelliJ Idea IDE (ou você pode usar um alternativo) [Download](https://www.jetbrains.com/idea/download/)
- Maven *Bundle 3* [Download](https://maven.apache.org/download.cgi)

### Frontend
- Visual Studio Code [Download](https://code.visualstudio.com/download) (for only editing or reviewing codes)
- NPM(NodeJS) *Version 22.2.0* [Download](https://nodejs.org/en/download/)
- Angular CLI *Version 12.1.4* [Download](https://cli.angular.io/)

**Usei o Windows 11 como sistema operacional da máquina host.*

## Preparing the Backend to start

1. Instalar Java JDK
2. Configurar a variavel de ambiente Java_Home
3. Instalar o Maven e configurar a variavel 'Maven Path'
4. Instalar IntelliJ Idea
5. Instalar o Docker Desktop
6. Subir o Docker e executar o comando ***'docker-compose up'*** na pasta raiz do projeto
7. Confira se é a ultima versão do projeto
8. Abra a IDE
9. Abra o projeto via pom.xml
10. O IntelliJ instalará todas as dependências, mas se não; use ***maven clean*** e instale comandos para instalar todos os pacotes de dependência.

## Preparing the Frontend to start

1. Instalar NodeJS
2. Instalar VS Code
3. Instalar Angular CLI via NPM
4. Abrir a pasta do projeto via VS Code
5. Abra o terminal e use o comando ***'npm i'*** para instalar todos os pacotes
6. Depois da instalação voce pode subir a aplicação com o comando ***'ng serve'***.

## Observações
Se as portas '**8080**' ou '**4200**' da máquina host já estiverem em uso, você receberá alguns erros sobre isso. O '**4200**' é a porta padrão do Angular e o '**8080**' é a porta padrão da API Spring.

Se '**4200**' já estiver em uso, você pode usar o comando '**ng s --port < port number >**' para iniciar o FE com uma porta diferente. Ao alterar a porta FE você deve editar a classe 'TodoController' na API Spring. A classe do controlador possui um atributo que é sobre **CORS**, você verá a definição da porta FE aqui, basta atualizar o valor do número da porta.

Se '**8080**' já estiver em uso, modifique **application.properties** e adicione/edite o valor da propriedade **server.port**. (como: server.port = 8090). Em seguida, abra os arquivos do projeto FE e encontre o arquivo **envirments.ts**. Ele armazena o URL da API com o valor da porta, altere o valor da porta do URL.