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

## Data models suggestion

**These models are definitely not final - please change!**

| Data Model | Property | Data Type | Description |
| --- | --- | --- | --- |
| JobPosting | id | integer | Unique identifier for the job posting |
| | title | string | Job title |
| | companyName | string | Name of the company offering the job |
| | location | string | Location of the job (e.g. city, state, country) |
| | description | string | Job description |
| | salary | decimal | Salary or salary range offered for the job |
| JobSite | id | integer | Unique identifier for the job site |
| | name | string | Name of the job site (e.g. Indeed, Glassdoor) |
| | url | string | URL of the job site |
| Technology | id | integer | Unique identifier for the technology |
| | name | string | Name of the technology (e.g. Java, React) |
| | description | string | Description of the technology |
| | skillLevel | string | Level of skill required to use the technology (e.g. beginner, intermediate, advanced) |
| Filter | id | integer | Unique identifier for the filter |
| | technology | integer | ID of the technology to filter job postings by |
| | title | string | Keywords to filter job postings by in the job title |
| | location | string | Location to filter job postings by |
| | salary | decimal | Minimum salary to filter job postings by |
| JobResult | id | integer | Unique identifier for the job result |
| | jobPostingId | integer | ID of the job posting that matches the filter criteria |
| | jobSiteId | integer | ID of the job site the posting was found on |
| | title | string | Title of the job posting |
| | companyName | string | Name of the company offering the job |
| | location | string | Location of the job (e.g. city, state, country) |
| | description | string | Job description |
| | salary | decimal | Salary or salary range offered for the job |
| User | id | integer | Unique identifier for the user |
| | username | string | User's username |
| | password | string | User's password (encrypted) |
| | email | string | User's email address |
| | firstName | string | User's first name |
| | lastName | string | User's last name |
| | createdAt | datetime | Date and time the user's account was created |
| | updatedAt | datetime | Date and time the user's account was updated the last time |


(c) 2023




