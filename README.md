# get initial admin password

kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath='{.data.password}' | base64 -d; echo

# get postgres password
port: 5432
url: postgres-postgresql.data.svc.cluster.local

### admin password
POSTGRES_ADMIN_PASSWORD: 7jmSwNCcF7

### get the password for "app" run:
POSTGRES_PASSWORD=app123


### To connect to your database from outside the cluster execute the following commands:
kubectl port-forward --namespace data svc/postgres-postgresql 5432:5432 &
    PGPASSWORD="$POSTGRES_PASSWORD" psql --host 127.0.0.1 -U app -d appdb -p 5432

## Build & load into kind (no registry needed):

### creates a separate builder if you don't have one
docker buildx create --use --name multi || docker buildx use multi

### Repeat for each service with unique image names
docker buildx build --platform linux/arm64 -t svc-a:local --load ./services/service-a
docker buildx build --platform linux/arm64 -t svc-b:local --load ./services/service-b
 
### Then load them into the kind cluster nodes:
kind load docker-image svc-a:local --name lagerr
kind load docker-image svc-b:local --name lagerr


# School Project (Kind + Argo CD + Spring Boot services)

This repo contains three Java 21 / Spring Boot 3.5 microservices (`notification`, `order`, `lager`) + Postgres + Prometheus/Grafana via Argo CD.
See `apps/app-of-apps.yaml` to register the whole stack in Argo CD.

## Quick start
1. Create `kind` cluster and NGINX ingress (see earlier instructions).
2. Install Argo CD and expose it via ingress.
3. Push this repo to Git, update `repoURL` fields in `apps/*/*.yaml` to your repo.
4. `kubectl apply -n argocd -f apps/app-of-apps.yaml`
5. Argo will deploy Postgres, Monitoring, shared ingress/secret, and all services.
6. Browse:
   - Grafana: http://grafana.127.0.0.1.nip.io
   - Prometheus: http://prometheus.127.0.0.1.nip.io
   - Services: http://services.127.0.0.1.nip.io/order/api/order (etc.)

## Build & load images for kind
```bash
docker buildx build --platform linux/arm64 -t notification:local --load ./services/notification
docker buildx build --platform linux/arm64 -t order:local        --load ./services/order
docker buildx build --platform linux/arm64 -t lager:local        --load ./services/lager
kind load docker-image notification:local --name school
kind load docker-image order:local        --name school
kind load docker-image lager:local        --name school
```
