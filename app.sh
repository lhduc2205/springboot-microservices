#!/usr/bin/env bash

cd docker || exit

docker-compose down
docker-compose up -d

echo "Build complete"