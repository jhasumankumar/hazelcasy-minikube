#!/usr/bin/env bash
minikube stop
minikube delete
minikube start
eval $(minikube docker-env)
mvn clean install
docker build -t party-booking .
kubectl create -f manifest.yaml
kubectl expose deployment party-booking --type=NodePort --port=8080
##minikube service party-booking --url
##http://<URL>/hazelcast/swagger-ui.html#/user-service/findByIdUsingGET


##kubectl expose deployment party-booking --type=NodePort --name=party-booking-service
##kubectl delete services party-booking-service
##kubectl describe services party-booking-service
##kubectl get pods --selector="app=party-booking" --output=wide
##kubectl delete deployment --all

##kubectl get pods
##kubectl logs <POD>