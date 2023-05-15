# TechTrekker
dies ist ein Arbeitstitel. Das Frontend ist eine Responsive Webapp die Jobdaten aus verschiedenen Lokalitäten anzeigt und Frameworks vergleicht

## Domainvorschläge
1. www.techtrekker.io (frei, 3€/Monat)


## Strukturvorschlag:
1. backend/ *Backend Server Struktur* 
2. frontend/ *Frontend Struktur*
3. docs/ *Alles an Docs und Vorlagen, Mockups und so*
4. fluff/ *code fetzen, Experimente, Spielwiese*



## Todo
### Devops
1. set up Docker file
2. Enable Routing for Apache2

### Backend
1. Entscheidung über Backend-Architektur im Bezug auf Crawler
2. Integration von SSR und eigenen Server + evt. Crawler API
3. DB Table definitions => which tables with which fields?
4. CRUD Operations 
5. Auth method
6. Serialization
7. Error Handling with Status Pages
8. SwaggerIO / OpenAPI for API docs?
9. DB versioning (https://www.liquibase.org)
10. Test Strategy

### Frontend 
1. Design erstellen
2. Use semantic release with conventional commits? (https://github.com/semantic-release/semantic-release, https://www.conventionalcommits.org/en/v1.0.0/#specification)
3. Session handling with which auth method? Prob JWT Oath2
4. Test Strategy

### Infrastructure
1. DevOps: Docker / k8s?
2. DevOps: CI / CD which pipelines? 
3. Monitoring
4. Where to host? (check https://fly.io - ionos, AWS lightsail?)
5. Automatic releases (see semantic release)
6. Set Env variables 
7. Uptime Kuma for monitoring? https://github.com/louislam/uptime-kuma

(c) 2023
