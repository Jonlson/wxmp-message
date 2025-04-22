
# WeChat Official Account Message Push Integration

## 📌 About

This project integrates **WeChat Official Account (公众号)** message push notifications using **Spring Boot**. It simplifies the development and integration of WeChat message push functionality by encapsulating key processes such as:

- Official account login
- Message receiving and parsing
- Response message generation and sending
- Request signature verification
- Routing and processing based on message type

Users only need to reference this project, configure their own official account credentials, middleware settings, and routing URLs — and you're all set!

---

## 🚀 Features

- ✅ Spring Boot-based framework  
- ✅ Message parsing and response using **FreeMarker** templates  
- ✅ User/session management via **JWT**  
- ✅ Multi-database, table-sharding support  
- ✅ Data persistence using **MySQL + MyBatis**  
- ✅ Caching and session management with **Redis**  
- ✅ Full support for WeChat Official Account message types  
- ✅ Easy extension and custom business logic  
- ✅ Integrated with **Elasticsearch (ES)** for message logging or analytics

---

## 🛠️ Tech Stack

| Component       | Description                    |
|----------------|--------------------------------|
| Spring Boot     | Base framework                 |
| MyBatis         | ORM for DB access              |
| Redis           | Cache/session support          |
| MySQL           | Primary data storage           |
| JWT             | User identity token            |
| FreeMarker      | Template engine for messages   |
| Elasticsearch   | Logging, tracking, search      |

---

## 📦 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/wechat-message-push.git
cd wechat-message-push
```

### 2. Update Configuration

Modify the following configuration files to fit your official account:

- `application.yml`:
  - WeChat App ID and Secret
  - Redis/MySQL/ES configs
  - Message routing URLs

```yaml
wechat:
  app-id: your-app-id
  app-secret: your-secret
  token: your-token
  aes-key: your-encodingAESKey
  callback-url: https://yourdomain.com/wechat/message
```

### 3. Database Setup

Import the SQL schema provided in the `sql/` folder to set up your database structure.

### 4. Build & Run

```bash
mvn clean install
java -jar target/wechat-message-push.jar
```

---

## 🧩 How to Use in Your Project

1. **Add project dependency** (via Maven or package manually as a JAR)
2. **Configure your own `application.yml`**
3. **Deploy your project with WeChat callback routing** enabled
4. WeChat servers will communicate with your route automatically

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 🙋 Contact / Contribution

Feel free to submit issues, PRs, or suggestions. Contributions are welcome!

