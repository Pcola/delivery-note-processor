# Delivery Note Processor

Automate delivery note processing for warehouse management. Reads handwritten/printed delivery notes, extracts product information via AI, and integrates with Pohoda accounting system.

## Project Overview

**Purpose:** Replace manual delivery note processing with automated OCR + intelligent matching + system integration

**Business Impact:**
- Reduces manual data entry by ~30 min/day
- Eliminates transcription errors
- Integrates directly with Pohoda warehouse system

**Learning Goal:** Enterprise Java development (Spring Boot 3, PostgreSQL, microservices thinking, cloud deployment)

---

## Tech Stack

| Layer | Technology | Why This Choice |
|-------|-----------|-----------------|
| **Backend** | Spring Boot 3.3 | Enterprise standard, mature, excellent documentation |
| **Database** | PostgreSQL | Relational data needs ACID compliance, industry standard |
| **AI/OCR** | Claude Vision API | Accurate document reading, handles various document formats |
| **Infrastructure** | AWS (EC2, RDS, S3) | Scalable, managed services, industry standard |
| **Deployment** | Docker + GitHub Actions | Consistent dev/prod environments, automated testing |

---

## Architecture

### Current: MVP (Week 1-2)
```
Photo Upload
    ↓
REST API (Spring Boot)
    ↓
OCR (Claude Vision)
    ↓
Product Matching (Fuzzy matching)
    ↓
XML Generation (Pohoda format)
    ↓
mServer Integration (Warehouse system)
```

**Decision: Monolith (Single Spring Boot JAR)**
- Current load: 10 delivery notes/day
- Single-process simplicity
- Easier debugging and testing
- When to split: 1000+ notes/day or component bottleneck

### Future: Production (Week 3-4)
- Comprehensive testing (80%+ coverage)
- Logging & monitoring (CloudWatch)
- Environment management (dev/test/prod profiles)
- Docker containerization
- AWS deployment (EC2 + RDS + S3)

---

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 14+ (or Docker)

### Setup

1. **Clone repo:**
```bash
git clone https://github.com/YOUR_USERNAME/delivery-note-processor.git
cd delivery-note-processor
```

2. **Start PostgreSQL (Docker):**
```bash
docker run -d -p 5432:5432 \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=delivery_note_db \
  postgres:15
```

3. **Run application:**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

App should start on `http://localhost:8080`

### Test API

```bash
# POST file for processing
curl -X POST http://localhost:8080/api/delivery-notes/upload \
  -F "file=@/path/to/delivery_note.jpg"

# Expected response (200 OK)
{
  "id": 1,
  "uploadedAt": "2025-01-08T10:30:00",
  "filename": "delivery_note.jpg",
  "status": "UPLOADED"
}
```

---

## Project Timeline

### Week 1-2: MVP Foundation (35 hours)
- [x] Project setup
- [x] Database schema (DeliveryNote, DeliveryItem entities)
- [x] REST API endpoint for file upload
- [x] Error handling (GlobalExceptionHandler)
- [ ] Claude Vision OCR integration
- [ ] Fuzzy product matching
- [ ] XML generation for Pohoda
- [ ] Pohoda mServer integration

### Week 3: Production-Ready (20 hours)
- [ ] Unit tests (JUnit 5, Mockito)
- [ ] Integration tests (TestContainers)
- [ ] Structured logging (Logback)
- [ ] Environment profiles (dev/test/prod)
- [ ] Database optimization (indexes, timestamps)

### Week 4: Cloud Deployment (15 hours)
- [ ] Docker containerization
- [ ] GitHub Actions CI/CD pipeline
- [ ] AWS infrastructure (EC2, RDS, S3)
- [ ] CloudWatch monitoring
- [ ] Production deployment

---

## Key Design Decisions

### Decision 1: Synchronous Processing
**Question:** Should OCR be async (user waits vs. email notification)?  
**Answer:** Synchronous (user expects immediate feedback)  
**Why:** Processing < 5 seconds, no job queue complexity needed  
**Revisit if:** Processing takes > 10 seconds or 1000s concurrent uploads

### Decision 2: Single Database
**Question:** Separate read/write databases?  
**Answer:** Single PostgreSQL instance  
**Why:** ACID compliance needed, data volume is small, complexity not justified

### Decision 3: REST over gRPC
**Question:** gRPC for better performance?  
**Answer:** REST API  
**Why:** Easier to test, standard for web apps, sufficient for this use case

---

## Learning Resources

### Official Documentation (Start Here)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Manual](https://www.postgresql.org/docs/)

### Tutorials
- [Baeldung Spring Boot Guides](https://www.baeldung.com)
- [Spring Getting Started Guides](https://spring.io/guides)

---

## Repository Structure

```
delivery-note-processor/
├── src/
│   ├── main/
│   │   ├── java/com/pohoda/processor/
│   │   │   ├── controller/       # REST endpoints
│   │   │   ├── service/          # Business logic
│   │   │   ├── model/            # JPA entities
│   │   │   └── repository/       # Data access
│   │   └── resources/
│   │       └── application.properties
│   └── test/                      # Unit & integration tests
├── pom.xml                        # Maven dependencies
├── Dockerfile                     # Container definition
├── docker-compose.yml             # Local dev environment
├── README.md                      # This file
├── LEARNING_JOURNAL.md            # Learning notes
└── ARCHITECTURE.md                # Design decisions (future)
```

---

## Git Commit Strategy

Semantic commit messages for clean history:

```bash
# Feature
git commit -m "feat: Add OCR integration with Claude Vision"

# Bug fix
git commit -m "fix: Handle null pointer in product matcher"

# Documentation
git commit -m "docs: Update README with setup instructions"

# Test
git commit -m "test: Add unit tests for XMLBuilder"
```

---

## What's Not Here (Yet)

- ❌ Docker (Week 4)
- ❌ Tests (Week 3)
- ❌ Logging (Week 3)
- ❌ AWS deployment (Week 4)
- ❌ Spring Security (out of scope for MVP)
- ❌ Message queues/Kafka (not needed at current scale)

---

## Next Steps

1. Week 1-2: Build MVP with working end-to-end flow
2. Week 3: Add comprehensive testing & logging
3. Week 4: Containerize & deploy to AWS
4. Document learnings in Learning Journal
5. Update this README with production details

---

## Author Notes

This project is a learning vehicle for enterprise Java development. The goal is not just to solve a business problem, but to understand:

- How Spring Boot abstracts complexity
- Why relational databases matter
- How to structure production code
- Cloud deployment patterns
- Testing & monitoring practices

See `LEARNING_JOURNAL.md` for detailed learning notes.

---

## License

MIT (personal project for portfolio)

---

**Status:** 🟡 In Development (Week 1-2)  
**Last Updated:** 2025-01-08
