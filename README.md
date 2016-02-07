
This is a simple Dropwizard application which interacts with MongoDB

Steps:
=====

1. Clone this Repo

2. Install MongoDB

3. Run with mvn compile:exec java

4. Use CURL or Rest Chrom plugin to publish the data

Service: http://localhost:8080/employee/publish
JSON content:
Method: Post
Header: Content-Type: application/json
{
    "name": "bala",
    "designation": "programmer"
}

5. Access http://localhost:8080/employee/fetch from the browser to check the content

[
{
"id": "29400a1a-f6c7-491a-8140-5e274c4755a1",
"name": "bala",
"designation": "programmer"
},
{
"id": "f7664d86-6fe4-45a6-99bb-59cfec636b80",
"name": "arun",
"designation": "architect"
}
]
