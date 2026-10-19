sealed class CoffeeMachineState {
    // E1: Estado inicial de la máquina de café
    object Idle : CoffeeMachineState()
    // E2: Estado cuando el usuario está insertando dinero
    data class InsertingMoney(val balance: Double) : CoffeeMachineState()
    // E3: Estado cuando el usuario está eligiendo el tipo de café
    data class SelectingCoffee(val balance: Double) : CoffeeMachineState()
    // E4: Estado cuando la máquina está preparando el café
    data class MakingCoffee(val coffee: Bebidas, val balance: Double) : CoffeeMachineState()
    //E5: Estado cuando la máquina está sirviendo el café
    data class ServingCoffee(val coffee: Bebidas, val change: Double) : CoffeeMachineState()
    //E6: Estado cuando ocurre algun tipo de error en la máquina
    data class Error(val message: String) : CoffeeMachineState()
}