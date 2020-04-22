# Задание №2 - Коллекции

Написать свою реализвацию `ArrayList` -> `DIYArrayList`, задача - добиться работы следующих методов:
* Collections.copy(Collection, Collection)
* Collections.sort(Collection, Comparator)
* Collections.addAll(Collection, ...)

Реализованы все необходимые методы интерфейса `List`, нереализованные - выбрасывают 
`UnsupportedOperationException`

Из примечаний:
* Не стал реализовывать метод `DIYArraList#sort()`, воспользовался `default` методом
интерфейса `List#sort()`
* Написал ряд тестов, подтверждающих правильность работы реализованной коллекции
* JMH тестирование показало, что реализованная коллекция работает хуже, чем ArrayList, ниже приведены результаты сравнения
сортировок

```
Benchmark                                   Mode  Cnt  Score   Error  Units
DIYArrayListJMHSorting.sortDIYArrayList     avgt    5  9.640 ± 8.156  ms/op
DIYArrayListJMHSorting.sortNormalArrayList  avgt    5  3.800 ± 0.064  ms/op
```

* JMH-тест, для сравнения добавления элементов, через раз выдает `OutOfMemory`, причем при работе с ArrayList, отсюда
возникает идея необходимости чистить "отработанные" массивы, как в "C", после их "расширения",
вроде как это должен делать GC, но что-то явно идет не так
