object CoffeeMachine {
    private var currentState: CoffeeMachineState = CoffeeMachineState.Idle

    fun makeCoffee() {
        println("Estado actual: $currentState")

        when (val state = currentState) {
            is CoffeeMachineState.Idle -> {
                println("Máquina encendida. Inserte dinero...")
                currentState = CoffeeMachineState.InsertingMoney(2.0)
                println("Saldo actual: 2 €")

                currentState = CoffeeMachineState.SelectingCoffee(2.0)
                makeCoffee()
            }

            is CoffeeMachineState.InsertingMoney -> {
                println("Saldo disponible: ${state.balance} €")

                currentState = CoffeeMachineState.SelectingCoffee(state.balance)
                makeCoffee()
            }

            is CoffeeMachineState.SelectingCoffee -> {
                println("\nSelecciona tu café:")

                val bebidas = Bebidas.values()

                for (i in bebidas.indices) {
                    println("${i + 1}. ${bebidas[i]} - ${bebidas[i].precio} €")
                }

                print("Opción: ")
                val opcion = readln().toInt()

                if (opcion !in 1..bebidas.size) {
                    println("Opción no válida")
                    return
                }

                val coffee = bebidas[opcion - 1]

                if (state.balance >= coffee.precio) {
                    currentState = CoffeeMachineState.MakingCoffee(coffee, state.balance)

                    println("Preparando $coffee...")
                    Thread.sleep(2000)

                    val change = state.balance - coffee.precio

                    currentState = CoffeeMachineState.ServingCoffee(coffee, change)

                    println("Café listo: $coffee")
                } else {
                    println("Saldo insuficiente")
                    currentState = CoffeeMachineState.Error("Saldo insuficiente")
                }
            }

            is CoffeeMachineState.MakingCoffee -> {
                println("Espera, ya se está preparando tu café")
            }

            is CoffeeMachineState.ServingCoffee -> {
                println("Ya está listo. Toma tu café")
                println("Café: ${state.coffee}")
                println("Cambio: ${state.change} €")            }

            is CoffeeMachineState.Error -> {
                println("La máquina tiene un error: ${state.message}")
            }
        }
    }

    fun clean() {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle
        println("Máquina limpia. Estado: $currentState")
    }
}