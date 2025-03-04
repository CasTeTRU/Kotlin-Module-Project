class NoteMenu(private val note: Note) : Screen {
    override fun show() {
        println("\nЗаметка: ${note.title}")
        println("Содержание: ${note.content}")
        println("Нажмите Enter, чтобы вернуться.")
        readln()
        Navigator.goBack()
    }
}

data class Archive(val name: String, val notes: MutableList<Note> = mutableListOf())

data class Note(val title: String, val content: String)

fun readUserInput(prompt: String): String {
    var input: String
    do {
        print("$prompt ")
        input = readln().trim()
        if (input.isEmpty()) println("Ошибка: ввод не может быть пустым. Попробуйте снова.")
    } while (input.isEmpty())
    return input
}

fun readUserInput(range: Int): Int {
    while (true) {
        print("Выберите номер: ")
        val input = readln().toIntOrNull()
        if (input in 0..range) return input ?: 0
        println("Ошибка: Введите число от 0 до $range.")
    }
}