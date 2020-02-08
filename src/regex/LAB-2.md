# Отчет, задание 2

_Вариант 2_
(ограниченные регулярные выражения)

---

## Базовая грамматика

```
Start: R
R -> S | R '|' S
S -> T | S T
T -> T '*' | A
A -> ['a'..'z'] | '(' R ')'
```

| Нетерминал | Описание |
| ---------- | -------- |
| `R (regexp)` | стартовое состояние |
| `S (sequence of terms)` | последовательность термов без непосредственного выбора |
| `T (term)` | атом или замыкание (возможно неоднократное) атома |
| `A (atom)` | символ алфавита или выражение в скобках |

К сожалению, в такой грамматике есть левая рекурсия (непосредственная). 
От нее можно избавиться двумя способами:

1. заметить, что операции выбора и конкатенации не имеют строгой ассоциативности (их можно применять в любом порядке)
2. воспользоваться стандартным алгоритмом избавления от непосредственной левой рекурсии

## Измененная грамматику

1. если менять порядок выбора и конкатенации, а также разбить терм на атом и последовательность '*', получается:
```
R  -> S R'
R' -> eps | '|' R
S  -> T S'
S' -> eps | S
T  -> A C
C  -> eps | '*' C
A  -> ['a'..'z'] | '(' R ')'
```

2. если применить алгоритм устранения непосредственной левой рекурсии [Grammar::directLeftRecursionElimination](../../include/grammar/Grammar.kt), 
получается:
```
R  -> S R' | S
R' -> '|' S | '|' S R'
S  -> T S' | T
S' -> T | T S'
T  -> A T' | A
T' -> '*' | '*' T'
A  -> ['a'..'z'] | '(' R ')'
```

Заметим, что в полученной грамматике теперь есть правое ветвление. 
Воспользовавшись алгоритмом устранения правого ветвления, [Grammar::rightBranchingElimination](../../include/grammar/Grammar.kt),
мы получим огромную грамматику, c которой работать явно менее удобно, чем с первой:
```
R    -> S R'
R'   -> R'' | eps
R''  -> '|' S R'''
R''' -> eps | R''
S    -> T S'
S'   -> S'' | eps
S''  -> T S'''
S''' -> eps | S''
T    -> A T'''
T''' -> T' | eps
T'   -> '*' T''
T''  -> eps | T'
A    -> ['a'..'z'] | '(' R ')'
```

## Используемая грамматика
```
Start: R
R  -> S R'
R' -> eps | '|' R
S  -> T S'
S' -> eps | S
T  -> A C
C  -> eps | '*' C
A  -> ['a'..'z'] | '(' R ')'
```

| Nonterminal | Описание |
| ----------- | -------- |
| `R (regex)` | стартовое состояние |
| `R' (regex continuation)` | последовательность альтернатив первому Sequence of terms |
| `S (sequence of terms)` | последовательность термов без непосредственного выбора |
| `S' (sequence continuation)` | продолжение последовательности термов |
| `T (term)` | атом или замыкание (возможно неоднократное) атома |
| `A (atom)` | символ алфавита или выражение в скобках |
| `C (closure)` | замыкание (возможно неоднократное) стоящего слева атома |

## FIRST и FOLLOW

Построим множества FIRST и FOLLOW для описанной грамматики. 
Для этого воспользуемся алгоритмом их построения, описанным в [Helper](../../include/parse/Helper.kt):

### FIRST
```
R  : '(', ['a'..'z']
R' : eps, '|'
S  : '(', ['a'..'z']
S' : eps, '(', ['a'..'z']
T  : '(', ['a'..'z']
C  : eps, '*'
A  : '(', ['a'..'z']
```

### FOLLOW
```
R  : $, ')'
R' : $, ')'
S  : $, ')', '|'
S' : $, ')', '|'
T  : $, '(', ')', '|', ['a'..'z']
C  : $, '(', ')', '|', ['a'..'z']
A  : $, '(', ')', '|', '*', ['a'..'z']
```

## Processing

### Фиксированная грамматика

В [RegexDescriptionы](../gen/RegexGrammarInfo.kt)
* задаем алфавит языка
```kotlin
object regex        : Token.StateToken("regex")
object regexPlus    : Token.StateToken("regexPlus")
object sequence     : Token.StateToken("sequence")
object sequencePlus : Token.StateToken("sequencePlus")
object term         : Token.StateToken("term")
object closure      : Token.StateToken("closure")
object atom         : Token.StateToken("atom")

object LPAREN       : Token.StringToken("LPAREN", "(")
object RPAREN       : Token.StringToken("RPAREN", ")")
object KLEENE       : Token.StringToken("KLEENE", "*")
object CHOICE       : Token.StringToken("CHOICE", "|")

object ALPHA        : Token.CharRangeToken("ALPHA", 'a'..'z')
```
* задаем грамматику, с которой работаем
```kotlin
private val grammar = Grammar(
    regex,
    
    regex           into Expansion(sequence, regexPlus),
    regexPlus       into Expansion(CHOICE, regex),
    regexPlus       into Expansion(Token.UniqueToken.EPSILON),
    sequence        into Expansion(term, sequencePlus),
    sequencePlus    into Expansion(sequence),
    sequencePlus    into Expansion(Token.UniqueToken.EPSILON),
    term            into Expansion(atom, closure),
    closure         into Expansion(KLEENE, closure),
    closure         into Expansion(Token.UniqueToken.EPSILON),
    atom            into Expansion(LPAREN, regex, RPAREN),
    atom            into Expansion(ALPHA)
).order()
```

