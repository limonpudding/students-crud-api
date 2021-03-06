# Описание REST сервиса для работы с таблицей "Students"

Приложение реализует сервис, который позволяет работать с сущностями типа Student. Сервис позволяет создавать, изменять, просматривать и удалять объекты.

Приложение написано с помощью фреймворка Spring Boot. В качестве базы данных используется H2.
База данных представлена в виде файла и автоматически создаётся при первом запуске приложения.
Таблица Students также создаётся автоматически на основе соответствующего Java класса.
Поля таблицы:
```sql
ID INTEGER NOT NULL PRIMARY KEY,
NAME VARCHAR(255) NOT NULL,
PASSPORT VARCHAR(11) NOT NULL UNIQUE
```



Пример сущности в формате Json:

```json
{
  "id": 1,
  "name": "Виталий Смирнов",
  "passport": "1234 123456"
}
```

Поддерживаемые сервисом запросы:

| Действие  | HTTP метод | URL           | Входные данные в формате Json         |
|:---------:|:----------:|:-------------:|:-------------------------------------:|
| Создание  | POST       | /student      | Требуется указать name и passport     |
| Чтение    | GET        | /student/{id} | -                                     |
| Чтение    | GET        | /students     | -                                     |
| Изменение | PUT        | /student/{id} | Требуется указать name и/или passport |
| Удаление  | DELETE     | /student/{id} | -                                     |


# Спецификация API
## Создать новую сущность
**URL**: /student  
**HTTP метод**: POST  
**Значения возвращаемых HTTP статусов:**  
200 (OK): Сущность успешно создана и сохранена в БД  
400 (BAD REQUEST): Неверный формат записи passport  
409 (CONFLICT): В БД уже существует запись с указанным passport

### Заголовки запроса:
**content-type: application/json**
### Тело запроса:
```metadata json
{
  "name": "<ФИО студента>",
  "passport": "<Серия и номер паспорта через пробел>"
}
```
### Тело ответа:
```metadata json
{
  "id": <Присвоенный идентификатор>,
  "name": "<ФИО студента>",
  "passport": "<Серия и номер паспорта через пробел>"
}
```


## Получить сущность
**URL**: /student/{id}  
id - идентификатор сущности, которую требуется получить  
**HTTP метод**: GET  
**Значения возвращаемых HTTP статусов:**  
200 (OK): Сущность с указанным id найдена  
404 (NOT FOUND): Сущность с указанным id не найдена

### Тело ответа:
Если сущность с указанным id найдена:
```metadata json
{
  "id": <Идентификатор студента>,
  "name": "<ФИО студента>",
  "passport": "<Серия и номер паспорта через пробел>"
}
```


## Получить все сущности
**URL**: /students  
**HTTP метод**: GET  
**Значения возвращаемых HTTP статусов:**  
200 (OK): Успешный запрос

### Тело ответа:
Если найдена хотя бы одна сущность:
```metadata json
[
    {
      "id": <Идентификатор студента>,
      "name": "<ФИО студента>",
      "passport": "<Серия и номер паспорта через пробел>"
    },
    <...>
]
```
Если не найдено ни одной сущности:
```metadata json
[]
```


## Изменить сущность
**URL**: /student/{id}  
id - идентификатор сущности, которую требуется обновить  
**HTTP метод**: PUT  
**Значения возвращаемых HTTP статусов:**  
200 (OK): Сущность успешно обновлена и сохранена в БД  
400 (BAD REQUEST): Неверный формат записи passport  
404 (NOT FOUND): Сущность с указанным id не найдена  
409 (CONFLICT): В БД уже существует запись с указанным passport

### Заголовки запроса:
**content-type: application/json**
### Тело запроса:
```metadata json
{
  "name": "<ФИО студента>",
  "passport": "<Серия и номер паспорта через пробел>"
}
```
### Тело ответа:
```metadata json
{
  "id": <Идентификатор из URL>,
  "name": "<ФИО студента>",
  "passport": "<Серия и номер паспорта через пробел>"
}
```


## Удалить сущность
**URL**: /student/{id}  
id - идентификатор сущности, которую требуется удалить  
**HTTP метод**: DELETE  
**Значения возвращаемых HTTP статусов:**  
200 (OK): Сущность успешно удалена  
404 (NOT FOUND): Сущность с указанным id не найдена

###### * Если поле id указано в _теле запроса_, то оно игнорируется.

# Инструкция по развёртыванию приложения
Предусловия:
* Операционная система Windows или Linux
* Установлен Git
* Установлен JDK 8
* Установлен Maven

Шаги по порядку:
1. Для Windows: войти в командную строку CMD, для Linux: открыть терминал.
2. Клонировать репозиторий: ```git clone https://github.com/limonpudding/students-crud-api.git```
3. Войти в полученную в результате папку: ```cd students-crud-api```
4. Собрать приложение с помощью Maven: ```mvn clean install```
5. Войти в папку target: ```cd target```
6. Для запуска приложения выполнить команду: ```java -jar students-crud-api-1.0.0.jar```  

Приложение использует порт 8080. Для отправки запросов в приложение можно использовать Postman. Например, URL для получения всех сущностей будет выглядеть так: http://localhost:8080/students
