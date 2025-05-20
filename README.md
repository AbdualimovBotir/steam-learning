# Web Platform for Improving Methodological Competence of Primary School Teachers Based on STEAM Education Technologies

## 📘 Introduction
This project aims to create an interactive web platform focused on improving the methodological competence of primary school teachers in the field of STEAM education technologies. The platform includes educational resources (articles, videos, virtual lab sessions), real-time chat, and user management features.

---

## 🎯 Goals and Objectives

### Main Goal:
Develop a user-friendly, effective, and interactive web platform for enhancing the STEAM-based methodological skills of primary school educators.

### Key Objectives:
- User registration and authentication
- Educational resource management (view, add, delete)
- Interactive virtual lab functionality
- Real-time chat between users and admin
- User profile management
- Admin dashboard for full system control

---

## 🛠️ Technologies Used

| Layer        | Technologies                                      | Purpose                        |
|--------------|---------------------------------------------------|--------------------------------|
| Frontend     | React.js, Redux, React Router, Axios, Tailwind CSS / Material UI | User Interface (UI)           |
| Backend      | Java Spring Boot, Spring Security, JWT, Spring WebSocket, Spring Mail | Server logic & security      |
| Database     | MySQL or PostgreSQL                               | Data storage                   |
| Versioning   | Git + GitHub / GitLab                             | Source control                 |
| Deployment   | AWS, Heroku, or Local Server                      | Hosting                        |

---

## 🚀 Functional Requirements

### 🔐 Authentication
- Registration with email and password
- Password hashing with bcrypt
- JWT-based session management
- Password reset via email

### 👤 User Profile
- View and edit personal data (name, email, password)
- View user role and status

### 📚 Educational Resources
- Articles, videos, PDFs, and interactive labs
- Search and filtering capabilities
- Admin can add, edit, and delete resources

### 🧪 Virtual Labs
- Create and interact with virtual lessons
- Include interactive elements like tests and tasks
- Save and display user results

### 💬 Real-time Chat
- Real-time chat using WebSocket
- Chat history view
- Admin and user communication

### 🛠️ Admin Panel
- Manage users (ban, delete, assign roles)
- Manage resources
- Monitor chat sessions

---

## 🗃️ Database Structure

| Table          | Columns                                                                 |
|----------------|-------------------------------------------------------------------------|
| `users`        | id, full_name, email, password_hash, role, status, created_at, updated_at |
| `resources`    | id, title, description, type, content_url, created_at, updated_at        |
| `virtual_labs` | id, title, description, content_json, created_at, updated_at             |
| `chat_messages`| id, sender_id, receiver_id, message_text, timestamp                      |
| `roles`        | id, role_name                                                            |

---

## 📡 API Endpoints

### Authentication
- `POST /api/auth/register` – Register new user
- `POST /api/auth/login` – Login
- `POST /api/auth/forgot-password` – Send password reset email

### User
- `GET /api/users/me` – Get user profile
- `PUT /api/users/me` – Update profile

### Resources
- `GET /api/resources` – Get list of resources
- `POST /api/resources` – Add resource (admin only)
- `PUT /api/resources/{id}` – Edit resource (admin only)
- `DELETE /api/resources/{id}` – Delete resource (admin only)

### Virtual Labs
- `GET /api/labs` – Get list of labs
- `POST /api/labs` – Add new lab (admin only)

### Chat
- `GET /api/chat/messages` – Get chat messages
- `POST /api/chat/messages` – Send a message

---

## 🔒 Security
- All requests via HTTPS
- Passwords encrypted with BCrypt
- JWT authentication & authorization
- CSRF & XSS protection
- Role-based access control (ADMIN, USER)

---

## 🎨 UI/UX Requirements
- Responsive design for mobile, tablet, and desktop
- Simple and clean interface
- STEAM-inspired color palette (blue, green, white)
- Built with Material UI or Tailwind CSS
- Dedicated page for interactive labs
- User-friendly and intuitive chat interface

---

## 🗓️ Development Timeline

| Stage                   | Task Description                        | Duration |
|-------------------------|------------------------------------------|----------|
| Requirement Analysis    | Define scope and features                | 3 days   |
| Technical Documentation | Create technical specification           | 2 days   |
| UI/UX Design            | Create mockups and design layouts        | 7 days   |
| Backend Development     | Build REST API, security, DB setup       | 14 days  |
| Frontend Development    | React app setup and API integration      | 14 days  |
| Integration & Testing   | Combine frontend and backend, test       | 7 days   |
| Deployment              | Setup and deploy on server               | 2 days   |

---

## 🗂️ Project Structure

```project_root/
├── backend/
│ ├── src/main/java/com/project/
│ │ ├── controllers/
│ │ ├── models/
│ │ ├── repositories/
│ │ ├── security/
│ │ ├── services/
│ │ └── Application.java
│ ├── src/main/resources/
│ │ └── application.properties
│ └── pom.xml
├── frontend/
│ ├── public/
│ ├── src/
│ │ ├── components/
│ │ ├── pages/
│ │ ├── redux/
│ │ ├── services/
│ │ ├── App.js
│ │ └── index.js
│ ├── tailwind.config.js
│ └── package.json
└── README.md
```

yaml


---

## 📎 Extras
- Git for version control
- Full API documentation with Swagger or Postman
- Well-commented and clean code

---

> **Note**: This project supports Uzbek primary education modernization through interactive and high-tech learning platforms.
