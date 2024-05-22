# Сервис для работы с экземплярами депозитных продуктов

### Порядок установки и запуска

### Sonarqube + sonar plugin

1) Запустите docker-compose -> войдите в sonarqube по http://localhost:9000
2) Поменяйте пароль
3) Нажимайте Create local project
4) Заполняете поля, project key можете взять из pom.xml
5) Выбираете глобальные настройки
6) Генерируете token и копируете его
7) После этот токен вставляете в pom.xml <sonar.token> </sonar.token>
8) запускаете плагин sonar:sonar
9) В sonarqube появится отчёт

#### Примеры разных роутов
https://github.com/CamelCookbook/camel-cookbook-examples/tree/master

#### Обработка ошибок в камеле

https://camel.apache.org/manual/exception-clause.html


### Релиз образов и артефактов

#### К примеру, версия в pom = 1.0.0, номер сборки считается на ci/cd

##### Для release веток:
    ms-ppcd-example-app:release-[номер-релиза]-1.0.0.[номер-сборки]

    Пример: ms-ppcd-example-app:release-14.10-2024.05-1.0.0.1  

##### Для hotfix веток:
    ms-ppcd-example-app:1.0.[номер-сборки]

    Пример: ms-ppcd-example-app:1.0.1

##### Для веток не release/hotfix выполняется перезапись образов:
    ms-ppcd-example-app:develop-1.0.0 (как 1.0.0-snapshot)

##### Публикация артефактов (библиотек) в nexus:
    lib-ms-ppcd-example-api:1.0.0 (пример)

#####  Публикация библиотеки состоит из двух этапов
    1. инкремент версии (берется последняя версия из nexus, инткрементится, записывается в pom)
    2. сама публикация в nexus (pushToMaven)

##### Публикация snapshot библиотеки перезаписывает текущую версию:
    1. pushToMaven 
    2. не забыть про очистку в .m2 (иначе в кеше останется старый образ)