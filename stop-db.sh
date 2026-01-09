#!/bin/bash

# Stop PostgreSQL container
docker stop $(docker ps -q --filter "ancestor=postgres:15")

echo "PostgreSQL stopped"