# Utiliser une image de base Java 17 (ou la version que tu préfères)
FROM eclipse-temurin:17-jdk-alpine

# Copier le projet dans le container
COPY . /app

# Définir le répertoire de travail
WORKDIR /app

# Builder le projet
RUN ./mvnw clean package

# Exposer le port de l'application
EXPOSE 8080

# Démarrer l'application
CMD ["java", "-jar", "target/Mercadona-0.0.1-SNAPSHOT.jar"]
