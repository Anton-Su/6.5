package di


import Security.PasswordHasher
import data.repository.UserRepositoryImpl
import domain.repository.UserRepository
import controller.AuthController
import controller.PrizeController
import data.repository.PrizeRepositoryImpl
import domain.repository.PrizeRepository
import domain.usecases.GetPrizeUseCase
import domain.usecases.LoginUseCase
import kotlin.getValue

object AppContainer {
    val userRepository: UserRepository by lazy { UserRepositoryImpl() }
    val loginUseCase: LoginUseCase by lazy { LoginUseCase(userRepository, PasswordHasher) }
    val prizeRepository: PrizeRepository by lazy { PrizeRepositoryImpl() }
    val getPrizeUseCase: GetPrizeUseCase by lazy { GetPrizeUseCase(prizeRepository) }
//    val getUserCountriesUseCase: GetUserCountriesUseCase by lazy { GetUserCountriesUseCase(userRepository) }
    val authController: AuthController by lazy { AuthController(loginUseCase) }
    val prizeController: PrizeController by lazy { PrizeController(getPrizeUseCase) }
}

fun appModule() {
    println("DI инициализирован")
}