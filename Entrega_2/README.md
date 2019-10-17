
  

# Server-side programming with servlets

  

  

## Apache Tomcat Server Installation

  

  

### Download & Install Tomcat 9

  

```

  

wget http://www-us.apache.org/dist/tomcat/tomcat-9/v9.0.26/bin/apache-tomcat-9.0.26.tar.gz

  

tar xzf apache-tomcat-9.0.26.tar.gz

  

sudo mv apache-tomcat-9.0.26 /usr/local/apache-tomcat9

  

```

  

### Configure Environment Variables

  

```

  

echo "export CATALINA_HOME="/usr/local/apache-tomcat9"" >> ~/.bashrc

  

echo "export JAVA_HOME="/usr/lib/jvm/java-11-oracle"" >> ~/.bashrc

  

echo "export JRE_HOME="/usr/lib/jvm/java-11-oracle"" >> ~/.bashrc

  

source ~/.bashrc

  

```

  

  

### Setup Tomcat User Accounts

  

```

  

<!-- user manager can access only manager section -->

  

<role rolename="manager-gui" />

  

<user username="manager" password="_SECRET_PASSWORD_" roles="manager-gui" />

  

  

<!-- user admin can access manager and admin section both -->

  

<role rolename="admin-gui" />

  

<user username="admin" password="_SECRET_PASSWORD_" roles="manager-gui,admin-gui" />

  

```

  

  

### Enable Host/Manager for Remote IP

  

  

Neste passo adicionei o meu IP ao ficheiro de configuração context.xml.

  

  

### Starting Tomcat Service

  

  

```

  

cd /usr/local/apache-tomcat9

  

chmod +x ./bin/startup.sh

  

./bin/startup.sh

  

```

  

  

### ## Access Tomcat in Browser

  

  

Para aceder ao Tomcat conectamo-nos na porta 8080:

  

  

http://localhost:8080

  

  

## Criação do projeto Maven

  

Tive alguns problemas na criação deste projeto, mas com a ajuda do meu colega Vasco Ramos consegui encontrar a solução editando o path da variavel de ambiente JAVA_HOME

  

  

```

  

sudo echo "export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"" >> ~/.bashrc

  

.~/.bashrc^C

.~/.bashrc^C

  

```

  

Após instalar com sucesso a extensão do tomcat server para o VS code tive que matar o processo do tomcat ativo:

  

```

ps -ef | grep tomcat // para verificars os processos tomcat ativos

  

pkill -9 -f tomcat //para matar os processos tomcat ativos

```

  

Consegui dar deploy com sucesso do meu war file através do VS code:

  

```

INFO: Server startup in [395] milliseconds

  

[apache-tomcat9]: out 10, 2019 8:26:44 DA MANHÃ org.apache.catalina.startup.HostConfig deployWAR

  

INFO: Deploying web application archive [/home/joao/.config/Code/User/workspaceStorage/16bcf05094c08c970789814db5c2f029/adashen.vscode-tomcat/tomcat/apache-tomcat9/webapps/WebApp-1.1.war]

  

[apache-tomcat9]: out 10, 2019 8:26:44 DA MANHÃ org.apache.catalina.startup.HostConfig deployWAR

  

INFO: Deployment of web application archive [/home/joao/.config/Code/User/workspaceStorage/16bcf05094c08c970789814db5c2f029/adashen.vscode-tomcat/tomcat/apache-tomcat9/webapps/WebApp-1.1.war] has finished in [26] ms

```

  

**Quais as responsabilidades e serviços de um servlet container:**

  

A ideia base de um servlet container é gerar paginas web dinamicas baseadas no input do utilizador usando java.Um servlet contianer e responsavel pela criação,execução e destruição dos servlets mantendo o ciclo de vida de um servlet como init,service e destroy

  
  
  
  
  

No ponto h) tive algumas dificuldades em perceber como funcionava o servlet. Acabei por me basear no tutorial fornecido no guiao e em exemplos na net, sendo possivel aceder ao meu exemplo em : `http://localhost:8080/webapp-javaee7-1.1/MyFirstServlet?username=joao`. Passo o argumento username por http header e crio uma mensagem "Hello"+username.

  

Inclui deliberadamente um runtime error :

```

Object obj = null;

obj.hashCode();

```

  

No log da aplicação recebi o seguinte log:

  

