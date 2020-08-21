#!/usr/bin/env bash

mvn clean install
docker build -t party-booking .