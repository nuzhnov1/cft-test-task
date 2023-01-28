# Тестовое задание для ШИФТ ЦФТ
## Содержание задания
Условия задания описаны в [документе](./documents/task.pdf).
## Суть работы приложения
Приложение позволяет пользователю вводить BIN-номер банковской карты, 
по которому он может получить некоторую информацию о карте:
* Длину номера карты;
* Используется ли в ней алгоритм Луна для вычисления контрольной цифры 
номера пластиковой карты;
* Схему или сеть карты;
* Тип карты: дебитовая или кредитная;
* Логотип банковской карты или её бренд, например, VISA, American Express
и другие;
* Является ли карта предоплаченной, т.н. можно ли с данной карты
снимать наличные и совершать покупки;
* Информацию о банке-эмитенте:
    * Название банка;
    * Город, где он расположен;
    * URL-сайта банка;
    * Телефонный номер банка;
* Информацию о стране, где была выпущена карта:
    * Код страны;
    * Название;
    * Валюта страны;
    * Координаты страны;

БИН-номер банковской карты - часть номера, расположенного на пластиковой карте. 
Используется для идентификации банка в рамках карточной платежной системы при 
авторизации, процессинге и клиринге.  
Пользователь может, по мимо получения данных о карте из сервиса, сохранить 
полученные данные у себя локально. Все сохранённые карты отображаются в виде списка,
который пользователь также может очистить при желании.  
## Пример работы приложения
Устройство - Google Pixel;  
ОС - Android 11 R (API level 30);
![Пример работы приложения](./media/record.gif)
## Используемые библиотеки
* [ORM Room](https://developer.android.com/training/data-storage/room). Является одной из библиотек
Android Jetpack для автоматизации выполнения
транзакций к БД SQLite;
* [Kotlin Coroutines и Kotlin Flow](https://kotlinlang.org/docs/coroutines-guide.html).
Официальные библиотеки для языка Kotlin, предназначенные для выполнения асинхронных операций в
корутинах (Kotlin Coroutines) и для отслеживания изменения потока данных (Kotlin Flow);
* [Retrofit2](https://square.github.io/retrofit). Удобная библиотека для быстрого построения
REST-API интерфейсов;
* [Moshi](https://github.com/square/moshi). Библиотека для серилизации/десерилизации JSON-файлов.
Отличается удобством использования и простотой построения кастомных конвертеров для различных
типов;
* [Hitl](https://developer.android.com/training/dependency-injection/hilt-android). Библиотека
предназначенная для автоматизации внедрения зависимостей (Dependency Injection или DI) для компонент
Android. Является расширением для библиотеки [Dagger](https://dagger.dev);
* [ViewPager2](https://developer.android.com/guide/navigation/navigation-swipe-view-2). Библиотека
для создания пролистываемых экранов под Android;
## Тестирование работы приложения
Работоспособность приложения была протестирована на следующих устройствах:
* Galaxy Nexus. Android 4.4 KitKat (API level 19);
* Google Pixel. Android 11 R (API level 30);
