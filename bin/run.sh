#!/usr/bin/env bash

set -eux

./gradlew build

# https://github.com/spring-guides/top-spring-boot-docker/blob/main/demo/Dockerfile
mkdir -p build/extracted
java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted
docker build -t aidang/encounter-builder -f bin/Dockerfile .

docker compose -f ./bin/compose.yaml up -d