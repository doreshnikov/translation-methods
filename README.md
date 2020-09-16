## Генератор атрибутно-транслирующих парсеров

**Класс грамматик:** LL-1

**Типы атрибутов:** 
* синтезируемые (вычисляются на основе значений детей)
* наследуемые (вычисляются на основе значений родителя и братьев)
* трансформирующие (вычисляются на основе других атрибутов в вершине)
---

## Примеры описания грамматик

* текстовые: [здесь](include/translate/examples)
* собранные: [здесь](gen) (файлы <Something>GrammarInfo.kt и <Something>Visitor.kt)
* мета-грамматика (грамматика языка описания грамматик: [MetaGrammarInfo.kt](include/translate/meta/helpers/MetaGrammarInfo.kt)

### Функционал

1. Генерация
    - [codegen.GrammarInfoBuilder](include/translate/codegen/GrammarInfoBuilder.kt)
        - использует AST .my файла 
        - собирает файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt)
    - [codegen.WalkerBuilder](include/translate/codegen/WalkerBaseBuilder.kt)
        - использует файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt) 
        - собирает примитивный интерфейс, наследующий [Walker](include/structure/Walker.kt)
    - [codegen.VisitorBuilder](include/translate/codegen/VisitorBuilder.kt) наследует [meta.MetaVisitorBase](include/translate/meta/MetaVisitorBase.kt) 
        - использует AST .my файла и файл [GrammarInfo](include/translate/codegen/helpers/GrammarInfo.kt)
        - собирает реализацию интерфейса [Visitor](include/structure/Visitor.kt), вычисляющую заданные атрибуты
2. Запуск
    - у сгенерированного Visitor есть метод `.collect(ASTNode<Token>)`
    - этому методу на вход подается корень AST, полученный как `Parser(grammarInfo).parse(<string>)`
