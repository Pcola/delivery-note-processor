#!/bin/bash

# Spusti Spring Boot aplikaciu
echo "🚀 Starting delivery-note-processor backend..."
./mvnw spring-boot:run

# Keď sa zatvori:
echo "❌ Backend stopped"