#!/usr/bin/env bash

set -ux

./gradlew build

mkdir -p build/extracted
java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted
docker build -t aidang/encounter-builder -f bin/Dockerfile .

docker compose -f ./bin/compose.yaml up -d