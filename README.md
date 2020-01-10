## Генератор атрибутно-транслирующих парсеров

**Класс грамматик:** LL-1

**Типы атрибутов:** 
* синтезируемые (вычисляются на основе значений детей)
* наследуемые (вычисляются на основе значений родителя и братьев)
* трансформирующие (вычисляются на основе других атрибутов в вершине)

### Сборка

1. Meta-описания:
    - [meta.grammar.MetaGrammarInfo](include/translate/meta/helpers/MetaGrammarInfo.kt) описывает грамматику языка описания грамматик .my
    - [meta.visitors.MetaVisitorInfo](include/translate/meta/helpers/MetaWalkerBuilder.kt) и [meta.visitors.MetaAttributedVisitorInfo](include/translate/meta/helpers/MetaVisitorBuilder.kt) 
    описывают правила codegen'а для получения Visitor'ов мета-грамматики
2. Мета-генерация: [meta.BuildMyself](include/translate/meta/BuildMyself.kt) генерирует
    - по MetaVisitorInfo - [meta.visitors.MetaVisitorBase](include/translate/meta/MetaWalkerBase.kt)
    - по MetaAttributedVisitorInfo - [meta.visitors.MetaAttributedVisitorBase](include/translate/meta/MetaVisitorBase.kt)
3. Генерация
    - MetaVisitorBase наследуется [codegen.GrammarInfoBuilder](include/translate/codegen/GrammarInfoBuilder.kt)
        - он по AST .my файла собирает файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt)
    - MetaAttributedVisitorBase наследуется [codegen.VisitorInfoBuilder](include/translate/codegen/WalkerBuilder.kt)
        - он по AST .my файла собирает файл [VisitorInfo](include/translate/codegen/helpers/BuilderHelper.kt)
4. Сборка транслятора
    - полученный VisitorInfo, используя лежащий рядом GrammarInfo генерирует Visitor, обходящий AST заданной грамматики и считающий атрибуты по описанным правилам
    - *поддержка атрибутов: work in progress*
        - на данный момент можно есть VisitorInfoBuilder, собирающий VisitorInfo для абстрактного Visitor как в antlr 