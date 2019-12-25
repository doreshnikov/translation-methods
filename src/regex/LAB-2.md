# Отчет, задание 2

_Вариант 2_
(ограниченные регулярные выражения)

---

## Построим грамматику

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

## Переделаем грамматику

1. если менять порядок выбора и конкатенации, а так же разбить терм на атом и последовательность '*', получим:
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
получим:
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
мы получим огромную грамматику, c которой работать явно менее удобно, чем с первой
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
Для этого воспользуемся алгоритмом их построения, описанным в [Helper](../../include/parse/Helper.kt)

* для предоставления внешним классам доступа к `FIRST[state]` и `FIRST(expansion)` используется [ConsistentViewer](../../include/utils/viewer/ConsistentViewer.kt)
* для предоставления внешним классам доступа к `FOLLOW[state]` используется [GetViewer](../../include/utils/viewer/GetViewer.kt)
* для аккуратного вывода алфавитных терминалов используется [Beautifier::tokenFold](../../include/utils/Beautifier.kt)

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

В [Regex](Regex.kt)
1. задаем алфавит языка
```kotlin
init {
    Token.AlphaToken.allow('a'..'z')
}

val LPAREN = PD('(')
val RPAREN = PD(')')
val KLEENE = PD('*')
val CHOICE = PD('|')

val R0 = ST("R")
val R1 = ST("R`")
val S0 = ST("S")
val S1 = ST("S`")
val T  = ST("T")
val C  = ST("C")
val A  = ST("A")
```
1. задаем грамматику, с которой работаем
```kotlin
val grammar = Grammar(
    R0,

    R0 into E(S0, R1),
    R1 into E(EPSILON),
    R1 into E(CHOICE, R0),
    S0 into E(T, S1),
    S1 into E(EPSILON),
    S1 into E(S0),
    T  into E(A, C),
    C  into E(EPSILON),
    C  into E(KLEENE, C),
    A  into E(LPAREN, R0, RPAREN)
).also {
    ('a'..'z').map { c -> A into E(AT(c)) }.forEach { r ->
        it.add(r)
    }
}.order()
```

### Helper и Lexer

1. В [Helper](../../include/parse/Helper.kt) считаем `FIRST` и `FOLLOW` по описанной грамматике
2. В [Lexer](../../include/parse/Lexer.kt) описываем правила получения следующего [Token](../../include/grammar/token/Token.kt). 
Для ограничения типа токена, получаемого из лексера, используем [TokenRestricted](../../include/grammar/token/RestrictedBy.kt)
    ```kotlin
    class Lexer : TR by TRUniversal + TRFollow
    ```

### Parser

В [Parser](../../include/parse/Parser.kt) используем Lexer и Helper заданной грамматики, 
чтобы по построенным `FIRST` и `FOLLOW` для текущего состояния найти в какое правило его раскрыть.
```kotlin
private fun parse(state: Token.State, lexer: Lexer): Tree {
    val rule = grammar.RULES[state]

    val byFirst = rule.expansions.filter { lexer.getToken() in helper.FIRST(it) }
    val byFollow = rule.expansions.filter { Token.EPSILON in helper.FIRST(it) }
    val isNullable = Token.EPSILON in helper.FIRST[state] && lexer.getToken() in helper.FOLLOW[state]

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

Для тестирования используется класс [Test](test/Test.kt):
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

Поскольку чисел бесконечно много, мы не можем для каждого из них добавить правило в грамматику. Для этого:
* в [Grammar](../../include/grammar/Grammar.kt) выводим токен для описания числа [AnyNumber](../../include/grammar/token/Token.kt)
* в [Lexer](../../include/parse/Lexer.kt) возвращаем настоящее число

Для проверки соответствия токена ожидаемому используется следующая проверка:
```kotlin
private fun isAcceptable(token: Token, originalToken: Token) : Boolean {
    return token == originalToken || token is RepresentedBy<*> && token.getRepresentation() == originalToken
}
```

Аналогично поступаем с символами алфавита для упрощения.

### Новая грамматика

```kotlin
val LPAREN = PD('(')
val RPAREN = PD(')')
val KLEENE = PD('*')
val CHOICE = PD('|')

val R0 = ST("R")
val R1 = ST("R`")
val S0 = ST("S")
val S1 = ST("S`")
val T  = ST("T")
val M  = ST("M")
val N  = ST("N")
val C  = ST("C")
val A  = ST("A")

val grammar = Grammar(
    R0,

    R0 into E(S0, R1),
    R1 into E(EPSILON),
    R1 into E(CHOICE, R0),
    S0 into E(T, S1),
    S1 into E(EPSILON),
    S1 into E(S0),
    T  into E(A, M),
    M  into E(N, C),
    N  into E(EPSILON),
    N  into E(Token.RepresentationToken.AnyNumber),
    C  into E(EPSILON),
    C  into E(KLEENE, C),
    A  into E(LPAREN, R0, RPAREN),
    A  into E(Token.RepresentationToken.AnyAlpha)
).order()
```
где

| Nonterminal | Описание |
| ----------- | -------- |
| `M (modification)` | любой суффикс-модификация атома |
| `N (number)` | число-множитель атома |