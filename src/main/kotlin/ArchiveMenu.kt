class ArchiveMenu(private val archive: Archive) : Screen {
    override fun show() {
        println("\nАрхив: ${archive.name}")
        println("0. Создать новую заметку")
        archive.notes.forEachIndexed { index, note -> println("${index + 1}. ${note.title}") }
        println("${archive.notes.size + 1}. Назад")

        when (val choice = readUserInput(archive.notes.size + 1)) {
            0 -> createNote()
            in 1..archive.notes.size -> Navigator.navigate(NoteMenu(archive.notes[choice - 1]))
            else -> Navigator.goBack()
        }
    }

    private fun createNote() {
        val title = readUserInput("Введите заголовок заметки:")
        val content = readUserInput("Введите текст заметки:")
        archive.notes.add(Note(title, content))
        println("Заметка \"$title\" создана.")
        show()
    }
}