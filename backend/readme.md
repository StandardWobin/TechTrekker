Techstack fürs backend

# Start the App

- Navigate to the backend directory: `cd ./backend`
- Install the dependencies: `./gradlew build`
- Start the application by running: `./gradlew bootRun`
- Example route: http://localhost:8090/api/v1/hello

# TODOS
- Automatic gradle version handling 

# Facts
- Running on Port 8090
- API structure: url/api/v1/...

## Serving Https Requests
1. [Ktor](https://ktor.io/)
    1. [... ]

## Crawler 
*teil vom anderen Server?, eigener Server?*

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


