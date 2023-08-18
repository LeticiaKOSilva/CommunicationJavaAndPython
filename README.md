# CommunicationJavaAndPython
### ->Criação de 2 sockets, onde o servidor é feito em java e o cliente em python, o objetivo e passar dados entre os dois.

- O programa foi feito utilizando as linguagens Python e Java;

- Na parte em java foram criadas 6 classes:
    - Server: que inicializa, coleta os dados e cria o servidor e envia os dados para o cliente;
    - InputAndOutput: onde é utilizado a classe JOptionPane para ler os dados e para exibir
      as mensagens em uma interface;
    - Student: instancia os dados dos estudantes (matricula,nome,idadde);
    - Team: instancia os dados da turma (id,nome,ano) e o vetor de objetos com os estudantes da turma;
    - Constant: onde fica tudas as mensagens exibidas na classe InputAndOutput;
    - Validation: realiza a validação dos dados tanto da classe Student quando da Team antes de mandar
       os dados para o servidor.
      
- Na parte do Python foi criado um único arquivo:
  - Client: que cria o socket cliente, recebe os dados do servidor e os imprime em uma interface em Python.
