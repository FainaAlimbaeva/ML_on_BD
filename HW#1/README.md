# HW#1 Hadoop

## Блок 1. Развертывание локального кластера Hadoop
~~~
1) Развернуть локальный кластер в конфигурации 1 NN, 3 DN + NM, 1 RM, 1 History server (инструкция) +
2) Изучить настройки и состояние NM и RM в веб-интерфейсе +
3) Сделать скриншоты NN и RM, добавить в репозиторий +
~~~
Файлы находятся в папке Block_1

Запуск кластера по команде: 
```
docker-compose up
```
Подключение к Namenode:
```
http://localhost:9870/dfshealth.html#tab-overview
```
Подключение к Resourcemanager:
```
http://localhost:8088/cluster
```
## Блок 2. Написание map reduce на Python
~~~
1. Загрузите датасет по ценам на жилье Airbnb, доступный на kaggle.com: https://www.kaggle.com/dgomonov/new-york-city-airbnb-open-data +
2. Подсчитайте среднее значение и дисперсию по признаку ”price” стандартными способами (”чистый код” или использование библиотек). Не учитывайте пропущенные значения при подсчете статистик. +
3. Используя Python, реализуйте скрипт mapper.py и reducer.py для расчета каждой из двух величин. В итоге у вас должно получиться 4 скрипта: 2 mapper и 2 reducer для каждой величины. +
4. Проверьте правильность подсчета статистик методом map-reduce в сравнении со стандартным подходом +
5. Результаты сравнения (то есть, подсчета двумя разными способами) для среднего значения и дисперсии запишите в файл .txt. В итоге, у вас должно получиться две пары значений (стандартного расчета и map-reduce)- одна пара для среднего, другая - для дисперсии. +
6. Итоговый результат с выполненным заданием должен включать в себя сам код, а также результаты его работы, который необходимо разместить в репозитории. +
~~~
Файлы находятся в папке Block_2

Запуск джобы на Hadoop:
```
mapred streaming -mapper "python3 mapper_var.py" -file /mapper_var.py -reducer "python3 reducer_var.py" -file /reducer_var.py -input /AB_NYC_2019.csv -output /out
mapred streaming -mapper "python3 mapper_mean.py" -file /mapper_mean.py -reducer "python3 reducer_mean.py" -file /reducer_mean.py -input /AB_NYC_2019.csv -output /out
```
Локальная проверка скриптов на контейнере:
```
cat AB_NYC_2019.csv | python3 mapper_mean.py | python3 reducer_mean.py
```
Разница в значениях заметна толкьо после 10 знака после запятой.
