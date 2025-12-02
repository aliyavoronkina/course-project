# Автоматизация тестирования сервиса покупки туров



### 1. Запуск БД
docker-compose up -d

### 2.SUT (тестируемое приложение) 
java -jar artifacts/aqa-shop.jar

### 3. Запуск тестов
./gradlew clean test

### 4.Просмотр отчетов Allure
./gradlew allureServe

