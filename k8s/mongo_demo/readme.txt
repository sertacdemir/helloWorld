How to create basic mongo environment?
1. Create a secret (mongo-secret.yaml)
  1.1. Opaque is used: basic username/password
  1.2. Values should be base64 encoded
    1.2.1 Terminal can be used to this. Ex: echo -n 'username' | base64
2. Create mongo db (mongo.yaml)
  2.1. Initialize root username/pass
    2.1.1. Reference credentials from secret
  2.2. Open necessary ports
  2.3. Create internal service
3. Create mongo-express to manage db(mongo-express.yaml)
  3.1. Create config map to store mongo db address(mongo-configmap.yaml). Username and pass is already in secret and will be used from there
  3.2. Crete a external service with LoadBalancer type and node port
  3.3. For minikube to have external ip execute: minikube service mongo-express-service. It will create internal and external address that can be seen from the command output
    3.3.1 Local access through: http://127.0.0.1:52019
4. Create persistant volume for mongo date to be persistent(mongo_pv.yaml). This step should be executed before the second environment
  4.1. For this exercise local store will be used.
  4.2. Normally I should use persistent but I will use statefull set as I will not scale up.


Helpful links: https://www.bogotobogo.com/DevOps/Docker/Docker_Kubernetes_MongoDB_MongoExpress.php
