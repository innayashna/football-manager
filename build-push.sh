#!/bin/sh

mvn clean install
docker build -t innayashna/football-manager:latest .
docker push innayashna/football-manager:latest