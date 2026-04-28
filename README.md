# 🛒 E-Commerce API (Spring Boot)

## 📌 Objetivo do Projeto

Este projeto consiste em uma API REST desenvolvida com Spring Boot para gerenciamento de um sistema de e-commerce.  
O objetivo é permitir o cadastro, consulta, atualização e remoção de produtos, categorias e fornecedores, além de oferecer filtros para busca avançada de produtos.

A aplicação simula a base de um sistema real de loja online, aplicando conceitos de arquitetura em camadas, validação de dados e persistência com JPA/Hibernate.

---

## 🧱 Entidades do Sistema

### 📦 Product

Representa os produtos disponíveis na loja.

**Atributos:**
- id  
- name  
- price  
- quantity  
- supplier (ManyToOne)  
- category (ManyToOne)  

🔎 Possui filtros por preço:

- maior que (greater than)  
- menor que (less than)  
- entre (between)  

---

### 🗂 Category

Representa as categorias dos produtos.

**Atributos:**
- id  
- name  
- description  

🔎 Permite busca por nome (case insensitive e parcial).

---

### 🏭 Supplier

Representa os fornecedores dos produtos.

**Atributos:**
- id  
- cnpj  
- name  
- address  
- email  
- status  

🔎 Permite busca por nome (case insensitive e parcial).

---

## ⚙️ Funcionamento da API

A API segue arquitetura REST com as seguintes operações:

---

### 🔹 Categories

- `GET /categories` → lista todas as categorias  
- `GET /categories/{id}` → busca por ID  
- `POST /categories` → cria categoria  
- `PUT /categories/{id}` → atualiza categoria  
- `DELETE /categories/{id}` → remove categoria  
- `GET /categories/by-name/{name}` → busca por nome  

---

### 🔹 Products

- `GET /products` → lista todos os produtos  
- `GET /products/{id}` → busca por ID  
- `POST /products` → cria produto  
- `PUT /products/{id}` → atualiza produto  
- `DELETE /products/{id}` → remove produto  

🔎 Filtros de preço:

- `GET /products/price/greater-than/{price}`  
- `GET /products/price/less-than/{price}`  
- `GET /products/price/between?min=&max=`  

---

### 🔹 Suppliers

- `GET /suppliers` → lista fornecedores  
- `GET /suppliers/{id}` → busca por ID  
- `POST /suppliers` → cria fornecedor  
- `PUT /suppliers/{id}` → atualiza fornecedor  
- `DELETE /suppliers/{id}` → remove fornecedor  
- `GET /suppliers/by-name/{name}` → busca por nome  

---

## 🧠 Tecnologias Utilizadas

- Java 17+  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Hibernate Validator  
- Lombok  
- Banco de dados relacional (ex: MySQL / H2)  

---

## 📌 Validações

O projeto utiliza Bean Validation:

- `@NotBlank` → campos String obrigatórios  
- `@NotNull` → valores obrigatórios  
- `@Size` → limite de caracteres  
- `@DecimalMin / @DecimalMax` → validação de preço  
- `@Min / @Max` → validação de quantidade  
- `@Email` → validação de email  
- `@CNPJ` → validação de fornecedor  

---

## 🧩 Arquitetura

O projeto segue o padrão em camadas:

- **Controller** → expõe a API REST  
- **Service** → regras de negócio  
- **Repository** → acesso ao banco de dados  
- **Model** → entidades JPA  

---

## 🚀 Conclusão

Este projeto demonstra a construção de uma API REST completa com Spring Boot, incluindo relacionamentos entre entidades, validação de dados e filtros dinâmicos, simulando um sistema real de e-commerce.
