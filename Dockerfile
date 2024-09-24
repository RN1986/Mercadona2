# Utiliser une image de base Java 17 (ou une autre version de Java)
FROM eclipse-temurin:17-jdk-alpine

# Copier tout le projet dans le conteneur
COPY . /mercadona

# Définir le répertoire de travail
WORKDIR /mercadona

# Donner les permissions d'exécution à Maven Wrapper
RUN chmod +x ./mvnw

# Builder le projet avec Maven
RUN ./mvnw clean package

# Exposer le port sur lequel l'application Spring Boot s'exécute (8080 par défaut)
EXPOSE 8080

# Démarrer l'application
CMD ["java", "-jar", "target/Mercadona-0.0.1-SNAPSHOT.jar"]
