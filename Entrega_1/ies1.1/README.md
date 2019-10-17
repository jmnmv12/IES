
# Instalação

Corri o comando mvn --version e tive alguns problemas pois ainda não tinha o maven instalado.

De seguida após alguma pesquisa encontrei o comando sudo apt-get install maven e após a execução do mesmo cosnegui isntaçar o Maven.

  

# Guião de Introdução

Analisei o guião de introdução e começei por excecutar o programa inicial que me instalou uma serie de dependencias e criou-me uma serie de ficheiros.

Após alguma análise consegui perceber que o pom.xml contêm toda a configuração essencial para o projeto.


 1. mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4  -DinteractiveMode=false (gera um projeto consoante os goals passados)
 2. mvn package (faz o build do projeto)
 3. 1.  mvn clean dependency:copy-dependencies package (fases e goals podem ser executados em sequência)

# App do IPMA

 1. curl http://api.ipma.pt/open-
data/forecast/meteorology/cities/daily/1010500.json | json_pp
 2. mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4  -DinteractiveMode=false (Gerar Projeto WeatherRadar)

Tive alguns problemas a instalar as dependencias e após alguma pesquisa descobri que nao tinha importado as mesmas.
Após essa correção consegui resolver o exercíco rapidamente.

## O que é um Maven Goal?
Quando o Maven inicia a build de um projeto, percorre uma sequência de fases e executa os goals associados a cada fase. Um goal representa uma tarefa especifica que contribui para a build do projeto e pode estar associado a 1 ou mais fases.

## Quais os principais “maven goals” e a respetiva sequência de invocação?

Os principais maven goals por sequência de invocação são:

 1. compiler:compile associado à fase compile
 2. compiler:testCompile associado à fase test-compile
 3. surefire:test associado à fase test 
 4. install:install associado à fase install
 5. jar:jar e war:war associados à fase package

