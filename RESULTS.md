# Задания

## Лабораторная 2

**Описание:** [LAB-2.md](src/regex/LAB-2.md)

Код:
* [include/](include): описание токенов и грамматики, а также парсер и лексер
    * помимо описания в формате [Description](include/translate/codegen/info/GrammarInfo.kt), дополнительная генерация кода не требуется
* [src/regex/](src/regex):
    * [Tests](src/regex/test/Tests.kt): тесты для этой грамматики и ее парсинга
    * [Main](src/regex/Main.kt): утилита для построения и компиляции AST в .pdf
* [src/gen/](src/gen):
    * [RegexDescription](src/gen/RegexGrammarInfo.kt): реализация грамматики для регулярных выражений
    (которая на данный момент генерируется с помощью парсера на [MetaDescription](include/translate/meta/MetaGrammarInfo.kt))
    
## Лабораторная 3

**Описание:** [LAB-3.md](src/antlr/LAB-3.md)

Код:
* [lib/](lib): библиотека _antlr-4.7.2-complete.jar_
* [src/antlr/](src/antlr):
    * [Prefix.g4](src/antlr/Prefix.g4): грамматику _antlr_, описывающую примитивные префиксные программы
    * [make.bat](src/antlr/make.bat): скрипт для сборки парсера
    * сгенерированные файлы _antlr_, в том числе [prefixBaseListener](src/antlr/prefixBaseListener.java), использующийся для трансляции
    
## Лабораторная 4

**Описание:** [README.md](README.md)