#!/bin/bash

# Build script to integrate React frontend with Spring Boot backend

echo "Building React frontend..."
cd frontend
npm run build

echo "Copying React build to Spring Boot static directory..."
cd ..
rm -rf src/main/resources/static/*
cp -r frontend/build/* src/main/resources/static/

echo "Frontend build complete and copied to backend!"
echo "You can now run: ./mvnw spring-boot:run"
echo "The app will be available at: http://localhost:8080"
