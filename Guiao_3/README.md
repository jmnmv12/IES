# 3.1 Layered applications in Spring

Decidi usar o VS code com a extensão Spring Tools e apesar de alguns problemas a instalar a extensão acabei por conseguir executar a aplicação através do seguinte comando:

    mvn clean spring-boot:run

Acedendo ao url `http://localhost:8080/employees` inicialmente era lançado um erro "Could not marshal" , mas alterando as anotações do EmployeeController.java para `@GetMapping(value="/employees",produces="application/json; charset=UTF-8")` acabou por representar em JSON todos os employees.

 - **Be sure to provide evidence (e.g.: screenshots, JSON results view) that you have successfully used the API to insert new entries.**

Para obter a lista de todos os funcionários:

	   http://localhost:8080/employees
Para filtrar os fucionários:

     http://localhost:8080/employees/{id}
Para inserir novo funcionário:

![Inserção de um novo funcionário](https://i.imgur.com/dcUge6N.png)
    Para editar os fields de um funcionário:
    ![Edição do funcionário com id 3](https://i.imgur.com/4Rgu6PP.png)
   Para apagar um funcionário:
   ![Antes de apagarmos o funcionário com id 2](https://i.imgur.com/et3Oy3Q.png)
![Apagar o funcionário com id 2](https://i.imgur.com/kqDcK4X.png)![Resultado da listagem dos funcionários após apagar o funcionário com id 2](https://i.imgur.com/3OqfkDx.png)

 - **What happens to your data when the application is stopped and restarted? How could you change
that behavior?**

Ao parar a nossa aplicação e após reiniciar conseguimos concluir que todos os dados que tinhamos modificado/inserido despareceram e a base de dados voltou ao seu estado inicial com apenas 2 entradas.
Para modificar esse comportamente teriamos que desacopular a base de dados da aplicação para que quando a nossa aplicação pare a informação nao seja alterada e possa continuar a ser acedida.

![LoadDatabase.java](https://i.imgur.com/Qj3sjZO.png)
Através desta imagem conseguimos perceber que sempre que a aplicação é iniciada a base de dados começa sempre com as mesmas 2 entradas.

 - **Create a layered architecture view (diagram), displaying the key abstractions in the solution, in particular:entities, repositories and REST controllers.**

![Layered diagram](https://i.imgur.com/RUFw9Q9.png)

 - **Describe the role of the elements modeled in the previous point.**
 
A camada "Employee Repository" tem como objetivo armazenar e disponibilizar toda a informação associada à nossa aplicação. A camada "Employee Controller" disponibiliza-nos operações de manipulação,representação e elminação dos dados através dos métodos GET,PUT,DELETE
 e POST. Além disso disponibiliza-nos através do url http://localhost:8080/employees a listagem dos employees e o resutado das operações em formato JSON.
 
Além disso temos a entidade Employee que é utilizada para formalizar a representação dos dados e é usada pela camada "Employee Repository" para armazenar dados do tipo Employee e pela camada "Employee Controller " para criação e alteração de dados. Usando a anotação `@Entity` o JPA irá establecer a ligação entre a entidade e uma tabela de mesmo nome na base de dados.



sudo docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=demo
-e MYSQL_USER=demo -e MYSQL_PASSWORD=password -p 3306:3306 -d mysql/mysql-
server:5.7


 - **Why is that the Employee entity does not have getters and setters defined? (tip: Lombok)**

Ao analisar a entidade Employee conseguimos perceber que não existem getters e setters definidos, apesar de serem utilizados na classe `EmployeeController.java`. Isso deve-se ao facto da existência da anotação `@Data` do lombok que cria no nosso ficheiro compilado (.class) os getters e setters para todos os atributos definidos na classe.



# 3.2 Accessing JPA Data with REST interface


Para criar uma instância do servidor MySQL executei o seguinte comando:

    sudo docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=password -p 3306:3306 -d mysql/mysql-server:5.7

Para criar um novo projeto Spring boot abro a **Command Palette** (Ctrl+Shift+P) e escrevo `Spring Initializr`.

