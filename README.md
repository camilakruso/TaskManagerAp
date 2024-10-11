Descrição do Projeto

O Task Manager App é um aplicativo Android para gerenciamento de tarefas. Ele permite que os usuários criem, editem, listem e excluam tarefas. O objetivo é ajudar as pessoas a organizarem suas atividades diárias, proporcionando uma ferramenta simples e intuitiva para acompanhamento de tarefas. O armazenamento das tarefas é realizado localmente no dispositivo, utilizando um banco de dados SQLite.

Autores:

Camila Maciel Caruso Silva

Instruções de Instalação:

1. Pré-requisitos:
    Android Studio instalado (disponível em https://developer.android.com/studio)
    Dispositivo Android ou emulador configurado no Android Studio

2. Passos para Instalação:
    Faça o download ou clone o projeto para o seu computador:     
   https://github.com/camilakruso/TaskManagerAp.git
     
    Abra o Android Studio e selecione "Open an existing project".
    Navegue até a pasta onde o projeto foi baixado e selecione a pasta do projeto.
    Configure um emulador Android ou conecte um dispositivo físico com a depuração USB ativada.
    Clique no botão "Run" ou use o atalho `Shift + F10` para compilar e executar o aplicativo.

Explicação Detalhada das Funcionalidades Desenvolvidas

1. Tela Principal (MainActivity):

    Contém botões para acessar as funcionalidades de criar, atualizar, listar e excluir tarefas.

2. Criar Tarefa (CreateTaskActivity):

    Permite ao usuário adicionar uma nova tarefa com nome, ID e status.
    Realiza validações para garantir que o nome não esteja vazio e que o ID seja único.

3. Atualizar Tarefa (UpdateTaskActivity):

    Permite ao usuário modificar o nome e o status de uma tarefa existente.
    Verifica se o ID fornecido é válido e se a tarefa existe no banco de dados.

4. Listar Tarefas (ListTasksActivity):

    Exibe uma lista de todas as tarefas armazenadas.
    Caso não haja tarefas, uma mensagem informativa é exibida.

5. Buscar Tarefa por ID (ListTaskByIdActivity):

    Permite buscar uma tarefa específica pelo seu ID.
    Exibe os detalhes da tarefa ou uma mensagem de erro caso o ID não seja encontrado.

6. Excluir Tarefa (DeleteTaskActivity):

    Permite que o usuário remova uma tarefa do banco de dados.
    Solicita a confirmação do usuário antes de excluir a tarefa.

