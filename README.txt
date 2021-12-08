Instruction :

1. Download project file

2. Run docker compose

C:\project\> docker-compose up -d

- Then three components will be running
  a. mongodb (localhost:27107)
  b. backend-server (localhost:8080)
  c. frontend-server (localhost:8081)

3. By using browser, go to http://localhost:8081
- customerList and itemList are preloaded

4. Select customer, set items' quantities, and click "Purchase Submit" button
- You can see purchase info

Note :
Postman collection (for API test) is located in payment\postman




