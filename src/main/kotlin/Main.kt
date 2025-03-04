import kotlin.system.exitProcess

val archives = mutableListOf<Archive>()

fun main() {
    println("\nДобро пожаловать в приложение \"Заметки\"!")
    Navigator.navigate(MainMenu)
}

object Navigator {
    private val screenStack = mutableListOf<Screen>()

    fun navigate(screen: Screen) {
        screenStack.add(screen)
        screen.show()
    }

    fun goBack() {
        if (screenStack.size > 1) {
            screenStack.removeLast()
            screenStack.last().show()
        } else {
            exitProcess(0)
        }
    }
}

interface Screen {
    fun show()
}

object MainMenu : Screen {
    override fun show() {
        println("\nСписок архивов:")
        println("0. Создать новый архив")
        archives.forEachIndexed { index, archive -> println("${index + 1}. ${archive.name}") }
        println("${archives.size + 1}. Выход")

        when (val choice = readUserInput(archives.size + 1)) {
            0 -> createArchive()
            in 1..archives.size -> Navigator.navigate(ArchiveMenu(archives[choice - 1]))
            else -> exitProcess(0)
        }
    }

    private fun createArchive() {
        val name = readUserInput("Введите имя архива:")
        archives.add(Archive(name))
        println("Архив \"$name\" создан.")
        show()
    }
}