![GitHub top language](https://img.shields.io/github/languages/top/NikitaPlatonov/Course-Work-2)
# Расчет статистики затрат

## Описание программы:

Программа позволяет подключаться к серверу и передавать данные о покупках в формате JSON. В результате расчетов возвращается категория с наибольшей суммой затрат. Для работы программы необходимо файл с категориями (categories.tsv), из которого она считывает информацию. Если при запуске программы присутствует файл сохранившийся после предыдущей работы, то производится восстановление информации о покупках.

## Инструкция по установке и запуску программы:

1. Скачайте исходный код программы.

1. Откройте скачанный проект в выбранной вами IDE (например, IntelliJ IDEA).

1. Убедитесь, что у вас установлен maven.

1. Сбилдите проект с помощью команды "mvn install".

1. Запустите программу командой "java -jar target/naimenovanie_jar.jar".

1. Следуйте инструкциям, выведенным в консоли.

## Пример использования:

Запустите программу.

Подключитесь к серверу с помощью клиента, передав JSON с данными о покупке:
```css
[  {    "title": "Хлеб",    "date": "2023-04-30",    "sum": 50  },  {    "title": "Молоко",    "date": "2023-04-30",    "sum": 80  },  {    "title": "Одежда",    "date": "2023-04-30",    "sum": 3000  },  {    "title": "Продукты",    "date": "2023-04-30",    "sum": 600  },  {    "title": "Развлечения",    "date": "2023-04-30",    "sum": 1500  }]
```
Программа вернет вам JSON с максимально затратной категорией в таком виде:
```json
{
  "maxCategory": {
    "category": "Одежда",
    "sum": 3000
  }
}
```
Программа автоматически сохранит данные о покупке в файле saveTxt.

## Требования к системе:

* Операционная система: Windows 7 и выше, Linux, MacOS.

* Установленная Java 8 или выше.

* Установленный maven.

* Свободный порт 8989.
