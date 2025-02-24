#set heading(numbering: "1.")

#show heading: it => [
  #set text(size: 1.2em)
  #block(smallcaps(it.body))
]

#outline(indent: 1em, title: [Содержание])

#pagebreak()
#heading(level: 1)[Текст задания]
#include "task_text.typ"
#pagebreak()

#heading(level: 1)[Диаграмма классов разработанной программы]
#include "diagram.typ"
#pagebreak()

#heading(level: 1)[Исходный код программы]
#include "code.typ"
#pagebreak()

#heading(level: 1)[Выводы по работе]
#include "conclusion.typ"