## Генератор атрибутно-транслирующих парсеров

**Класс грамматик:** LL-1

**Типы атрибутов:** 
* синтезируемые (вычисляются на основе значений детей)
* наследуемые (вычисляются на основе значений родителя и братьев)
* трансформирующие (вычисляются на основе других атрибутов в вершине)
---

### Сборка

1. Meta-описания:
    - [meta.helpers.MetaGrammarInfo](include/translate/meta/helpers/MetaGrammarInfo.kt) описывает грамматику языка описания грамматик .my
    - [meta.helpers.MetaVisitorBuilder](include/translate/meta/helpers/MetaVisitorBuilder.kt) описывает правила сборки [meta.MetaVisitorBase](include/translate/meta/MetaVisitorBase.kt) 
2. Meta-генерация: [meta.BuildMyself](include/translate/meta/BuildMyself.kt) генерирует
    - [meta.MetaWalkerBase](include/translate/meta/MetaWalkerBase.kt) - интерфейс для классов, обходящих AST мета-грамматики
        - генерируется с помощью [codegen.WalkerBuilder](include/translate/codegen/WalkerBuilder.kt)
    - [meta.MetaVisitorBase](include/translate/meta/MetaVisitorBase.kt) - интерфейс для классов, обходящих AST мета-грамматики и передающих/возвращающих данные из вершин во время обхода
        - генерируется с помощью [meta.helpers.MetaVisitorBuilder](include/translate/meta/helpers/MetaVisitorBuilder.kt)
---

### Функционал

1. Генерация
    - [codegen.GrammarInfoBuilder](include/translate/codegen/GrammarInfoBuilder.kt) наследует [meta.MetaWalkerBase](include/translate/meta/MetaWalkerBase.kt) 
        - использует AST .my файла 
        - собирает файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt)
    - [codegen.WalkerBuilder](include/translate/codegen/WalkerBuilder.kt)
        - использует файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt) 
        - собирает примитивный интерфейс, наследующий [Walker](include/structure/Walker.kt)
    - [codegen.VisitorBuilder](include/translate/codegen/VisitorBuilder.kt) наследует [meta.MetaVisitorBase](include/translate/meta/MetaVisitorBase.kt) 
        - использует AST .my файла и файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt)
        - собирает реализацию интерфейса [Visitor](include/structure/Visitor.kt), вычисляющую заданные атрибуты
2. Запуск
    - рядом с SomeNameVisitor генерируется SomeNameVisitorMain.kt
        - в нем достаточно считать исходную строку в заданной грамматике, после чего он выведет посчитанные атрибуты
---
---
---

#### TODO

1. дописать поддержку атрибутов и их преобразований
2. убрать файл GrammarInfo из требований VisitorBuilder (хотя зачем?)
3. переписать VisitorBuilder с другими Generic-параметрами (хотя читабельным это все равно вряд ли можно будет назвать)
4. написать тесты к `calculator` и `prefix`
5. доработать синтаксические возможности .my файлов
6. немного переделать грамматику .my файлов и MetaGrammarInfo, чтобы было проще парсить и было меньше ограничений на описание атрибутов

* сделать трансляцию из Visitor+GrammarInfo в .my файл
* сгенерировать meta.my
* генерировать MetaGrammarInfo и VisitorBuilder ими же (чем я вообще занимаюсь?...)