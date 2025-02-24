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

#heading(level: 1)[Заголовок1]
#include "h1.typ"
#pagebreak()

#heading(level: 1)[Заголовок2]
#include "h2.typ"
#pagebreak()

#heading(level: 1)[Выводы по работе]
#include "conclusion.typ"