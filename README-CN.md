

# 微信公众号消息推送集成

## 📌 项目简介

该项目使用 **Spring Boot** 集成 **微信公众号** 消息推送通知功能。通过封装一系列关键流程，如：

- 公众号登录
- 消息接收与解析
- 回复消息生成与发送
- 请求签名验证
- 根据消息类型进行路由与处理

使用者只需引用该项目，配置自己的公众号密钥信息、中间件配置和路由网址，即可轻松集成微信公众账号的消息推送功能。

---

## 🚀 功能特点

- ✅ 基于 Spring Boot 架构
- ✅ 使用 **FreeMarker** 模板引擎解析消息并生成回复
- ✅ **JWT** 实现用户会话管理
- ✅ 支持 **分库分表** 设计
- ✅ 数据持久化使用 **MySQL + MyBatis**
- ✅ 使用 **Redis** 进行缓存和会话管理
- ✅ 全面支持微信公众号消息类型
- ✅ 易于扩展与定制化业务逻辑
- ✅ 与 **Elasticsearch (ES)** 集成，用于消息日志或分析

---

## 🛠️ 技术栈

| 组件           | 描述                           |
|----------------|--------------------------------|
| Spring Boot     | 基础框架                        |
| MyBatis         | 数据库操作ORM框架               |
| Redis           | 缓存/会话管理                   |
| MySQL           | 主数据库存储                   |
| JWT             | 用户身份令牌                    |
| FreeMarker      | 消息模板引擎                    |
| Elasticsearch   | 消息日志、追踪、搜索            |

---

## 📦 快速开始

### 1. 克隆项目

```bash
git https://github.com/Jonlson/wxmp-message.git
cd wechat-message-push
```

### 2. 配置项目

修改 `application.yml` 配置文件，填写你自己的公众号密钥信息、Redis/MySQL/ES 配置信息和消息路由 URL。

```yaml
wechat:
  app-id: your-app-id
  app-secret: your-secret
  token: your-token
  aes-key: your-encodingAESKey
  callback-url: https://yourdomain.com/wechat/message
```

### 3. 数据库配置

将 `sql/` 文件夹中的 SQL 脚本导入到数据库，初始化数据表。

### 4. 构建与运行

```bash
mvn clean install
java -jar target/wechat-message-push.jar
```

---

## 🧩 如何在你的项目中使用

1. **添加项目依赖**（通过 Maven 引入或将其打包成 JAR 手动引入）
2. **配置自己的 `application.yml` 配置文件**
3. **部署你的项目并启用微信回调路由**
4. 微信服务器会自动与该路由进行交互

---

## 📄 许可证

本项目开源并采用 [MIT License](LICENSE) 许可证。

---

## 🙋 联系方式 / 贡献

欢迎提交问题、PR 或建议。欢迎贡献代码！

