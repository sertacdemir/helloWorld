import requests

response = requests.get("https://gitlab.com/api/v4/users/sertacdemir/projects")

print(response.json())
print(type(response.json()))
print(response.json()[0])

my_projects = response.json()

for project in my_projects:
    print(f"name: {project['name']} url is  {project['web_url']}")
