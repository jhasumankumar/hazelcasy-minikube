# Getting Started

### Reference Documentation

This is a simple spring boot project that uses Hazelcast multicast to join cluster.

To deploy application and create cluster Kubernetes minikube is used.
Use minikube.sh to create docker image and deploy it on minikube cluster with 2 hazelcast instances.


Use minikube service party-booking --url to get URL
Now your pod is up and running use the url returned from above to use swagger 
<URL>/hazelcast/swagger-ui.html

http://192.168.201.100:31091/hazelcast/swagger-ui.html
Use POST to insert in cache using Write-Behind-Cache or Write-Through-Cache based on <b>write-delay-seconds</b> configuration in Hazelcast
and GET to fetch it

