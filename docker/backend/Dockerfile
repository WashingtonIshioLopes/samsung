# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY springboot-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponha a porta que a aplicação vai usar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]