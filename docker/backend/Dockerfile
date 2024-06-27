# Usar uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o jar da aplicação para dentro do contêiner
COPY target/*.jar app.jar

# Expor a porta que a aplicação irá usar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

