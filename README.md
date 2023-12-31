# Дипломная работа «Облачное хранилище»

Запуск приложения : 

- Скачать или склонировать репозиторий приложения в программу IntelliJ IDEA

- Упаковать проект в Maven

- В терминале в корне приложения выполнить команду docker build ./ 

- Упаковать образ в контейнер командой docker-compose build

- Запустить контейнер командой docker-compose up

- Для входа можно использовать дефолтные данные :  почта - user, пароль - user

## Описание проекта

Задача — разработать REST-сервис. Сервис должен предоставить REST-интерфейс для загрузки файлов и вывода списка уже загруженных файлов пользователя. 

Все запросы к сервису должны быть авторизованы. Заранее подготовленное веб-приложение (FRONT) должно подключаться к разработанному сервису без доработок, 
а также использовать функционал FRONT для авторизации, загрузки и вывода списка файлов пользователя.

## Требования к приложению

- Сервис должен предоставлять REST-интерфейс для интеграции с FRONT.
- Сервис должен реализовывать методы
  1. Вывод списка файлов.
  2. Добавление файла.
  3. Удаление файла.
  4. Авторизация.
- Все настройки должны вычитываться из файла настроек
- Информация о пользователях сервиса (логины для авторизации) и данные должны храниться в базе данных Postgres

## Реализация

- Приложение разработано с использованием Spring Boot.
- Использован сборщик пакетов maven.
- Для запуска используется docker, docker-compose.
- Код размещён на Github.
- Код покрыт unit-тестами с использованием mockito.
- Добавлены интеграционные тесты с использованием testcontainers.
- Протестировано приложение с FRONT
- Протестировано приложение с помощью postman.

### FRONT
- для запуска FRONT: 
- необходимо скачать его по ссылке [FRONT]
- Следуя описанию README.md FRONT проекта, запустите nodejs-приложение (npm install, npm run serve)
