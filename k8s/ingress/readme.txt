How to create Ingress?

1. ingress
  1.1. Enable ingress with: minikube addons enable ingress
    1.1.1 There is a bug for this error for combination of m1 docker and kubernetes
  1.2. Enable domain access to kubernetes dashboard (dashboard-ingress.yaml)
  1.3. map IP address that ingress defined with the domain namespace - (IPdefined k8s.dashboard.com)

Helpful links: https://www.bogotobogo.com/DevOps/Docker/Docker_Kubernetes_MongoDB_MongoExpress.php
