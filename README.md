# Задания

## Лабораторная 2

**Описание:** [LAB-2.md](src/regex/LAB-2.md)

Код:
* [include/](include): содержит описание общего вида токенов, грамматики, парсера и лексера
* [regex/](src/regex): содержит 
    * [Regex](src/regex/RegexDescription.kt): реализацию грамматики для регулярных выражений
    * [Tests](src/regex/test/Tests.kt): тесты для этой грамматики и ее парсинга
    
## Лабораторная 3

**Описание:** [LAB-3.md](src/prefix/LAB-3.md)

Код:
* [lib/](lib): содержит _antlr-4.7.2-complete.jar_ в качестве библиотеки
* [prefix/](src/prefix): содержит 
    * [prefix.g4](src/prefix/Prefix.g4): грамматику _antlr_, описывающую примитивные префиксные программы
    * [make.bat](src/prefix/make.bat): скрипт для сборки парсера
    * сгенерированные файлы _antlr_, в том числе [prefixBaseListener](src/prefix/prefixBaseListener.java), использующийся для трансляции