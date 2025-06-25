eureka dashboard=
![EurekaDashboard](https://github.com/user-attachments/assets/58579519-1c62-4e45-8021-9daf9d9bf587)

postman collection=
[smart-parking-management-system.postman_collection.json](smart-parking-management-system.postman_collection.json)

🚕🚓🚗 Smart Parking Management System (SPMS)

🧠 Overview
The Smart Parking Management System (SPMS) is a cloud-native, microservice-based backend system designed to streamline urban parking operations. It empowers users to find and reserve parking in real-time, allows space owners to manage availability, and handles mock payments — all while reducing traffic congestion and improving urban mobility.

🚀 Technologies Used
    🔧 Spring Boot – Core framework for building backend microservices.
    🌐 Spring Cloud Config – Centralized configuration server for managing shared properties.
    🧭 Spring Cloud Eureka – Service registry for dynamic service discovery.
    🚪 Spring Cloud Gateway – API Gateway for intelligent routing and request filtering.
    🔐 Spring Security + JWT – Secure login and role-based access with JSON Web Tokens.
    🗄️ MySQL – Relational database for storing microservice data.
    🧪 Postman – Tool for API testing, request automation, and validation.
    🛠️ Maven – Build and dependency management tool for Spring Boot projects.

Make sure you have these installed:
    🧩 Java 17 or above
    🐘 Maven
    🐬 MySQL Server
    📫 Postman (for testing)
    💻 Git (to clone/push the project)
    ☁️ Internet connection (for dependency download)

🚀 How to Run the Project
    Start Eureka Server
🔗 Open in browser: http://localhost:8761

    Start Config Server
🔗 Open in browser: http://localhost:8888

    Start All Microservices:

👤 User Service → Runs on http://localhost:8081

🚗 Vehicle Service → Runs on http://localhost:8082

🅿️ Parking Service → Runs on http://localhost:8084

💳 Payment Service → Runs on http://localhost:8085

Start API Gateway
🔗 Open in browser: http://localhost:8080

Test Endpoints

📫 Use Postman and import the provided collection:
    postman collection=
[smart-parking-management-system.postman_collection.json](smart-parking-management-system.postman_collection.json)


⌛Test all API endpoints through the API Gateway.





