# Usar a imagem oficial do OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Definir o nome do diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR para o contêiner
COPY target/seu-arquivo.jar app.jar

# Expor a porta na qual a aplicação será executada
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
