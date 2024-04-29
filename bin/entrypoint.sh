#!/usr/bin/env sh

echo "Starting Java application"
java -Dspring.main.lazy-initialization=true org.springframework.boot.loader.JarLauncher