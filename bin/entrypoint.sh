#!/usr/bin/env sh

# This shouldn't be required, but Docker compose lets containers
# start up before the /etc/hosts entry is actually updated
# So we need to delay the Java process getting started
while :
do
  echo "Testing for postgres"
  if ! nc -z postgres 5432; then
    echo "Found it!"
    break
  fi
done

echo "Starting Java application"
java -Dspring.main.lazy-initialization=true org.springframework.boot.loader.JarLauncher