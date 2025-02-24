#set heading(numbering: "1.")

#show heading: it => [
  #set text(size: 1.2em)
  #block(smallcaps(it.body))
]

#outline(indent: 1em, title: [Содержание])

#pagebreak()
#heading(level: 1)[Задание]
#include "task_text.typ"
#pagebreak()

#heading(level: 1)[Этапы выполнения]
#heading(level: 2)[Текст исходной программы]
#include "basic_text.typ"
#pagebreak()

#heading(level: 2)[Описание программы]
#include "description.typ"
#pagebreak()
#heading(level: 2)[Трассировка программы]
#include "trassing.typ"
#pagebreak()

#heading(level: 1)[Вывод]
#include "conclusion.typ"