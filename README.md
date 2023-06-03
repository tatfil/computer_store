# computer_store
Для запуска приложения нужно:

1. скачать файл ComputerStore-1.0-SNAPSHOT.jar

2. зайти в папку где находится файл из пункта 1 , открыть терминал и в нем ввести команду java -jar ComputerStore-1.0-SNAPSHOT.jar

3. в браузере набрать http://localhost:8080/swagger-ui/index.html#/

Протестировать приложение можно с помощью команд: 

Добавление товара
POST
{
  "name": "PC4",
  "brand": "Asus",
  "price": 500,
  "productCategoryName": "COMPUTER",
  "properties": [
  ]
}

Редактирование товара
PUT

{
  "id": 1,
  "name": "monitor",
  "brand": "sony",
  "price": 50,
  "productCategoryName": "MONITOR",
  "properties": [

  ]
}

Просмотр товара по идентификатору
GET/products
Get product card by id
id = 1

Просмотр всех существующих товаров по типу
GET/products
Get product cards by category
category = MONITOR
