# 💰 Expense Tracker - Full Stack Application

A full-stack expense tracking application built with **Spring Boot** (Backend) and **React.js** (Frontend).

## 🏗️ Architecture

- **Backend**: Spring Boot with Spring Security, JPA, and Kafka
- **Frontend**: React.js with Bootstrap for styling
- **Database**: H2 in-memory database
- **Message Broker**: Apache Kafka

## 🚀 How to Run

### Prerequisites
- Java 17+
- Node.js 16+
- Docker and Docker Compose

### 1. Start the Backend (Spring Boot + Kafka)

```bash
# Start all services (Spring Boot, Kafka, Zookeeper)
docker compose up -d

# Check if all services are running
docker compose ps
```

Your Spring Boot backend will be available at: http://localhost:8080

### 2. Start the Frontend (React)

```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm start
```

Your React frontend will be available at: http://localhost:3000

## 📁 Project Structure

```
task2/
├── src/                    # Spring Boot backend
│   ├── main/java/
│   │   └── com/example/task1/
│   │       ├── controller/    # REST API endpoints
│   │       ├── service/       # Business logic
│   │       ├── model/         # Data models
│   │       └── config/        # Configuration
│   └── resources/
├── frontend/              # React frontend
│   ├── src/
│   │   ├── components/    # Reusable UI components
│   │   ├── pages/         # Page components
│   │   ├── services/      # API communication
│   │   └── App.js         # Main app component
│   └── package.json
├── docker-compose.yml     # Backend services
└── README.md
```

## 🔧 API Endpoints

- `GET /api/expenses` - Get all expenses
- `POST /api/expenses` - Create new expense
- `PUT /api/expenses/{id}` - Update expense
- `DELETE /api/expenses/{id}` - Delete expense
- `GET /api/users` - Get all users
- `POST /api/users` - Create new user

## 🎯 Features

- ✅ Add, edit, and delete expenses
- ✅ Categorize expenses
- ✅ Track expense dates
- ✅ Real-time expense summary
- ✅ Responsive design
- ✅ Kafka messaging integration

## 🛠️ Development

### Backend Development
- The Spring Boot application runs on port 8080
- H2 database is accessible at http://localhost:8080/h2-console
- Kafka runs on port 9092

### Frontend Development
- React development server runs on port 3000
- Hot reload enabled for development
- API calls are made to http://localhost:8080/api

## 🔍 Troubleshooting

### Common Issues

1. **Port conflicts**: Make sure ports 8080, 3000, and 9092 are available
2. **CORS errors**: Backend CORS is configured to allow all origins
3. **Kafka connection**: Ensure Docker containers are running

### Useful Commands

```bash
# View backend logs
docker compose logs -f springboot

# View frontend logs
cd frontend && npm start

# Restart backend
docker compose restart

# Stop all services
docker compose down
```

## 📚 Learning Resources

- [React Documentation](https://react.dev/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Bootstrap Documentation](https://getbootstrap.com/)
- [Kafka Documentation](https://kafka.apache.org/documentation/)

## 🎉 Next Steps

- Add user authentication
- Implement expense categories
- Add data visualization (charts)
- Deploy to cloud platforms
- Add unit tests

Happy coding! 🚀 