### Helper и Lexer

1. [Helper](../../include/parse/Helper.kt) считает `FIRST` и `FOLLOW` по описанной грамматике
2. [Lexer](../../include/parse/Lexer.kt) использует описания токенов и выдает следующий [Token](../../include/grammar/token/Token.kt). 
Для ограничения типа токена, получаемого из лексера, есть интерфейс [Restricted](../../include/grammar/token/Restricted.kt)
    ```kotlin
    class Lexer : Restricted by Restricted.Symbolic + Restricted.Eof
    ```

### Parser

[Parser](../../include/parse/Parser.kt) использует Lexer и Helper заданной грамматики, 
чтобы по построенным `FIRST` и `FOLLOW` для текущего состояния найти в какое правило его раскрыть:
```kotlin
private fun parse(state: Token.StateToken, lexer: Lexer): ASTNode<Token.StateToken> {
    val rule = description.getGrammar().RULES[state]

    val byFirst = rule.expansions.filter { Token.isAcceptable(lexer.getToken(), helper.FIRST(it)) }
    val byFollow = rule.expansions.filter { Token.isAcceptable(Token.UniqueToken.EPSILON, helper.FIRST(it)) }
    val isNullable = Token.isAcceptable(Token.UniqueToken.EPSILON, helper.FIRST[state]) &&
            Token.isAcceptable(lexer.getToken(), helper.FOLLOW[state])

    return when (val options = byFirst.size + (if (isNullable) byFollow.size else 0)) {
        0 -> throw ParseException(
            "Could not parse state $state: no expansions starting with ${lexer.getToken()}",
            lexer.getIndex()
        )
        1 -> {
            if (byFirst.isNotEmpty()) {
                parseExpandable(state, byFirst.first(), lexer)
            } else {
                parseNullable(state, byFollow.first())
            }
        }
        else -> throw ParseException(
            "Could not parse state $state: $options ambiguous expansions starting with ${lexer.getToken()}",
            lexer.getIndex()
        )
    }
}
```

## Тесты

Для тестирования используется класс [Test](../test/Test.kt):
1. `CorrectnessTest`
    * для проверки совпадения структуры дерева разбора
    * для проверки, что парсер работает без ошибок
2. `ParseExceptionTest`
    * для проверки, что во время парсинга выбрасывается ошибка `ParseException`
    
Тесты проверяют следующие случаи:
* корректный парсинг всех переходов
    * совпадение структуры дерева разбора с ожидаемой
* корректный парсинг сложных выражений
* обработка ошибок при некорректном вводе
    * lexer:
        * несовпадение символов строки заданному алфавиту
    * parser:
        * следующий токен не в `FIRST` и не в `FOLLOW`
        * выражение заканчивается до окончания разбора
        * разбор закончился, но конец строки не достигнут
        
## Модификация

Добавить выражения вида `<regex><number>`, означающие повторение `<regex>` `<number>` раз.

### Новая модификация

Как для `atom -> ALPHA` для отличия получаемых из [Lexer](../../include/parse/Lexer.kt) токенов со значениями 
от токена, использующегося в грамматике, был использован [CharRangeToken](../../include/grammar/token/Token.kt), так и здесь
* [Grammar](../../include/grammar/Grammar.kt) использует [RegexToken](../../include/grammar/token/Token.kt)
* [Lexer](../../include/parse/Lexer.kt) возвращает instance [RegexToken -> VariantInstanceToken](../../include/grammar/token/Token.kt)

### Новая грамматика

```kotlin
object regex        : Token.StateToken("regex")
object regexPlus    : Token.StateToken("regexPlus")
object sequence     : Token.StateToken("sequence")
object sequencePlus : Token.StateToken("sequencePlus")
object term         : Token.StateToken("term")
object number       : Token.StateToken("number")
object closure      : Token.StateToken("closure")
object atom         : Token.StateToken("atom")

object LPAREN       : Token.StringToken("LPAREN", "(")
object RPAREN       : Token.StringToken("RPAREN", ")")
object KLEENE       : Token.StringToken("KLEENE", "*")
object CHOICE       : Token.StringToken("CHOICE", "|")
object ALPHA        : Token.CharRangeToken("ALPHA", 'a'..'z')
object UINT         : Token.RegexToken("UINT", "[1-9][0-9]*".toRegex())

private val grammar = Grammar(
    regex,
    
    regex           into Expansion(sequence, regexPlus),
    regexPlus       into Expansion(CHOICE, regex),
    regexPlus       into Expansion(Token.UniqueToken.EPSILON),
    sequence        into Expansion(term, sequencePlus),
    sequencePlus    into Expansion(sequence),
    sequencePlus    into Expansion(Token.UniqueToken.EPSILON),
    term            into Expansion(atom, number, closure),
    number          into Expansion(UINT),
    number          into Expansion(Token.UniqueToken.EPSILON),
    closure         into Expansion(KLEENE, closure),
    closure         into Expansion(Token.UniqueToken.EPSILON),
    atom            into Expansion(LPAREN, regex, RPAREN),
    atom            into Expansion(ALPHA)
).order()
```
где

| Nonterminal | Описание |
| ----------- | -------- |
| `N (number)` | число-множитель атома |