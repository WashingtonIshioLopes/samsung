# Usar a imagem oficial do Node.js como base
FROM node:20.14.0

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar o package.json e package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instalar as dependências
RUN npm install

# Copiar todos os arquivos da aplicação para o diretório de trabalho
COPY . .

# Construir a aplicação
RUN npm run build

# Expôr a porta na qual a aplicação vai rodar
EXPOSE 5173

# Comando para iniciar a aplicação
CMD ["npm", "run", "preview"]
