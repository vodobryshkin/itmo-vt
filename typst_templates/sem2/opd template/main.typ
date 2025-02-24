#set text(
  font: "Stix Two Text",
  size: 14pt
)
#set par(
  leading: 0.5em,
)
#include "title_opd.typ"
#pagebreak()

#show: doc => {
  set page(footer: align(center, counter(page).display()))
  doc
}

#include "main_part.typ"