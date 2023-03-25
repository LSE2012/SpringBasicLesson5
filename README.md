# SpringBasicLesson5
Spring Basic Lesson5 https://lms.cbs.com.ua/mod/assign/view.php?id=1916

## Завдання 1
Вивчити конструкції та поняття, розглянуті на уроці.

## Завдання 2
Покрити всі методи проєкту, які мають нетривіальну логіку, тестами.

## Завдання 3
Ознайомитись з технікою розробки TDD.

## Завдання 4
Створити 2 тести, які тестують метод на перевірений тип породженого винятку за невалідних параметрів, що були передані в метод. Створити 2 тести, які тестуватимуть отримання інформації з БД. Створити 2 тести, які перевірять роботу контролерів..


## Requirements

* Postgres Database
* JDK 19
* 
1. Create database named `lesson4_db`
2. docker run --name Lesson4-pg-13.3 -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=lesson4_db -d postgres:13.3
2. Modify [Application config](src/main/resources/application.yaml) if needed
3. Run using IDEA or maven wrapper command `./mvnw spring-boot:run`