```

java.lang.NullPointerException

com.ies.WEBAPP.servlet_example.doGet(servlet_example.java:26)

javax.servlet.http.HttpServlet.service(HttpServlet.java:634)

javax.servlet.http.HttpServlet.service(HttpServlet.java:741)

org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)

```

  

De seguida decidir criar um docker container para a minha app e para o tomcat server.

  

Clonar imagem tomcat:

```

docker image pull tomcat:8.0

docker image ls # it will list all images in your docker

```

Criar e iniciar tomcat container, pode ser acedido em `http://localhost:8082`:

```

docker container create --publish 8082:8080 --name my-tomcat-container tomcat:8.0

docker container ls -a # it will list all the containers

docker container start my-tomcat-container

```

Criei um dockerfile para o meu .war:

```

# we are extending everything from tomcat:8.0 image ...

FROM tomcat:8.0

MAINTAINER jmnmv12

# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat

COPY target/webapp-javaee7-1.1.war /usr/local/tomcat/webapps/

```

  

Criei um docker image file para a minha app:

```

# docker image build -t your_name/some-app _location_of_dockerfile

_docker image build -t ieswebapp .

```

  

Iniciei o container:

```

docker container run -it --publish 8081:8080 your_name/some-app-image

```

# Introduction to web apps with Spring Boot

Usando o tutorial fornecido no guiao, consegui implementar uma web app com spring boot ([https://spring.io/guides/gs/serving-web-content/](https://spring.io/guides/gs/serving-web-content/))

Numa primeira fase desenvolvi uma página web que ao receber o parâmetro "user" modificava dinamicamente o texto na página.

Numa segunda fase desenvolvi uma página web com um rest endpoint onde recebiamos os parametros no formato json.

Tive algumas dificuldades nesta fase devido ao facto de não ter alterado o url,mas após perceber o problema consegui acabar esta fase com sucesso.

**Web applications in Java can be deployed to stand-alone applications servers or embedded servers.
Elaborate on when to choose one over the other.**

Embedded servers são servidores HTTP que correm no mesmo processo que a aplicação. A nossa aplicação deve iniciar o servidor e configurar o mesmo.

Standalone application servers são servidores HTTP que correm separadamente da nossa aplicação. A configuração do servidor é realizada usando ficheiros de configuranção separados.

**Embedded servers**:
**Prós:**
 - Facilidade na troca entre hosts
 - Um único objeto para dar deploy

**Contras:**

 - Uma única exceção que não esteja controlada pode "mandar" a baixo todo o servidor
 - Existe um esforço maior para lançar updates

**Stand-alone application servers**
**Prós:**
 - Erros na aplicação não prejudicam o servidor
 - Fácil de lançar updates sem ter que reiniciar o servidor

**Contras:**

 - A performance é menor em relação aos embedded servers
 - Existe uma complexidade maior de lançamento, é necessário lançar o servidor e a aplicação separadamente

 **Give specific examples of annotations in Spring Boot that implement the principle of convention-over-configuration**
 
Através da informação fornecida pela documentação  conseguimos comprovar o principio de convention-over-configuration.
> For a lot of projects, **sticking to established conventions and having reasonable defaults is just what they (the projects) need**... this theme of convention-over-configuration now has explicit support in Spring Web MVC. What this means is that **if you establish a set of naming conventions and suchlike, you can _substantially_ cut down on the amount of configuration that is required to set up handler mappings, view resolvers, `ModelAndView` instances, etc.** This is a great boon with regards to rapid prototyping, and can also lend a degree of (always good-to-have) consistency across a codebase should you choose to move forward with it into production._ _This convention over configuration support address the three core areas of MVC - namely, the models, views, and controllers._
> 
> [Spring Docs](https://docs.spring.io/spring/docs/3.0.0.M3/spring-framework-reference/html/ch16s10.html)

 **Which annotations are transitively included in the @SpringBootApplication?**
 
 Algumas das anotações presentes em @SpringBootApplication são:
 

 - @SpringBootApplication:usada para marcar a classe main da aplicação Spring Boot
 - @EnableAutoConfiguration: autoriza configuração automática
-   @Configuration: indica que uma classe é uma classe de configuração que pode conter definições bean.    
-   @Controller: marca a classe como web controller,capaz de lidar com requests

Authors

    João Vasconcelos (nmec 88808)
