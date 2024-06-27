# Use uma imagem base do Maven com a versão do Java desejada
FROM maven:3.8.5-openjdk-17-slim AS build

# Configuração do diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências do Maven de forma separada
# Isso permite que as dependências sejam cacheadas separadamente, a menos que o pom.xml tenha sido alterado
COPY /dist/app/pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte do projeto e compila a aplicação
COPY /dist/app/src ./src
RUN mvn package -DskipTests

# Segunda fase para criar a imagem final, usando apenas a JRE para reduzir o tamanho
FROM openjdk:17-jdk-slim

# Copia o arquivo JAR construído da fase de compilação para a nova imagem
COPY --from=build /app/target/springboot-0.0.1-SNAPSHOT.jar /app/app.jar

# Expõe a porta 8080 do contêiner Docker
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner iniciar
CMD ["java", "-jar", "/app/app.jar"]

