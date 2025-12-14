# Kafka High Throughput Lab (Java, Local)

## Overview

This project demonstrates **high-throughput message processing** using **Apache Kafka** with **Java & Spring Boot** in a fully local environment.

The goal is to simulate **millions of concurrent messages**, observe **producer throughput**, **consumer lag**, and understand **Kafka partition & consumer group scaling** without using cloud infrastructure.

---

## 🎯 Goals

* Produce **millions of messages** to Kafka efficiently
* Consume messages using **consumer groups & parallelism**
* Observe:

  * Throughput (messages/sec)
  * Consumer lag
  * Partition assignment
* Apply **real-world Kafka tuning configurations**

---

## 🧱 Architecture

```text
Spring Boot Producer  --->  Kafka (Docker)
                               |
                               v
                    Spring Boot Consumer Group
```

* Kafka runs locally using Docker
* Producer and Consumer are separate Spring Boot applications
* Topic is partitioned to enable parallel consumption

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot 3
* Spring Kafka
* Apache Kafka
* Docker & Docker Compose
* Maven

---

## 🐳 Kafka Setup (Local)

Kafka is started using Docker Compose:

```bash
docker-compose up -d
```

Topic configuration:

* Topic name: `high-throughput-topic`
* Partitions: **12**
* Replication factor: **1**

---

## 🚀 Producer Configuration (High Throughput)

Key Kafka producer settings:

```yaml
batch-size: 32768
linger-ms: 10
compression-type: lz4
acks: 1
buffer-memory: 67108864
```

These settings enable:

* Message batching
* Reduced network overhead
* High throughput with acceptable durability trade-offs

Producer uses **multi-threading** to push load to Kafka.

---

## 👥 Consumer Configuration

* Consumer Group: `high-throughput-group`
* Concurrency: **4**
* Auto offset reset: `earliest`

Each consumer thread processes messages in parallel across partitions.

---

## 📊 Test Results

### 1 Million Messages

* Time: ~6 seconds
* Lag: 0

### 3 Million Messages

* Producer: multi-threaded
* Lag initially increases, then drops to **0**
* All messages successfully consumed

> Results depend on local machine resources.

---

## 🧠 Key Learnings

* Kafka throughput heavily depends on **producer batching**
* Partition count determines **maximum consumer parallelism**
* Consumer groups allow **horizontal scaling**
* Lag is expected under load and is not a failure
* Kafka can handle burst traffic gracefully

---

## 📁 Project Structure

```text
kafka-high-throughput-lab/
│
├── docker-compose.yml
├── producer-service/
├── consumer-service/
└── README.md
```

---

## ✅ When to Use This Architecture

* Event-driven systems
* Real-time analytics
* Notification & messaging platforms
* Log and stream processing

---

## ❌ When NOT to Use Kafka

* Simple CRUD applications
* Low-volume synchronous workflows

---

## 🔚 Conclusion

This project provides a practical, production-inspired Kafka setup suitable for learning, experimentation, and technical interviews.
