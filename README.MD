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