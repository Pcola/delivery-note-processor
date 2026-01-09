#!/bin/bash

# Stop system PostgreSQL if running (NO PASSWORD NEEDED)
echo "Stopping system PostgreSQL..."
sudo systemctl stop postgresql 2>/dev/null

# Stop and remove old Docker container if exists
echo "Removing old Docker container..."
docker stop $(docker ps -q --filter "ancestor=postgres:15") 2>/dev/null
docker rm $(docker ps -aq --filter "ancestor=postgres:15") 2>/dev/null

# Start PostgreSQL Docker container
echo "Starting PostgreSQL Docker container..."
docker run -d -p 5432:5432 \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=delivery_note_db \
  postgres:15

echo "✅ PostgreSQL started on port 5432"