## Генератор атрибутно-транслирующих парсеров

**Класс грамматик:** LL-1

**Типы атрибутов:** 
* синтезируемые (вычисляются на основе значений детей)
* наследуемые (вычисляются на основе значений родителя и братьев)
* трансформирующие (вычисляются на основе других атрибутов в вершине)

### Сборка

1. Основы:
    - [meta.MetaDescription](include/translate/meta/MetaDescription.kt) описывает грамматику языка описания грамматик .my
    - [codegen.AbstractVisitorBuilder](include/translate/codegen/AbstractVisitorBuilder.kt) использует файлы в формате [Description](include/structure/Description.kt)
    для построения абстрактного базового визитора AST заданной грамматики
2. Мета-генерация
    - [meta.Myself](include/translate/meta/Myself.kt) генерирует <span style="color:red !important">[meta.MetaBaseVisitor](include/translate/meta/MetaBaseVisitor.kt)</span>
    - его наследует [codegen.DescriptionBuilder](include/translate/codegen/DescriptionBuilder.kt)
3. Генерация
    - он, в свою очередь, используется для трансляции .my-файла в .kt-файл формата Description
    - *поддержка атрибутов: work in progress* 